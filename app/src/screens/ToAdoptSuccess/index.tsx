import React from "react";
import {
  View,
  KeyboardAvoidingView,
  Platform,
  Image,
  Text,
  ImageBackground,
} from "react-native";

import MaterialCommunityIcons from "react-native-vector-icons/FontAwesome";

import { useNavigation, useRoute } from "@react-navigation/native";

import { Animal } from "../../interfaces/animal";
import { styles } from "./styles";
import { theme } from "../../global/styles/theme";
import { api } from "../../services/api";
import { Button } from "../../components/Button";
import FestPng from "../../assets/fest.png";

type Params = {
  data: Animal;
};

export function ToAdoptSuccess() {
  const route = useRoute();
  const { data } = route.params as Params;

  const image = Image.resolveAssetSource(FestPng).uri;

  const urlImage = `${api.defaults.baseURL}/storage/view/${data.imageUrl}`;

  const navigation = useNavigation();

  function handleGoBack() {
    navigation.navigate('ToAdopt');
  }

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      style={styles.container}
    >
      <ImageBackground
        source={{ uri: image }}
        resizeMode="cover"
        style={styles.screenWrapper}
      >
        <View style={styles.view}>
          <Image source={{ uri: urlImage }} style={styles.image} />
          <View style={styles.row}>
            <Text style={styles.description}>
              Parabéns pelo seu amigo(a), agora ele fará parte da sua familia,
              cuide dele(a) com muito carinho.
            </Text>
          </View>
        </View>
      </ImageBackground>
      
      <View style={styles.content}>
        <Button
          title="Voltar"
          onPress={handleGoBack}
          cor={theme.colors.purple}
        />
      </View>
    </KeyboardAvoidingView>
  );
}
