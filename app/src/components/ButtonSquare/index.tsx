import React from "react";
import { RectButton, RectButtonProps } from "react-native-gesture-handler";

import { Text } from "react-native";

import { styles } from "./styles";

type Props = RectButtonProps & {
  title: string;
  cor: string;
};

export function ButtonSquare({ title, cor, ...rest }: Props) {
  return (
    <RectButton
      style={[styles.container, { backgroundColor: `${cor}` }]}
      {...rest}
    >
      <Text style={styles.title}>{title}</Text>
    </RectButton>
  );
}
