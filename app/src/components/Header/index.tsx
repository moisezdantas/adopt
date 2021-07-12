import React, { ReactNode } from "react";
import { LinearGradient } from "expo-linear-gradient";
import { BorderlessButton } from "react-native-gesture-handler";
import { View, Text, Image } from "react-native";

import ArrowPng from "../../assets/arrow.png";
import ArrowLeftPng from "../../assets/left.png";

import { styles } from "./styles";
import { theme } from "../../global/styles/theme";
import { useNavigation } from "@react-navigation/native";
import { useAuth } from "../../hook/auth";
import { Background } from "../Background";

type Props = {
  title: string;
  action?: ReactNode;
  isVisibleBack?: boolean;
  isVisibleLogout?: boolean;
};

export function Header({
  title,
  action,
  isVisibleBack = false,
  isVisibleLogout = false,
}: Props) {
  const { primary } = theme.colors;

  const { singOut } = useAuth();

  const navigation = useNavigation();

  function handleGoBack() {
    navigation.goBack();
  }

  return (
    <View style={styles.container}>
      {isVisibleBack && (
        <BorderlessButton onPress={handleGoBack}>
          <Image source={ArrowPng} />
        </BorderlessButton>
      )}

      <Text style={styles.title}>{title}</Text>

      {isVisibleLogout && (
        <BorderlessButton onPress={() => singOut()}>
          <Image source={ArrowLeftPng} />
        </BorderlessButton>
      )}
    </View>
  );
}
