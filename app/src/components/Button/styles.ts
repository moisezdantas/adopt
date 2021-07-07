import { theme } from '../../global/styles/theme';
import { StyleSheet } from 'react-native'

export const styles = StyleSheet.create({
    container: {
        width: '100%',
        height: 56,
        borderRadius: 8,
        flexDirection:'row',
        alignItems: 'center'
    },
    title:{
        flex: 1,
        color: theme.colors.buttonLabel,
        fontSize: 18,
        textAlign: 'center',
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