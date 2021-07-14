import { theme } from './../../global/styles/theme';
import { StyleSheet } from 'react-native';
import { getStatusBarHeight } from 'react-native-iphone-x-helper'

export const styles = StyleSheet.create({
  container: {
    width: '100%',
    paddingTop: getStatusBarHeight() + 30,
    flexDirection: 'row',
    justifyContent: 'space-around'
  },
  title: {
    textAlign: 'center',
    fontFamily: theme.fonts.title500,
    fontSize: 25,
    color: theme.colors.black
  }
});
