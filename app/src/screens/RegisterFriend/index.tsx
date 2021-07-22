import React, { useEffect, useRef, useState } from "react";

import {
  View,
  ScrollView,
  TouchableOpacity,
  Platform,
  Image,
  Text,
  Alert
} from "react-native";
import { useNavigation } from "@react-navigation/native";

import * as ImagePicker from "expo-image-picker";

import { Form } from "@unform/mobile";
import { FormHandles, Scope } from "@unform/core";

import { styles } from "./styles";
import { theme } from "../../global/styles/theme";

import { Background } from "../../components/Background";
import Input from "../../components/Input";
import SelectInput from "../../components/SelectInput";
import InputMask from "../../components/InputMask";
import { Button } from "../../components/Button";
import { Header } from "../../components/Header";
import ProfileSVG from "../../assets/profile.svg";

import { fetchBreeds } from "../../services/breedService";
import { fetchUpload } from "../../services/imageService";
import { createAnimal } from "../../services/animalService";

import getValidationErrors from "../../utils/getValidationErrors";
import * as Yup from "yup";
import { Item } from "react-native-picker-select";
import { Address } from "../../interfaces/address";

interface RegisterFriendFormData {
  name: string;
  typeGender: string;
  breed: string;
  deficiency: string;
  year: string;
  address: Address;
  note: string;
}
interface ImageProps {
  cancelled: boolean;
  height: number;
  type: string | undefined;
  uri: string;
  fileName: string;
}

export function RegisterFriend() {
  const formRef = useRef<FormHandles>(null);
  const [breedsOptions, setBreedsOptions] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [resultImage, setResultImage] = useState<ImageProps>();
  const [erroImage, setErroImage] = useState(false);
  const navigation = useNavigation();

  const options = [
    { value: "2", label: "Fêmea", key: 1 },
    { value: "1", label: "Macho", key: 2 },
  ];

  useEffect(() => {
    (async () => {
      if (Platform.OS !== "web") {
        const { status } =
          await ImagePicker.requestMediaLibraryPermissionsAsync();
        if (status !== "granted") {
          alert("Nós precisamos dessa permissão.");
        }
      }
    })();
  }, []);

  const uploadImage = async () => {
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3],
      quality: 1,
    });

    if (!result.cancelled) {
      const img = result.uri.split("/");
      const fileName = img[img.length - 1];
      setResultImage({
        cancelled: result.cancelled,
        height: result.height,
        type: result.type,
        uri: result.uri,
        fileName: fileName,
      });
    }
  };

  async function fetchBreed() {
    const data = await fetchBreeds();

    const options = data.map((item) => ({
      value: item.id,
      key: item.id,
      label: item.name,
    }));
    setBreedsOptions(options);
  }

  useEffect(() => {
    fetchBreed();
  }, []);

  async function fetchUploadImage() {
    const form_data = new FormData();

    var myImage = {
      uri:
        Platform.OS === "android"
          ? resultImage?.uri
          : resultImage?.uri.replace("file://", ""),
      name: resultImage?.fileName,
      type: "image/jpg",
    };

    form_data.append("file", myImage as any);

    const response =  await fetchUpload({ data: form_data });

    return response.imgUrl;
  }

  async function handleSubmit(data: RegisterFriendFormData) {
    try {
      formRef.current?.setErrors({});

      if(!resultImage?.uri){
        setErroImage(true)
      }else{
        setErroImage(false)
      }

      const schema = Yup.object().shape({
        name: Yup.string().required("Nome é obrigatório"),
        typeGender: Yup.string().required("Sexo é obrigatório"),
        breed: Yup.string().required("Raça é obrigatório"),
        deficiency: Yup.string().required("Deficiência é obrigatória"),
        year: Yup.string().required("Idade do animal"),
        note: Yup.string().required("Descrição é obrigatório"),
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

      const imgUrl = await fetchUploadImage();

      await createAnimal({
        address: data.address,
        breed: {
          id: data.breed
        },
        deficiency: data.deficiency,
        imageUrl: imgUrl,
        note: data.note,
        name: data.name,
        typeAnimalGender: data.typeGender,
        year: data.year
      
      })

      setLoading(false);

      Alert.alert('Info', 'Amigo(a) cadastrado com sucesso!');

      navigation.navigate('Home');

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
        <Header title="Cadastrar um Amigo(a)" isVisibleBack isVisibleLogout />

        <ScrollView>
          <View style={styles.content}>
            <TouchableOpacity onPress={uploadImage} style={styles.upload}>
              {resultImage?.uri ? (
                <Image
                  source={{ uri: resultImage?.uri }}
                  style={styles.image}
                />
              ) : (
                <ProfileSVG />
              )}
            </TouchableOpacity>
            {
              erroImage && (
                <Text style={styles.erroUpload}>
                    Precisa adicionar uma image
                </Text>)
            }
            <Form ref={formRef} onSubmit={handleSubmit}>
              <Input name="name" placeholder="Digite seu nome" />
              <SelectInput
                name="typeGender"
                items={options}
                placeHolder="Selecione tipo de sexo"
              />

              <SelectInput
                name="breed"
                items={breedsOptions}
                placeHolder="Selecione uma raça"
              />

              <SelectInput
                name="deficiency"
                items={[
                  { value: "Yes", label: "Sim", key: 1 },
                  { value: "No", label: "Não", key: 2 },
                ]}
                placeHolder="Existe um deficiência"
              />

              <SelectInput
                name="year"
                items={[
                  { value: "1", label: "até 1 ano", key: 1 },
                  { value: "2", label: "2 anos", key: 2 },
                  { value: "3", label: "3 anos", key: 3 },
                  { value: "4", label: "4 anos", key: 4 },
                  { value: "5", label: "5 anos", key: 5 },
                  { value: "6", label: "6 anos", key: 6 },
                  { value: "7", label: "7 anos", key: 7 },
                  { value: "8", label: "mais de 8 anos", key: 8 },
                ]}
                placeHolder="Selecione idade"
              />

              <Input 
                name="note" 
                placeholder="Descrição breve do animal" 
                multiline
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
