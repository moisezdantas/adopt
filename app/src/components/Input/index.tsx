import React, { useRef, useEffect, useCallback } from 'react';
import { TextInput, TextInputProps, View, Text } from 'react-native';
import { useField } from '@unform/core';
import MaterialCommunityIcons from 'react-native-vector-icons/MaterialCommunityIcons';


import {styles} from './styles'
import { theme } from '../../global/styles/theme';

interface InputProps extends TextInputProps {
  name: string;
  placeholder: string;
  icon?: string;
  rawText?: string;
}

interface InputReference extends TextInput {
  value: string;
}

const Input: React.FC<InputProps> = ({
  name,
  placeholder,
  icon,
  onChangeText,
  rawText,
  ...rest
}) => {
  const inputRef = useRef<InputReference>(null);

  const { fieldName, registerField, defaultValue = '', error } = useField(name);

  useEffect(() => {
    if (inputRef.current) inputRef.current.value = defaultValue;
  }, [defaultValue]);

  useEffect(() => {
    registerField<string>({
      name: fieldName,
      ref: inputRef.current,
      getValue() {
        if (rawText) return rawText;

        if (inputRef.current) return inputRef.current.value;
        
        return '';
      },
      setValue(_, value) {
        if (inputRef.current) {
          inputRef.current.setNativeProps({ text: value });
          inputRef.current.value = value;
        }
      },
      clearValue() {
        if (inputRef.current) {
          inputRef.current.setNativeProps({ text: '' });
          inputRef.current.value = '';
        }
      },
    });
  }, [fieldName, registerField, rawText]);

  const handleChangeText = useCallback((value: string) => {
  

      if (inputRef.current) inputRef.current.value = value

      if (onChangeText) onChangeText(value)
    },
    [onChangeText],
  );

  return (
    <View style={styles.container}>
      <View style={[styles.context, , error ? styles.red : styles.orange]}>
          {icon && (
            <MaterialCommunityIcons
            style={styles.icon}
            name={icon} 
            size={24} 
          /> 
          )}
          
          <TextInput
            style={styles.input}
            ref={inputRef}
            onChangeText={handleChangeText}
            defaultValue={defaultValue}
            placeholder={placeholder}
            placeholderTextColor={theme.colors.placeholder}
            {...rest}
          />
          
        </View>
        { error && <Text style={styles.error}>{error}</Text> }
        
    </View>
  );
};
export default Input;