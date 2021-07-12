import React from "react";

import { View, Image, Text } from "react-native";
import { RectButton, RectButtonProps } from 'react-native-gesture-handler';
import { theme } from "../../global/styles/theme";

import { Address } from "../../interfaces/address";
import { api } from "../../services/api";
import { styles } from "./styles";


type Props = {
  id: string;
  name: string;
  address: Address;
  note: string;
  imageUrl: string;
};

type PropsAnimal = RectButtonProps & {
  data: Props;
} 

export function Card({ data, ...rest }: PropsAnimal) {
  const urlImage = `${api.defaults.baseURL}/storage/view/${data.imageUrl}`;

  return (
    <RectButton 
    rippleColor={theme.colors.purple}
    {...rest} 
    >
    <View style={styles.container}>
      <View style={styles.card}>
        <Image source={{ uri: urlImage }} style={styles.image} />
        <View style={styles.cardContext}>
          <Text style={styles.cardTitle}>{data.name}</Text>
          <Text
            style={styles.cardCity}
          >{`${data.address.city} - ${data.address.district}`}</Text>
          <Text style={styles.cardDescription}>{data.note}</Text>
        </View>
      </View>
    </View>
    </RectButton>
  );
}
