import { theme } from './../../global/styles/theme';
import { StyleSheet} from 'react-native';

export const styles = StyleSheet.create({
    container:{
        flexDirection: 'row',
    },
    profile: {
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'space-around'
    },
    user: {
      flexDirection: 'row',
    },
    greeting: {
        fontFamily: theme.fonts.title500,
        fontSize: 24,
        color: theme.colors.black,
        marginRight: 6
    },
    username:{
        fontFamily: theme.fonts.title700,
        fontSize: 24,
        color: theme.colors.black,
    }
})