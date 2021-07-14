import React, { ReactNode } from "react";
import { LinearGradient } from "expo-linear-gradient";
import { BorderlessButton } from "react-native-gesture-handler";
import { View, Text, Image, Dimensions } from "react-native";

import ArrowPng from "../../assets/arrow.png";
import ArrowLeftPng from "../../assets/left.png";
import ArrowLeftWPng from "../../assets/left_w.png";
import ArrowWPng from "../../assets/arrow_w.png";

import { styles } from "./styles";
import { theme } from "../../global/styles/theme";
import { useNavigation } from "@react-navigation/native";
import { useAuth } from "../../hook/auth";
import { Background } from "../Background";

type Props = {
  title: string;
  isVisibleBack?: boolean;
  isVisibleLogout?: boolean;
  cor?: string;
  isDefaultButton?: boolean
};

export function Header({
  title,
  isVisibleBack = false,
  isVisibleLogout = false,
  cor = theme.colors.black,
  isDefaultButton = true
}: Props) {
  const { singOut } = useAuth();

  const navigation = useNavigation();

  function handleGoBack() {
    navigation.goBack();
  }

  return (
    <View style={styles.container}>
      {isVisibleBack && (
        <BorderlessButton onPress={handleGoBack}>
          <Image source={isDefaultButton ? ArrowPng : ArrowLeftWPng} />
        </BorderlessButton>
      )}

      <Text style={[styles.title, { color: cor }]}>{title}</Text>

      {isVisibleLogout ? (
        <BorderlessButton onPress={() => singOut()}>
          <Image source={isDefaultButton ? ArrowLeftPng : ArrowWPng} />
        </BorderlessButton>
      ) : (
        <View />
      )}
    </View>
  );
}
//
