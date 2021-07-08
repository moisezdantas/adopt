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

type Props = {
  title: string;
  action?: ReactNode;
  isVisibleBack?: boolean;
};

export function Header({ title, action, isVisibleBack = false }: Props) {
  const { primary } = theme.colors;

  const { singOut } = useAuth();

  const navigation = useNavigation();

  function handleGoBack() {
    navigation.goBack();
  }
  
  return (
    <LinearGradient style={styles.container} colors={[primary, primary]}>
      {isVisibleBack && (
        <BorderlessButton onPress={handleGoBack}>
          <Image source={ArrowPng} />
        </BorderlessButton>
      )}

      <Text style={styles.title}>{title}</Text>
      {action ? <View>{action}</View> : <View style={{ width: 24 }} />}

      <BorderlessButton onPress={() => singOut()}>
          <Image source={ArrowLeftPng} />
        </BorderlessButton>
    </LinearGradient>
  );
}
