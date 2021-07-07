import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { theme } from "../global/styles/theme";

import { SignIn } from "../screens/SignIn";
import { SignUp } from "../screens/SignUp";

const App = createStackNavigator();

export function AuthRoutes() {
  return (
    <App.Navigator
      headerMode="none"
      screenOptions={{
        cardStyle: {
          backgroundColor: theme.colors.primary,
        },
      }}
    >
      <App.Screen name='SignIn' component={SignIn} />
      <App.Screen name='SignUp' component={SignUp} />
    </App.Navigator>
  );
}
