import React from "react";
import { RectButton, RectButtonProps} from "react-native-gesture-handler";

import {
  Text
} from "react-native";

import { styles} from './styles'

type Props = RectButtonProps & {
  title: string;
  cor: string;
  checked?: boolean;
};

export function ButtonRound({ title, cor, checked = false, ...rest }: Props) {
  return (
    <RectButton style={[styles.container, { backgroundColor : checked ? '#C0C0D9' : `${cor}`}]} {...rest}>
      <Text style={styles.title}>{title}</Text>
    </RectButton>
  );
}
