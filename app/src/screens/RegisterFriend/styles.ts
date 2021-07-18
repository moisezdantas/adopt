import { theme } from './../../global/styles/theme';
import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  content: {
    flex: 1,
    width: '100%',
    
    justifyContent:'center',
    padding:20
  },
  upload: {
    marginBottom: 20,
    alignItems: 'center',
    justifyContent: 'center'
  },
  erroUpload:{
    justifyContent: 'center', 
    alignContent:'center', 
    alignSelf:'center', 
    color: theme.colors.red,
    fontFamily: theme.fonts.text400,
    fontSize: 15,
    marginBottom: 10
  },
  image: {
    width: 150, 
    height: 150, 
    borderRadius: 70,
  }
});