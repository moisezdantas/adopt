import React from 'react';
import { useAuth } from '../../hook/auth'
import {
  View, Text
} from 'react-native';


export function Home(){

    const { user } = useAuth();

  return (
    <View>
        <Text>Home</Text>
    </View>
  );
}