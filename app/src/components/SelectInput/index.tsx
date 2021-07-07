import React, { useRef, useEffect, useState } from "react";
import Picker, { PickerSelectProps } from "react-native-picker-select";
import { useField } from "@unform/core";
import { View, Text } from "react-native";
import MaterialCommunityIcons from "react-native-vector-icons/MaterialCommunityIcons";

import { styles } from "./styles";
import { theme } from "../../global/styles/theme";

interface Props extends Omit<PickerSelectProps, "onValueChange"> {
  name: string;
  icon?: string;
  placeHolder: string;
}

const Input: React.FC<Props> = ({
  name,
  items,
  icon,
  placeHolder,
  ...rest
}) => {
  const pickerRef = useRef(null);

  const { fieldName, registerField, defaultValue = "", error } = useField(name);

  const [selectedValue, setSelectedValue] = useState(defaultValue);

  useEffect(() => {
    registerField({
      name: fieldName,
      ref: pickerRef.current,
      getValue: (ref) => {
        return ref.props.value || "";
      },
      clearValue: (ref) => {
        ref.props.onValueChange(ref.props.placeholder.value);
      },
      setValue: (_, value: string) => {
        setSelectedValue(value);
      },
    });
  }, [fieldName, registerField]);

  // inputAndroid: { },

  return (
    <View style={styles.container}>
      <View style={[styles.context, , error ? styles.red : styles.orange]}>
        {icon && (
          <MaterialCommunityIcons style={styles.icon} name={icon} size={24} />
        )}

        <Picker
          ref={pickerRef}
          value={selectedValue}
          onValueChange={setSelectedValue}
          items={items}
          useNativeAndroidPickerStyle={false}
          placeholder={{label: placeHolder}}
         
          style={{
            inputAndroid: {
              paddingRight: 40,
              color: theme.colors.black,
              fontFamily: theme.fonts.text400,
              fontSize: 18
            },
            placeholder: {
              color: theme.colors.placeholder,
              fontFamily: theme.fonts.text400,
            }
           
          }}
          {...rest}
        />
      </View>
      {error && <Text style={styles.error}>{error}</Text>}
    </View>
  );
};
export default Input;
