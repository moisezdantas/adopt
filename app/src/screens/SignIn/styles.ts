import { theme } from './../../global/styles/theme';
import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  context: {
    justifyContent:'center',
    alignContent: 'center',
    padding: 20
  },
  image: {
   alignSelf: 'center',
   marginTop: 80,
  },
  title: {
    textAlign: 'center',
    fontFamily: theme.fonts.title900,
    color: theme.colors.black,
    fontSize: 35,
    marginBottom: 10
  },
  subTitle:{
    textAlign: 'center',
    fontFamily: theme.fonts.title500,
    color: theme.colors.black,
    fontSize: 16,
    lineHeight: 21,
    marginBottom: 25
  },
  subTitleNoCount: {
    textAlign: 'center',
    fontFamily: theme.fonts.text400,
    fontSize: 15
  },
  linkRegister: {
    textAlign: 'center',
    fontFamily: theme.fonts.text400,
    fontSize: 15
  },
  footer: {
    marginBottom: 10,
}
});