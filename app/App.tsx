import React from "react";
import { StatusBar, LogBox } from "react-native";
import {
  useFonts,
  Roboto_300Light,
  Roboto_400Regular,
  Roboto_500Medium,
  Roboto_700Bold,
  Roboto_900Black,
} from "@expo-google-fonts/roboto";
import AppLoading from "expo-app-loading";

LogBox.ignoreLogs([
  "You are not currently signed in to Expo on your development machine.",
]);

import { AuthProvider } from "./src/hook/auth";
import { Routes } from "./src/routes";
import { Background } from "./src/components/Background";
import { theme } from "./src/global/styles/theme";

export default function App() {
  const [fontsLoaded] = useFonts({
    Roboto_300Light,
    Roboto_400Regular,
    Roboto_500Medium,
    Roboto_700Bold,
    Roboto_900Black,
  });

  if (!fontsLoaded) {
    return <AppLoading />;
  }

  return (
    <Background>
      <StatusBar
        barStyle="dark-content"
        backgroundColor={theme.colors.white}
        translucent
      />
      <AuthProvider>
        <Routes />
      </AuthProvider>
    </Background>
  );
}
