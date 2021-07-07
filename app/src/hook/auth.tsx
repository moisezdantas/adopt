import React, {
  createContext,
  ReactNode,
  useContext,
  useState,
  useEffect,
} from "react";
import { Buffer } from "buffer";

import * as AuthSession from "expo-auth-session";
import AsyncStorage from "@react-native-async-storage/async-storage";
import {Alert} from 'react-native'

//   const { SCOPE } = process.env;
//   const { CLIENT_ID } = process.env;
//   const { CDN_IMAGE } = process.env;
//   const { REDIRECT_URI } = process.env;
//   const { RESPONSE_TYPE } = process.env;

import { api } from "../services/api";
import { COLLECTION_USERS } from "../config/user";
import { Base64 } from "../utils/boa";
import { MakeLogin } from "../services/request";
// import { Use } from "react-native-svg";

type User = {
  id: string;
  userName: string;
  firstName: string;
  //  avatar: string;
  email: string;
  access_token: string;
};

interface SignInCredentials {
  email: string;
  password: string;
}

type AuthContextData = {
  user: User;
  loading: boolean;
  signIn(credentials: SignInCredentials): Promise<void>;
  singOut: () => Promise<void>;
};

type AuthProviderProps = {
  children: ReactNode;
};

type AuthorizationResponse = AuthSession.AuthSessionResult & {
  params: {
    access_token?: string;
    error?: string;
  };
};

export const AuthContext = createContext({} as AuthContextData);

function AuthProvider({ children }: AuthProviderProps) {
  const [user, setUser] = useState<User>({} as User);
  const [loading, setLoading] = useState(false);

  async function signIn ({email, password }:SignInCredentials  ) {
    MakeLogin({ username: email, password })
    .then((response) => {
      if (response.data.access_token) {
        const userData = {
          id: response.data.userId,
          userName: response.data.userName,
          firstName: response.data.userName.split(" ")[0],
          email: response.data.email,
          access_token: response.data.access_token,
        };

        AsyncStorage.setItem(COLLECTION_USERS, JSON.stringify(userData));

        setUser(userData);
      }
    })
    .catch(() => {
      Alert.alert(
        'Erro na autenticação',
        'Ocorreu um erro ao fazer login, cheque as credenciais.',
      );
    })
    .finally(() => {
      setLoading(false);
    });
  }


  async function singOut() {
    setUser({} as User);

    await AsyncStorage.removeItem(COLLECTION_USERS);
  }

  async function loadUserStorageData() {
    const storage = await AsyncStorage.getItem(COLLECTION_USERS);

    if (storage) {
      const userLogged = JSON.parse(storage) as User;
      api.defaults.headers.authorization = `Bearer ${userLogged.access_token}`;
    }
  }

  useEffect(() => {
    loadUserStorageData();
  }, []);

  return (
    <AuthContext.Provider value={{ user, signIn, loading, singOut }}>
      {children}
    </AuthContext.Provider>
  );
}

function useAuth() {
  const context = useContext(AuthContext);
  return context;
}

export { AuthProvider, useAuth };
