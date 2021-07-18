import { useNavigation } from "@react-navigation/native";
import React from "react";
import { Text, View, TouchableOpacity } from "react-native";
import { useAuth } from "../../hook/auth";
import { styles } from "./styles";

export function Profile() {
  const { user } = useAuth();

  const navigation = useNavigation();


  async function updateProfile() {
    navigation.navigate('ProfileUpdate')
  }

  return (
    <View style={styles.container}>
      <View style={styles.profile}>
        <TouchableOpacity onPress={updateProfile}>
          <View style={styles.user}>
            <Text style={styles.greeting}>Olá,</Text>
            <Text style={styles.username}>{user.firstName}</Text>
          </View>
        </TouchableOpacity>
      </View>
    </View>
  );
}
