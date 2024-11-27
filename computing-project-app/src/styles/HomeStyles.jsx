import { StyleSheet } from 'react-native';

const HomeStyles =  StyleSheet.create( {
    maincontainer: {
        flex: 1,
        justifyContent: 'start',
        margin: 6
    },
    title : {
        fontSize: 12,
        fontWeight: 'bold',
        marginBottom: 5,
        alignItems: 'center', 
        shadowColor: '#e31079',

    },
    buttoncontainer: {
        alignItems: 'center'
    },
    switch: {
        transform: [{ scaleX: 1.2 }, { scaleY: 1.2 }],
      },
      statusText: {
          fontSize: 16,
          marginTop: 10,
      },
      weatherPanel: {
          marginTop: 30,
          padding: 20,
          backgroundColor: '#ffffff',
          borderRadius: 10,
          shadowColor: '#000',
          shadowOffset: { width: 0, height: 2 },
          shadowOpacity: 0.1,
          shadowRadius: 5,
          elevation: 2,
          alignItems: 'center',
      },
      panelTitle: {
          fontSize: 22,
          fontWeight: 'bold',
          marginBottom: 15,
      },
      dataContainer: {
          alignItems: 'center',
      },
      dataText: {
          fontSize: 18,
          marginVertical: 5,
      },
      errorText: {
          color: 'red',
          fontSize: 16,
      },
});

export default HomeStyles;
