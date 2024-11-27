import * as Device from "expo-device";
import * as Notifications from "expo-notifications";
import Constants from "expo-constants";

export async function registerForPushNotificationsAsync() {
  let token;

  if (Device.isDevice) {
    const { status: existingStatus } = await Notifications.getPermissionsAsync();
    let finalStatus = existingStatus;

    if (existingStatus !== "granted") {
      const { status } = await Notifications.requestPermissionsAsync();
      finalStatus = status;
    }

    if (finalStatus !== "granted") {
      alert("¡Permiso denegado para notificaciones!");
      return;
    }

    if (Constants.appOwnership === "expo") {
      token = (await Notifications.getExpoPushTokenAsync()).data;
    }

    console.log("Token de notificaciones:", token);
  } else {
    alert("Debes usar un dispositivo físico para probar notificaciones.");
  }

  return token;
}
