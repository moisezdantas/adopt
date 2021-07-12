import React, { useState, useEffect, useCallback } from "react";
import { useAuth } from "../../hook/auth";
import { View, KeyboardAvoidingView, Platform, FlatList } from "react-native";

import { theme } from "../../global/styles/theme";

import { Background } from "../../components/Background";

import { useNavigation } from "@react-navigation/native";
import { Header } from "../../components/Header";
import { Card } from "../../components/Card";
import { ButtonRound } from "../../components/ButtonRound";
import { fetchAnimalType, fetchAnimal } from "../../services/request";
import { AnimalType } from "../../interfaces/animalType";
import { Animal } from "../../interfaces/animal";
import { Load } from "../../components/Load";

import { styles } from "./styles";

export function ToAdoptDetails() {
  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      style={styles.container}
    >
      <Background>
        <View>
          <Header title="Perfil do Amigo" isVisibleBack isVisibleLogout />
        </View>
      </Background>
    </KeyboardAvoidingView>
  );
}
