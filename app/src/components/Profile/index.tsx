import React from "react";
import { Text, View, Alert, Image } from "react-native";
import { useAuth } from "../../hook/auth";
import { styles } from "./styles";

export function Profile() {
  const { user } = useAuth();

  return (
    <View style={styles.container}>
      <View style={styles.profile}>
        <View style={styles.user}>
          <Text style={styles.greeting}>Olá,</Text>
          <Text style={styles.username}>{user.firstName}</Text>
        </View>
      </View>
    </View>
  );
}
