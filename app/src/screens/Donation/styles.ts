import { theme } from './../../global/styles/theme';
import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
    webViewCon: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
    },
    wbHead: {
        flexDirection: 'row',
        alignItems: 'center',
        backgroundColor: '#f9f9f9',
        zIndex: 25,
        elevation: 2,
    },
    content: {
        flex: 1,
        width: '100%',

        justifyContent: 'center',
        padding: 20
    },
    title: {
        marginTop: 40,
        fontSize: 30,
        textAlign: 'center',
        fontFamily: theme.fonts.title700,
        color: theme.colors.gray
    }
});