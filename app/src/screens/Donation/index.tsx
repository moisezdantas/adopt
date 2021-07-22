import React, { useRef, useState } from "react";
import {
  SafeAreaView,
  ScrollView,
  Text,
  View,
  TouchableOpacity,
  Modal,
  ActivityIndicator,

} from "react-native";
import { WebView } from "react-native-webview";
import Feather from "react-native-vector-icons/Feather";
import { styles } from "./styles";
import { Header } from "../../components/Header";
import { Background } from "../../components/Background";
import InputMask from "../../components/InputMask";

import { Form } from "@unform/mobile";
import { FormHandles } from "@unform/core";

import { useAuth } from "../../hook/auth";
import { Button } from "../../components/Button";
import { theme } from "../../global/styles/theme";
import * as Yup from "yup";
import getValidationErrors from "../../utils/getValidationErrors";

// const data = {
//   price: 1,
//   currency: "BRL",
//   description: "Doação",
// };
interface RegisterFriendFormData {
  price: string;
}

type DataProps = {
  price: string | number;
  description: string;
}

export function Donation() {
  const { user } = useAuth();
  const [showGateway, setShowGateway] = useState(false);
  const [prog, setProg] = useState(false);
  const formRef = useRef<FormHandles>(null);
  const [ payPal, setPayPal] = useState<DataProps>();

  const handleResponse = async (data: any) => {
    if (data.title === "success") {
      formRef.current?.reset();
    } else if (data.title === "cancel") {
      console.log("cancel");
    } else {
      return;
    }
  };

  async function handleSubmit(data: RegisterFriendFormData) {
    try {
      formRef.current?.setErrors({});
      const schema = Yup.object().shape({
        price: Yup.string().required("Digite uma valor para doação"),
      });

      await schema.validate(data, {
        abortEarly: false,
      });

      setPayPal({
        price: data.price,
        description: 'Doação'
      });

      setShowGateway(true);
      setProg(true);
    } catch (error) {
      if (error instanceof Yup.ValidationError) {
        const validationErrors = getValidationErrors(error);

        formRef.current?.setErrors(validationErrors);

        return;
      }
    }
  }

  return (
    <Background>
      <SafeAreaView style={{ flex: 1 }}>
        <ScrollView>
          <View style={styles.container}>
            <Header title="Doação" isVisibleBack isVisibleLogout />
            <View>
              <Text style={styles.title}>
                Sua doação irá ajudar muitos amigos.
              </Text>
            </View>
            <View style={styles.content}>
              <Form ref={formRef} onSubmit={handleSubmit}>
                <InputMask
                  name="price"
                  type="money"
                  placeholder="Digite o valor"
                  options={{
                    precision: 2,
                    separator: '.',
                    delimiter: ',',
                    unit: 'R$',
                    suffixUnit: ''
                  }}
                />
                <Button
                  onPress={() => formRef.current?.submitForm()}
                  cor={theme.colors.buttonSuccess}
                  title="Pay Using PayPal"
                />
              </Form>
            </View>
            {showGateway ? (
              <Modal
                visible={showGateway}
                onDismiss={() => setShowGateway(false)}
                onRequestClose={() => setShowGateway(false)}
                animationType={"fade"}
                transparent
              >
                <View style={styles.webViewCon}>
                  <View style={styles.wbHead}>
                    <TouchableOpacity
                      style={{ padding: 13 }}
                      onPress={() => setShowGateway(false)}
                    >
                      <Feather name={"x"} size={24} />
                    </TouchableOpacity>
                    <Text
                      style={{
                        flex: 1,
                        textAlign: "center",
                        fontSize: 16,
                        fontWeight: "bold",
                        color: "#00457C"
                      }}
                    >
                      PayPal GateWay
                    </Text>
                    <View style={{ padding: 13, opacity: prog ? 1 : 0 }}>
                      <ActivityIndicator
                        size={24}
                        color={theme.colors.buttonBlue}
                      />
                    </View>
                  </View>
                  <WebView
                    source={{
                      uri: `http://192.168.0.11:3001/?userId=${user.id}&description=${payPal?.description}&price=${payPal?.price}&token=${user.access_token}`,
                    }}
                    style={{ flex: 1 }}
                    onNavigationStateChange={(data) => handleResponse(data)}
                    injectedJavaScript={`document.f1.submit()`}
                  />
                </View>
              </Modal>
            ) : null}
          </View>
        </ScrollView>
      </SafeAreaView>
    </Background>
  );
}
