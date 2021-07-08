import { StyleSheet } from 'react-native';
import { getStatusBarHeight } from 'react-native-iphone-x-helper';

export const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 33
    },
    profile: {
      alignItems: 'center'
    },
    headers: {
        width: '100%',
        paddingHorizontal: 24,
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginBottom: 42,
    },
    image: {
        marginTop: 20,
        alignSelf: 'center',
        justifyContent: 'center',
        alignItems: 'center'
    },
    grupoButton: {
        marginTop: 50,
        alignSelf: 'center',
    },
    item: {
        alignItems: "center",
        marginHorizontal: 5,
        margin: 4,
        padding: 5,
      },
      text: {
        color: "#333333"
      }

});