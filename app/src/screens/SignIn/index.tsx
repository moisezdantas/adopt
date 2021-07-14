import React, { useRef } from "react";

import { Form } from "@unform/mobile";

import { FormHandles } from "@unform/core";

import {
  Text,
  KeyboardAvoidingView,
  View,
  TouchableOpacity,
  Platform,
  ScrollView,
} from "react-native";
import Input from "../../components/Input";
import { Button } from "../../components/Button";
import * as Yup from "yup";
import { useAuth } from "../../hook/auth";

import getValidationErrors from "../../utils/getValidationErrors";

import LogoSvg from "../../assets/animal.svg";

import { styles } from "./styles";

import { useNavigation } from "@react-navigation/native";
import { theme } from "../../global/styles/theme";

interface SignInFormData {
  email: string;
  password: string;
}

export function SignIn() {
  const { signIn } = useAuth();
  const formRef = useRef<FormHandles>(null);

  const navigation = useNavigation();

  async function handleSubmit(data: SignInFormData) {
    try {
      formRef.current?.setErrors({});

      const schema = Yup.object().shape({
        email: Yup.string()
          .required("E-mail é obrigatório")
          .email("Digite um e-mail válido"),
        password: Yup.string().required("Senha obrigatória"),
      });

      await schema.validate(data, {
        abortEarly: false,
      });

      await signIn({
        email: data.email,
        password: data.password,
      });
    } catch (error) {
      if (error instanceof Yup.ValidationError) {
        const errors = getValidationErrors(error);

        formRef.current?.setErrors(errors);

        return;
      }
    }
  }

  function signUp() {
    navigation.navigate("SignUp");
  }

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === "ios" ? "padding" : "height"}
      style={styles.container}
    >
      <ScrollView>
        <View style={styles.context}>
          <LogoSvg style={styles.image} />

          <Text style={styles.title}>Adopt</Text>
          <Text style={styles.subTitle}>
            {`Doe algum tempo da sua vida para que\r
precisa de você.`}
          </Text>

          <Form ref={formRef} onSubmit={handleSubmit}>
            <Input name="email" placeholder="Email" icon="mail" />
            <Input
              name="password"
              placeholder="Senha"
              icon="lock"
              secureTextEntry
            />
            <Button
              title="Login"
              cor={theme.colors.buttonPrimary}
              onPress={() => formRef.current?.submitForm()}
            />
          </Form>
        </View>
        <View style={styles.footer}>
          <Text style={styles.subTitleNoCount}>Não tem uma conta?</Text>

          <TouchableOpacity onPress={signUp}>
            <Text style={styles.linkRegister}> Criar conta </Text>
          </TouchableOpacity>
        </View>
      </ScrollView>
    </KeyboardAvoidingView>
  );
}
