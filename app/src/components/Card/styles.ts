import { theme } from './../../global/styles/theme';
import { StyleSheet, Dimensions } from 'react-native';
import { borderRadius } from 'react-select/src/theme';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  card: {
    height: 130,
    marginBottom: 20,
    backgroundColor: "#F5F5FA",
    padding: 20,
    flexDirection: 'row',
    borderRadius: 16,
  },
  image: {
    width: 112,
    height: 88,
    borderRadius: 15,
    backgroundColor: theme.colors.white
  },
  cardContext: {
     paddingLeft: 10
  },
  cardTitle: {
    fontFamily:  theme.fonts.title500,
    fontSize: 20,
    marginBottom: 5
  },
  cardCity: {
    fontFamily: theme.fonts.title500,
    fontSize: 13,
    color: '#828282'
  },
  cardDescription: {
    width: '80%',
    fontFamily: theme.fonts.text400,
    fontSize: 14,
    color: '#4F4F4F',
  }
});

