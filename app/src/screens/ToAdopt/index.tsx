import React, { useState, useEffect, useCallback} from "react";
import { View, FlatList } from "react-native";

import { theme } from "../../global/styles/theme";

import { styles } from "./styles";
import { Background } from "../../components/Background";

import { useNavigation } from "@react-navigation/native";
import { Header } from "../../components/Header";
import { Card } from "../../components/Card";
import { ButtonRound } from "../../components/ButtonRound";
import { fetchAnimalType } from '../../services/animalTypeService'
import { fetchAnimal } from "../../services/animalService";
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

  const navigation = useNavigation();

  const fetchDataAnimal = useCallback(async (animalTypeId?: number) => {
    setLoading(true);
    const data = await fetchAnimal({
      animalTypeId: animalTypeId === 0 ? 0 : animalTypeId,
    });
    setAnimals(data?.content);
    setLoading(false);
  }, []);

  const fetchData = async () => {
    const response = await fetchAnimalType();
    const types = response.map((item) => {
      return { ...item, checked: false };
    });
    setAnimalTypes(types);
  };

  useEffect(() => {    
    fetchData();
  }, []);

  useEffect(() => {
    fetchDataAnimal(selectAnimalType);
  }, [selectAnimalType]);

  useEffect(() => {
    const unsubscribe = navigation.addListener('focus', () => {
      fetchData();
      fetchDataAnimal()
    });

    return unsubscribe;
  }, [navigation]);


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

  async function details(data: Animal) {
    navigation.navigate("ToAdoptDetails", { data });
    
  }

  return (
    <Background>
      <Header title="Adotar" isVisibleBack isVisibleLogout />
      <View style={styles.typeAnimal}>
        <FlatList
          data={animalTypes}
          horizontal
          keyExtractor={(item) => item.id.toString()}
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
      <View style={styles.animal}>
          {loading ? (
            <Load />
          ) : (
            <FlatList
              data={animals}
              keyExtractor={(item) => item.id}
              renderItem={({ item }) => {
                return <Card data={item} onPress={() => details(item)} />;
              }}
            />
          )}
        </View>
    </Background>
  );
}

