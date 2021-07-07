import { theme } from './../../global/styles/theme';
import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
    container:{
        display: 'flex',
    },
    context: {
        width: '100%',
        paddingHorizontal: 12,
        paddingVertical: 16,
        borderRadius: 8,
        borderWidth: 2,
        flexDirection: 'row',  
    },
    input: {
        color: theme.colors.black,
        fontFamily: theme.fonts.text400,
        fontSize: 18,
    },
    icon:{
        marginRight: 16,
        color: theme.colors.secondary,
    },
    error:{
        fontFamily: theme.fonts.text400,
        fontSize: 15,
        color: theme.colors.red,
        textAlign: 'center',
        marginBottom: 2
    },
    red: {
        borderColor: theme.colors.red,
    },
    orange: {
        borderColor: theme.colors.secondary,
        marginBottom: 5,
    }
});