import React from "react";
import {
  View,
  KeyboardAvoidingView,
  Platform,
  Image,
  Text,
  Alert,
} from "react-native";

import MaterialCommunityIcons from "react-native-vector-icons/FontAwesome";

import { useNavigation, useRoute } from "@react-navigation/native";
import { Header } from "../../components/Header";

import { Animal } from "../../interfaces/animal";
import { styles } from "./styles";
import { theme } from "../../global/styles/theme";
import { api } from "../../services/api";
import { Button } from "../../components/Button";
import { createAdoptAnimal } from '../../services/animalService';
import { useAuth } from "../../hook/auth";

type Params = {
  data: Animal;
};


export function ToAdoptDetails() {
  const route = useRoute();
  const { user } = useAuth();
  const { data } = route.params as Params;

  const navigation = useNavigation();

  const urlImage = `${api.defaults.baseURL}/storage/view/${data.imageUrl}`;


  async function adopter() {
    Alert.alert("Adotar", "Deseja realmente adotar um amigo(a)", [
      {
        text: "Não",
        style: "cancel",
      },
      {
        text: "Sim",
        onPress: () => goAdopterSuccess(),
      },
    ]);
  }

  async function goAdopterSuccess() {

    try {
      await createAdoptAnimal({animalId: data.id, userId: user.id})
      navigation.navigate("ToAdoptSuccess", { data });
    } catch (error) {
      Alert.alert("Erro", 'Erro ao adotar uma amigo(a)');
    }
  }

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      style={styles.container}
    >
      <View style={styles.view}>
        <Header
          title="Perfil do Amigo"
          isVisibleBack
          isVisibleLogout
          cor={theme.colors.white}
          isDefaultButton={false}
        />
        <Image source={{ uri: urlImage }} style={styles.image} />
      </View>
      <View style={styles.content}>
        <Text style={styles.city}>
          {`${data.address.city} - ${data.address.district}`}
        </Text>
        <View style={styles.icon}>
          <MaterialCommunityIcons name="intersex" size={24} />
          <Text style={styles.male}>
            {data.typeAnimalGender === 1 ? " Macho" : " Fêmea"}{" "}
          </Text>
        </View>

        <Text style={styles.title}>{`Descrição do amigo(a)`}</Text>
        <View style={styles.row}>
          <Text style={styles.description}>{`${data.note}`}</Text>
        </View>
      </View>
      <View style={styles.row}>
        <Button
          title="Adotar amigo(a)"
          onPress={adopter}
          cor={theme.colors.purple}
        />
        <View style={styles.row}></View>
      </View>
    </KeyboardAvoidingView>
  );
}
