import React from "react";
import { RectButton, RectButtonProps } from "react-native-gesture-handler";

import { Text, ActivityIndicator, View } from "react-native";

import { styles } from "./styles";
import { Load } from "../Load";
import { theme } from "../../global/styles/theme";

type Props = RectButtonProps & {
  title: string;
  cor: string;
  loading?: boolean;
};

export function Button({ title, cor, loading, ...rest }: Props) {
  return (
    <View style={styles.container}>
      {loading ? (
        <View
          style={{
            flex:1,
            alignContent: "center",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <ActivityIndicator size="large" color={theme.colors.purple} />
        </View>
      ) : (
        <RectButton
          style={[styles.container, { backgroundColor: `${cor}` }]}
          {...rest}
        >
          <Text style={styles.title}>{title}</Text>
        </RectButton>
      )}
    </View>
  );
}
