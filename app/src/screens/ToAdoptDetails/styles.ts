import { theme } from './../../global/styles/theme';
import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  view:{
    height: 231,
    backgroundColor: theme.colors.purple,
    color: theme.colors.white,
    marginBottom: 100
  },
  image: {
    width: 259,
    height: 189,
    marginTop: 40,
    borderRadius: 15,
    alignSelf: 'center'
  },
  content: {
    justifyContent:'center',
    alignContent: 'center',
    alignItems: 'center',
  },
  city: {
    fontSize: 14,
    fontFamily: theme.fonts.text400,
    marginBottom: 10
  },
  icon:{
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 16
  },
  male: {
    fontSize: 16,
    fontFamily: theme.fonts.text400,
  },
  title: {
    fontSize: 24,
    fontFamily: theme.fonts.title700,
    marginBottom: 10
  },
  row:{
    flexDirection: 'row',
    paddingLeft: 20,
    paddingRight: 20,
    marginBottom: 16,
  },
  description: {
    fontSize: 16,
    fontFamily: theme.fonts.text400,
    color: '#9e9e9e',
  }
});