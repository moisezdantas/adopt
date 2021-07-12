import React from "react";
import { Text, View, Alert, Image } from "react-native";
import { useAuth } from "../../hook/auth";
import { RectButton } from "react-native-gesture-handler";
// import { Avatar } from "../Avatar";
import { styles } from "./styles";

import ArrowPng from "../../assets/arrow.png";
import ArrowLeftPng from "../../assets/left.png";

import { LinearGradient } from "expo-linear-gradient";
import { BorderlessButton } from "react-native-gesture-handler";
export function Profile() {
  const { user, singOut } = useAuth();


  function handleSignOut() {
    Alert.alert("Logout", "Deseja sair do GamePlay", [
      {
        text: "Não",
        style: "cancel",
      },
      {
        text: "Sim",
        onPress: () => singOut(),
      },
    ]);
  }
 
  return (
    <View style={styles.container}>
      <RectButton onPress={handleSignOut}></RectButton>
      <View style={styles.profile}>
        <View style={styles.user}>
          <Text style={styles.greeting}>Olá,</Text>
          <Text style={styles.username}>{user.firstName}</Text>
        </View>
      </View>
    </View>
  );
}
