import React, { useState, useCallback, forwardRef } from "react";
import { ForwardedRef } from "react";
import { View, Text} from "react-native";

import {
  TextInputMask,
  TextInputMaskProps,
  TextInputMaskTypeProp,
} from "react-native-masked-text";

import Input from "../Input";

interface InputProps extends TextInputMaskProps {
  name: string;
  placeholder: string;
  icon?: string;
  type: TextInputMaskTypeProp;

}

const InputMask = (
  { type, value, ...rest }: InputProps,
  inputRef: ForwardedRef<HTMLInputElement>
) => {
  const [text, setText] = useState("");

  const [rawText, setRawText] = useState("");

  const handleChangeText = useCallback((maskedText, unmaskedText) => {
    setText(maskedText);

    setRawText(unmaskedText);
  }, []);

  return (
   
      <TextInputMask
        type={type}
        includeRawValueInChangeText
        value={text}
        onChangeText={handleChangeText}
        customTextInput={Input}
        customTextInputProps={{
          ref: inputRef,
          rawText,
          onInitialData: setText,
        }}
        {...rest}
      />
    
  );
};

export default forwardRef(InputMask);
