import { theme } from '../../global/styles/theme';
import { StyleSheet } from 'react-native'

export const styles = StyleSheet.create({
    container: {
        width: 132,
        height: 95,
        borderRadius: 10,
        flexDirection:'row',
        alignItems: 'center',
    },
    title:{
        flex: 1,
        color: theme.colors.buttonLabel,
        fontSize: 13,
        textAlign: 'center',
        fontWeight: 'bold',
        fontFamily: theme.fonts.title500,
    },
    iconWrapper: {
        width: 56,
        height: 56,
        justifyContent: 'center',
        alignItems: 'center',
        borderRightWidth: 1,
    }
})