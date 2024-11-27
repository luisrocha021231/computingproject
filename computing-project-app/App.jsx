import { StatusBar } from 'expo-status-bar';
import { PaperProvider } from 'react-native-paper';
import Home from './src/screens/Home';
import styles from './src/styles/AppStyles';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import * as Device from 'expo-device';
import * as Notifications from 'expo-notifications';
import Constants from "expo-constants";
import { useEffect } from 'react';
import { registerForPushNotificationsAsync } from './src/utils/notifications'

const Stack = createNativeStackNavigator();

Notifications.setNotificationHandler({
  handleNotification: async () => ({
    shouldShowAlert: true,
    shouldPlaySound: false,
    shouldSetBadge: false,
  }),
});

function AppStack(){

  return (
    <Stack.Navigator>
      <Stack.Screen name="Home" component={Home} 
        options={{
        title: "Oven Control", headerStyle: { backgroundColor: "#09debb",},
        headerTintColor: "#000", headerTitleStyle: { fontWeight: "bold",}, }}/>
    </Stack.Navigator>
  );
}

function App() {
  
  useEffect(() => {
    registerForPushNotificationsAsync();
  },[]);

  return (
    <PaperProvider style = {styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#09debb"/>
      <NavigationContainer>
        <AppStack/>
      </NavigationContainer>
    </PaperProvider>
  );

}

export default App;