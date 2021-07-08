import React from "react";
import { useAuth } from "../../hook/auth";
import {
  View,
  KeyboardAvoidingView,
  ScrollView,
  Platform,
  Text,
  FlatList,
} from "react-native";

import LogoSvg from "../../assets/animal.svg";

import { theme } from "../../global/styles/theme";
import { ButtonSquare } from "../../components/ButtonSquare";

import { styles } from "./styles";
import { Background } from "../../components/Background";

import { Profile } from "../../components/Profile";
import { useNavigation } from "@react-navigation/native";

export function Home() {
  const { user } = useAuth();

  const navigation = useNavigation();

  console.log(user);

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      style={styles.container}
    >
      <Background>
        <Profile />
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
                title: "Seja um padrinho",
                cor: theme.colors.buttonBlue,
                change: () => navigation.navigate("GodFather"),
              },
              {
                id: "03",
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

{
  /* <View style={styles.grupoButton}>
<ButtonSquare 
  title="Adotar um amigo" 
  onPress={() => console.log('d')}
  cor={theme.colors.buttonPrimary}
/>
 <ButtonSquare 
  title="Adotar um amigo" 
  onPress={() => console.log('d')}
  cor={theme.colors.buttonSuccess}
/>
</View>
<View style={styles.grupoButton}>
<ButtonSquare 
  title="Adotar um amigo" 
  onPress={() => console.log('d')}
  cor={theme.colors.buttonBlue}
/>
   <ButtonSquare 
  title="Adotar um amigo" 
  onPress={() => console.log('d')}
  cor={theme.colors.buttonBlue2}
/> */
}
