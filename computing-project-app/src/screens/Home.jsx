import { View, Text, ActivityIndicator, Dimensions } from "react-native";
import React, { useState, useEffect }  from 'react';
import HomeStyles from "../styles/HomeStyles";
import { Switch } from 'react-native-paper';
import axios from 'axios';

import  { 
          SPRINGBOOT_API_REST_URL_LATEST,
          SPRINGBOOT_API_REST_URL_OVEN_STATE,
          SPRINGBOOT_API_REST_URL_MAX,
          SPRINGBOOT_API_REST_URL_MIN
        } from '../api/constants';

import * as Notifications from 'expo-notifications';

const screenWidth = Dimensions.get("window").width;
const fechaISO = new Date().toISOString().split('T')[0];

const Home = () => {

    const [isSwitchOn, setIsSwitchOn] = useState(false);
    const [ovenState, setOvenState] = useState('');
    const [dataOven, setDataOven] = useState({
        temperature : '',
        humidity : ''
    });
    const [maxread, setMaxRead] = useState({
        temperature : ''
    })
    const [minread, setMinRead] = useState({
        temperature : ''
    })

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [temperature, setTemperature] = useState(null);

    useEffect(() => {

        const fetchOvenState = async () => {
            try {

                const response = await axios.get(SPRINGBOOT_API_REST_URL_OVEN_STATE);
                const initialState = response.data;
                setIsSwitchOn(initialState === 'ON');   
                setOvenState(initialState);
            } catch (error) {
                console.log('Error en la petición:', error);
            }
        };

        const fetchMaxReading = async () => {

            try {

                const response = await axios.get(SPRINGBOOT_API_REST_URL_MAX);
                const reading = response.data;

                setMaxRead({temperature: reading.temperature })

            } catch(error){
                console.log(error)
            }

        };

        const fetchMinReading = async () => {
            try {

                const response = await axios.get(SPRINGBOOT_API_REST_URL_MIN);
                const reading = response.data

                setMinRead({temperature: reading.temperature})

            } catch(error){
                console.log(error)
            }
        }

        const fetchWheaterData = async () => {
            try {
                const response = await fetch(SPRINGBOOT_API_REST_URL_LATEST, {
                    method: 'get'
                });

                if (!response.ok) {
                    throw new Error('Error al obtener los datos de la API');
                }

                const data = await response.json();

            
                setDataOven({
                    temperature: data.temperature,
                    humidity: data.humidity
                });

                const temp = data.temperature;
                setTemperature(temp);

                if(temp > 30){
                    await schedulePushNotification(temp);
                }

            } catch (error) {
                console.log('Error en la petición:', error);
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };
        
        fetchOvenState();
        fetchWheaterData();
        fetchMaxReading();
        fetchMinReading();

        const interval = setInterval(() => {
            fetchWheaterData();
            fetchMaxReading();
            fetchMinReading();
        }, 10000);

        return () => clearInterval(interval);

    }, []);

    const schedulePushNotification = async (temp) => {
        await Notifications.scheduleNotificationAsync({
          content: {
            title: "¡Oven temperature is high! 🌡️",
            body: `The current temperature ${temp}°C.`,
            sound: true,
            data: { temperature: temp },
          },
          trigger: null,
        });
      };

    const onToggleSwitch = async () => {
        const newSwitchState = !isSwitchOn;
        const newOvenState = newSwitchState ? 'ON' : 'OFF';

        setIsSwitchOn(newSwitchState);
        setOvenState(newOvenState);

        try {
            await axios.post(SPRINGBOOT_API_REST_URL_OVEN_STATE, { state: newOvenState });
            console.log('Switch is', newSwitchState ? 'ON' : 'OFF');
            console.log('Oven log is', newOvenState);

        }catch(error) {
            console.log('Error en la petición:', error);
        }
    };

    return (
        <View style ={HomeStyles.maincontainer}>

            <View style={HomeStyles.weatherPanel}>
                <Text style={HomeStyles.panelTitle}>Oven Weather Panel</Text>

                {loading ? (
                    <ActivityIndicator size="large" color="#e31079" />
                ) : error ? (
                    <Text style={HomeStyles.errorText}>Error: {error}</Text>
                ) : (
                    <View style={HomeStyles.dataContainer}>
                        <Text style={HomeStyles.dataText}>Temperature: {dataOven.temperature}°C</Text>
                        <Text style={HomeStyles.dataText}>Humidity: {dataOven.humidity}%</Text>
                    </View>
                )}
            </View>

            <View style = {HomeStyles.weatherPanel}>

                <Text style={HomeStyles.panelTitle}>Oven Control Panel</Text>

                <Switch value={isSwitchOn} 
                        onValueChange={onToggleSwitch} 
                        trackColor={{ false : "#D3D3D3", true: "#09debb"}}
                        thumbColor={isSwitchOn ? "#09debb" : "#D3D3D3"}
                        ios_backgroundColor="#3e3e3e"
                        style = {HomeStyles.switch}
                />
                    <Text style={HomeStyles.statusText}>Oven is {ovenState}</Text>

            </View>

            <View style={HomeStyles.weatherPanel}>

                <Text style={HomeStyles.panelTitle}>Maximum Reading</Text>
                <Text>Temperature: {maxread.temperature}°C</Text>
                <Text>Date: {fechaISO}</Text>


            </View>

            <View style={HomeStyles.weatherPanel}>

                <Text style={HomeStyles.panelTitle}>Minimum Reading</Text>
                <Text>Temperature: {minread.temperature}°C</Text>
                <Text>Date: {fechaISO}</Text>

            </View>

        </View>
    )
}

export default Home;