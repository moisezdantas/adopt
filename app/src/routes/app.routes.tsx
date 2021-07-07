import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { theme } from "../global/styles/theme";

import { Home } from "../screens/Home";

const App = createStackNavigator();

export function AppRoutes() {
  return (
    <App.Navigator
      headerMode="none"
      screenOptions={{
        cardStyle: {
          backgroundColor: theme.colors.primary,
        },
      }}
    >
      <App.Screen name='Home' component={Home} />
    </App.Navigator>
  );
}
