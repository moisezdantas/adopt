import React from "react";

import { View, Image, Text } from "react-native";
import { RectButton, RectButtonProps } from "react-native-gesture-handler";
import { theme } from "../../global/styles/theme";

import { Address } from "../../interfaces/address";
import { Breed } from "../../interfaces/breed";
import { api } from "../../services/api";
import { styles } from "./styles";

type Props = {
  id?: string | number;
  name: string;
  address: Address;
  note?: string;
  imageUrl: string;
  breed: Breed;
};

type PropsAnimal = RectButtonProps & {
  data: Props;
};

export function Card({ data, ...rest }: PropsAnimal) {
  const urlImage = `${api.defaults.baseURL}/storage/view/${data.imageUrl}`;

  return (
    <RectButton rippleColor={theme.colors.purple} {...rest}>
      <View style={styles.container}>
        <View style={styles.card}>
          <Image source={{ uri: urlImage }} style={styles.image} />
          <View style={styles.cardContext}>
            <Text style={styles.cardTitle}>{data.name}</Text>
            <Text
              style={styles.cardCity}
            >{`${data.address.city} - ${data.address.district}`}</Text>
            <View style={{flexDirection: 'row'}}>
              <Text style={styles.cardDescription}>
                {data.breed.description}
              </Text>
            </View>
          </View>
        </View>
      </View>
    </RectButton>
  );
}
