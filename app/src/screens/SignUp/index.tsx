import React, { useRef, useState } from "react";

import {
  View,
  ScrollView,
  Alert,
} from "react-native";

import { Form } from "@unform/mobile";
import { FormHandles } from "@unform/core";

import { Background } from "../../components/Background";
import Input from "../../components/Input";
import SelectInput from "../../components/SelectInput";
import { Button } from "../../components/Button";
import { theme } from "../../global/styles/theme";
import { Header } from "../../components/Header";
import { CreatePerson } from "../../services/request";
import { useNavigation } from "@react-navigation/native";

import { styles } from "./styles";
import * as Yup from "yup";
import getValidationErrors from "../../utils/getValidationErrors";
import InputMask from "../../components/InputMask";

interface SignUpFormData {
  name: string;
  email: string;
  mobilePhone: string;
  typeGender: string;
  password: string;
  confirmPassword: string;
}

export function SignUp() {
  const formRef = useRef<FormHandles>(null);
  const [loading, setLoading] = useState(false);
  const navigation = useNavigation();

  const options = [
    { value: "1", label: "Homem", key: 1 },
    { value: "2", label: "Mulher", key: 2 },
  ];

  async function handleSubmit(data: SignUpFormData) {
    try {
      formRef.current?.setErrors({});

      const schema = Yup.object().shape({
        name: Yup.string().required("Nome é obrigatório"),
        email: Yup.string()
          .required("E-mail é obrigatório")
          .email("Digite um e-mail válido"),
        mobilePhone: Yup.string().required("Telefone é obrigatório"),
        typeGender: Yup.string().required("Sexo é obrigatório"),
        password: Yup.string().required("Senha é obrigatória"),
        confirmPassword: Yup.string()
          .required("Confirmar senha é obrigatório")
          .oneOf(
            [Yup.ref("password"), null],
            "Senha e Confirmar senha são diferentes"
          ),
      });

      await schema.validate(data, {
        abortEarly: false,
      });
  
      setLoading(true);
      CreatePerson({
        typeGender: Number(data.typeGender),
        name: data.name,
        mobilePhone: data.mobilePhone,
        user: {
          email: data.email,
          password: data.password,
          roles: [
            {
              id: 1,
            },
          ],
        },
      })
        .then((response) => {
           Alert.alert("Info", 'Cadastro efetuado com sucesso!');
          navigation.goBack();
        })
        .catch((error) => {
          Alert.alert("Erro", error.response.data.message);
        })
        .finally(() => {
          setLoading(false);
        });
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
      <View style={styles.container}>
        <Header title="Criar Conta" isVisibleBack />
        <ScrollView>
          <View style={styles.content}>
            <Form ref={formRef} onSubmit={handleSubmit}>
              <Input name="name" placeholder="Digite seu nome" />
              <Input name="email" placeholder="Digite seu e-mail" />
              <SelectInput
                name="typeGender"
                items={options}
                placeHolder="Selecione uma sexo"
              />
              <InputMask 
                type='cel-phone'
                name="mobilePhone" 
                placeholder="Digite seu celular" />
              <Input
                name="password"
                placeholder="Digite sua senha"
                secureTextEntry
              />
              <Input
                name="confirmPassword"
                placeholder="Confirmar senha"
                secureTextEntry
              />
              <Button
                title="Cadastrar"
                cor={theme.colors.buttonSuccess}
                onPress={() => formRef.current?.submitForm()}
                loading={loading}
              />
            </Form>
          </View>
        </ScrollView>
      </View>
    </Background>
  );
}
