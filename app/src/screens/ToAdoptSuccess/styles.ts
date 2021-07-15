import { theme } from '../../global/styles/theme';
import { StyleSheet } from 'react-native';
import { getStatusBarHeight } from 'react-native-iphone-x-helper'

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop:  25,
   
  },
  view:{
    color: theme.colors.white,
    marginBottom: 70
  },
  image: {
    width: 259,
    height: 189,
    marginTop: 170,
    borderRadius: 15,
    alignSelf: 'center'
  },
  content: {
    marginTop: 20,
    justifyContent:'center',
    alignContent: 'center',
    alignItems: 'center',
    paddingLeft: 30,
    paddingRight: 30,
    backgroundColor: theme.colors.white,
    marginBottom: 30
  },
  row:{
    flexDirection: 'row',
    paddingLeft: 30,
    paddingRight: 30,
    marginBottom: 16,
  },
  description: {
    marginTop: 80,
    fontSize: 20,
    fontFamily: theme.fonts.title500,
    color: theme.colors.white,
    marginBottom: 30,
    textAlign: 'center'
  },
  screenWrapper: {
    backgroundColor: theme.colors.purple,
    flex: 1,
    width: 400,
    height: 540,
    justifyContent: 'center',
    alignItems: 'center',
    
  },
});