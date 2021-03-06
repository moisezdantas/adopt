import React from "react";
import { View, KeyboardAvoidingView, Platform, FlatList, Text } from "react-native";

import LogoSvg from "../../assets/animal.svg";

import { theme } from "../../global/styles/theme";
import { ButtonSquare } from "../../components/ButtonSquare";

import { styles } from "./styles";
import { Background } from "../../components/Background";

import { Profile } from "../../components/Profile";
import { useNavigation } from "@react-navigation/native";
import { Button } from "../../components/Button";

export function Home() {
  const navigation = useNavigation();

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      style={styles.container}
    >
      <Background>
        <Profile  />
        <LogoSvg style={styles.image} />
        <View style={styles.grupoButton}>
          <FlatList
            data={[
              {
                id: "00",
                title: "Adotar um amigo",
                cor: theme.colors.buttonPrimary,
                change: () => navigation.navigate("ToAdopt"),
              },
              {
                id: "01",
                title: "Fazer uma doação",
                cor: theme.colors.buttonSuccess,
                change: () => navigation.navigate("Donation"),
              },
              {
                id: "02",
                title: "Atualizar Perfil",
                cor: theme.colors.buttonBlue,
                change: () => navigation.navigate("ProfileUpdate"),
              },
              {
                id: "02",
                title: "Cadastrar um amigo",
                cor: theme.colors.buttonBlue2,
                change: () => navigation.navigate("RegisterFriend"),
              },
            ]}
            keyExtractor={(item) => item.id}
            numColumns={2}
            renderItem={({ item }) => {
              return (
                <View style={styles.item}>
                  <ButtonSquare
                    title={item.title}
                    onPress={item.change}
                    cor={item.cor}
                  />
                </View>
              );
            }}
          />
        </View>     
      </Background>
    </KeyboardAvoidingView>
  );
}
