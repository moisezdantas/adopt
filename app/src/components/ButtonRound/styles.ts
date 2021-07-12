import { theme } from '../../global/styles/theme';
import { StyleSheet } from 'react-native'

export const styles = StyleSheet.create({
    container: {
        width: 80,
        height: 35,
        borderRadius: 16,
        flexDirection:'row',
        alignItems: 'center',
        backgroundColor: '#F5F5FA'
    },
    title:{
        flex: 1,
        color: '#7878AB',
        fontSize: 14,
        textAlign: 'center',
        fontFamily: theme.fonts.title700,
    }
})