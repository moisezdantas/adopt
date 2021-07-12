import React, { useState, useEffect, useCallback, useMemo } from "react";
import { useAuth } from "../../hook/auth";
import { View, KeyboardAvoidingView, Platform, FlatList } from "react-native";

import { theme } from "../../global/styles/theme";

import { styles } from "./styles";
import { Background } from "../../components/Background";

import { useNavigation } from "@react-navigation/native";
import { Header } from "../../components/Header";
import { Card } from "../../components/Card";
import { ButtonRound } from "../../components/ButtonRound";
import { fetchAnimalType, fetchAnimal } from "../../services/request";
import { AnimalType } from "../../interfaces/animalType";
import { Animal } from "../../interfaces/animal";
import { Load } from "../../components/Load";

type PropsAnimalType = {
  id: number;
  description: string;
  checked?: boolean;
};

export function ToAdopt() {
  const [animalTypes, setAnimalTypes] = useState<PropsAnimalType[]>();
  const [animals, setAnimals] = useState<Animal[]>();
  const [selectAnimalType, setSelectAnimalType] = useState(0);
  const [loading, setLoading] = useState(false);

  const { user } = useAuth();
  const navigation = useNavigation();

  const fetchDataAnimal = useCallback(async (animalTypeId?: number) => {
    setLoading(true);
    const data = await fetchAnimal({
      animalTypeId: animalTypeId === 0 ? 0: animalTypeId,
    });
    setAnimals(data?.content);
    setLoading(false);
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetchAnimalType();
      const types = response.map((item) => {
        return { ...item, checked: false };
      });
      setAnimalTypes(types);
    };
    fetchData();
  }, []);

  useEffect(() => {
    fetchDataAnimal(selectAnimalType);
  }, [selectAnimalType]);

  const handleSelect = useCallback(async (data: AnimalType, list: any) => {
    const listTypes = list.map((item: any) => {
      if (item.id == data.id) {
        item.checked = item.checked === false ? true : false;
      } else {
        item.checked = false;
      }
      return item;
    });
    setAnimalTypes(listTypes);
    setSelectAnimalType(data.id);
  }, []);

  function details(){
    navigation.navigate('ToAdoptDetails')
  }

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      style={styles.container}
    >
      <Background>
        <Header title="Adotar" isVisibleBack isVisibleLogout />
        <View>
          <FlatList
            data={animalTypes}
            horizontal
            style={styles.flatListTypeAnimal}
            renderItem={({ item }) => {
              return (
                <ButtonRound
                  cor={theme.colors.primary}
                  title={item.description}
                  checked={item.checked}
                  onPress={() => handleSelect(item, animalTypes)}
                />
              );
            }}
          />
        </View>

        <View>
          {loading ? (
            <Load />
          ) : (
            <FlatList
              data={animals}
              style={{ padding: 20 }}
              keyExtractor={(item) => item.id}
              renderItem={({ item }) => {
                return (
                  <Card data={item} onPress={details} />
                );
              }}
            />
          )}
        </View>
      </Background>
    </KeyboardAvoidingView>
  );
}
