import React, { useRef, useState } from "react";

import { View, ScrollView, Alert, Text } from "react-native";
import { useNavigation } from "@react-navigation/native";

import { Form } from "@unform/mobile";
import { FormHandles, Scope } from "@unform/core";

import { styles } from "./styles";
import { theme } from "../../global/styles/theme";

import { Background } from "../../components/Background";
import Input from "../../components/Input";

import InputMask from "../../components/InputMask";
import { Button } from "../../components/Button";
import { Header } from "../../components/Header";

import { updatePerson } from "../../services/personService";

import getValidationErrors from "../../utils/getValidationErrors";
import * as Yup from "yup";
import { Address } from "../../interfaces/address";
import { useAuth } from "../../hook/auth";

interface RegisterFriendFormData {
  email: string;
  mobilePhone: string;
  address: Address;
}

export function ProfileUpdate() {
  const { user } = useAuth();
  const formRef = useRef<FormHandles>(null);
  const [loading, setLoading] = useState(false);

  const navigation = useNavigation();

  async function handleSubmit(data: RegisterFriendFormData) {
    try {
      formRef.current?.setErrors({});

      const schema = Yup.object().shape({
        mobilePhone: Yup.string().required("Telefone é obrigatório"),
        address: Yup.object().shape({
          cep: Yup.string().required("Cep é obrigatório"),
          street: Yup.string().required("Endereço é obrigatório"),
          number: Yup.string().required("Número é obrigatório"),
          district: Yup.string().required("Bairro é obrigatório"),
          city: Yup.string().required("Cidade é obrigatório"),
        }),
      });

      await schema.validate(data, {
        abortEarly: false,
      });

      setLoading(true);
      await updatePerson({
        mobilePhone: data.mobilePhone,
        user,
        address: [data.address],
      });

      setLoading(false);

      Alert.alert("Info", "Dados atualizado com sucesso!");

      navigation.navigate("Home");
    } catch (error) {
      if (error instanceof Yup.ValidationError) {
        const validationErrors = getValidationErrors(error);

        formRef.current?.setErrors(validationErrors);

        return;
      }
    }
  }

  function fetchDataCep(e: any) {
    const cep = e.target.value;
    if (cep.length === 9) {
      setLoading(true);
      fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then((res) => res.json())
        .then((data) => {
          formRef.current?.setFieldValue("address.city", data.localidade);
          formRef.current?.setFieldValue("address.street", data.logradouro);
          formRef.current?.setFieldValue("address.district", data.bairro);
        })
        .finally(() => {
          setLoading(false);
        });
    }
  }

  return (
    <Background>
      <View style={styles.container}>
        <Header title={`Atualizar Dados`} isVisibleBack isVisibleLogout />

        <ScrollView>
          <View style={styles.content}>
            <View style={styles.user}>
              <Text style={styles.greeting}>Olá,</Text>
              <Text style={styles.username}>{user.firstName}</Text>
            </View>

            <Form ref={formRef} onSubmit={handleSubmit}>
              <InputMask
                name="mobilePhone"
                type="cel-phone"
                placeholder="Digite seu telefone"
                onBlur={(e: any) => fetchDataCep(e)}
              />
              <Scope path="address">
                <InputMask
                  name="cep"
                  type="zip-code"
                  placeholder="Digite seu cep"
                  onBlur={(e: any) => fetchDataCep(e)}
                />
                <Input name="street" placeholder="Digite seu endereço" />
                <InputMask
                  name="number"
                  type="only-numbers"
                  placeholder="Digite o número"
                />
                <Input name="complement" placeholder="Digite complemento" />
                <Input name="district" placeholder="Digite seu bairro" />
                <Input name="city" placeholder="Digite seu cidade" />
              </Scope>
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
