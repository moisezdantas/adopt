import { theme } from '../../global/styles/theme';
import { StyleSheet } from 'react-native';
import { getStatusBarHeight } from 'react-native-iphone-x-helper'

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop:  25,
  },
  view:{
    backgroundColor: theme.colors.purple,
    color: theme.colors.white,
    marginBottom: 70
  },
  image: {
    width: 259,
    height: 189,
    marginTop: 80,
    borderRadius: 15,
    alignSelf: 'center'
  },
  content: {
    marginTop: 50,
    justifyContent:'center',
    alignContent: 'center',
    alignItems: 'center',
    paddingLeft: 30,
    paddingRight: 30
  },
  row:{
    flexDirection: 'row',
    paddingLeft: 30,
    paddingRight: 30,
    marginBottom: 16,
  },
  description: {
    marginTop: 30,
    fontSize: 20,
    fontFamily: theme.fonts.title500,
    color: theme.colors.white,
    marginBottom: 30
  },
  screenWrapper: {
    flex: 1,
    width: undefined,
    height: undefined,
    justifyContent: 'center',
    alignItems: 'center',
  },
});