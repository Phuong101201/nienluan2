import { message } from "antd";
import React, { createContext, useContext, useEffect, useState } from "react";
import { localhost } from "../service/api";
import { useHistory } from "react-router-dom";
import { useCategory } from "./category";
interface ContextProps {
  login: (username: string, password: string) => void;
  isAdmin: () => {};
  user: string;
}
const Authcontext = createContext({} as ContextProps);
const AuthProvider = (props: { children: React.ReactNode }) => {
  const [user, setUser] = useState<string>("");
  const history = useHistory();
  const { update } = useCategory();
  useEffect(() => {
    setUser(localStorage.getItem("name") || "");
  }, [user, update]);
  const login = (username: string, password: string) => {
    localhost
      .post({
        url: "/login",
        data: { username: username, password: password },
      })
      .then(async (res) => {
        window.localStorage.setItem("token", res.data.token);
        window.localStorage.setItem("id", res.data.id);
        window.localStorage.setItem("name", res.data.name);
        window.location.href = "/";
      })
      .catch(() => {
        message.error("nhập sai thông tin");
      });
  };
  const isAdmin = async () => {
    localhost
      .get({ url: `/v1/roles/${localStorage.getItem("id")}` })
      .then((res) => {})
      .catch((res) => {
        console.log(res.response.data.message);
        window.location.href = "/";
      });
  };

  return (
    <Authcontext.Provider value={{ login, isAdmin, user }}>
      <>{props.children}</>
    </Authcontext.Provider>
  );
};
const useAuth = () => {
  return useContext(Authcontext);
};
export { AuthProvider, useAuth, Authcontext };
