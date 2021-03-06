import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import { theme } from "../global/styles/theme";

import { Home } from "../screens/Home";
import { ToAdopt } from "../screens/ToAdopt";
import { Donation } from "../screens/Donation";
import { RegisterFriend } from "../screens/RegisterFriend";
import { ToAdoptDetails } from "../screens/ToAdoptDetails";
import { ToAdoptSuccess } from "../screens/ToAdoptSuccess";
import { ProfileUpdate } from "../screens/ProfileUpdate";

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
      <App.Screen name='ToAdopt' component={ToAdopt} />
      <App.Screen name='Donation' component={Donation} />
      <App.Screen name='RegisterFriend' component={RegisterFriend} />
      <App.Screen name='ToAdoptDetails' component={ToAdoptDetails} />
      <App.Screen name='ToAdoptSuccess' component={ToAdoptSuccess} />
      <App.Screen name='ProfileUpdate' component={ProfileUpdate} />
    </App.Navigator>
  );
}
