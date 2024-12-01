import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { Button, SubTitle, Title } from "../../atoms";
import { TextField } from "../../molecules";
import "./style.css";
import { useRegister } from "./register-form.store";
import { Badge, Form, message, Space } from "antd";

export function passowordValidation({
  setLenght8,
  setContainsNumber,
  setContainsCharSpecial,
  setContainsUppercase,
  setContainsLowercase,
}) {
  return (_, value) => {
    setLenght8 && setLenght8(!!value && value.length >= 8);
    setContainsNumber && setContainsNumber(/.*[0-9].*/.test(value));
    setContainsCharSpecial &&
      setContainsCharSpecial(/.*[^0-9, a-z, A-Z].*/.test(value));
    setContainsUppercase && setContainsUppercase(/.*[A-Z].*/.test(value));
    setContainsLowercase && setContainsLowercase(/.*[a-z].*/.test(value));
    if (
      !value ||
      (value.length >= 8 &&
        /.*[0-9].*/.test(value) &&
        /.*[^0-9, a-z, A-Z].*/.test(value) &&
        /.*[A-Z].*/.test(value) &&
        /.*[a-z].*/.test(value))
    ) {
      return Promise.resolve();
    }
    return Promise.reject(new Error("Senha inválida!"));
  };
}

export function RegisterForm() {
  const navigation = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [lenght8, setLenght8] = useState(false);
  const [containsNumber, setContainsNumber] = useState(false);
  const [containsCharSpecial, setContainsCharSpecial] = useState(false);
  const [containsUppercase, setContainsUppercase] = useState(false);
  const [containsLowercase, setContainsLowercase] = useState(false);
  const passwordValidator = passowordValidation({
    setLenght8,
    setContainsNumber,
    setContainsCharSpecial,
    setContainsUppercase,
    setContainsLowercase,
  });
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const { mutate } = useRegister(() => {
    navigation("/login");
    message.success("Registro feito com sucesso");
  });

  async function registerUser() {
    mutate({
      name: name,
      email: email,
      password: password,
    },); 
  }

  const handleClick = () => {
    navigation("/login");
  };
  return (
    <Form id="divGeral" onFinish={registerUser}>
      <Title>Cadastro de Usuário Geral</Title>
      <SubTitle>Preencha o Cadastro com as informações pertinentes</SubTitle>
      <TextField
        label="Nome"
        name="name"
        type="name"
        onChange={setName}
        value={name}
        rules={[
          {
            required: true,
            message: "Digite seu nome!",
          },
        ]}
        required
      />
      <TextField
        label="Email"
        name="email"
        type="email"
        onChange={setEmail}
        value={email}
        rules={[
          {
            required: true,
            message: "Digite uma email!",
          },
          {
            type: "email",
            message: "digite um email valido!",
          },
          {
            pattern: /^[A-Za-z0-9_.]+@upe\.br$/gm,
            message: "O email precisa ser institucional!",
          },
        ]}
        required
      />
      <TextField
        label="Senha"
        name="password"
        onChange={setPassword}
        value={password}
        type="Password"
        rules={[
          {
            required: true,
            message: "Digite uma senha!",
          },
          { validator: passwordValidator },
        ]}
        required
      />
      <TextField
        label="Confirme sua senha"
        name="confirmPassword"
        onChange={setConfirmPassword}
        value={confirmPassword}
        rules={[
          { required: true, message: "Confirme sua senha!" },
          ({ getFieldValue }) => ({
            validator(_, value) {
              if (!value || getFieldValue("password") === value) {
                return Promise.resolve();
              }
              return Promise.reject(new Error("Senha não correspondente!"));
            },
          }),
        ]}
        type="Password"
        required
      />
      <Space style={{ paddingBottom: 24 }} direction="vertical">
        <Badge
          status={lenght8 ? "success" : "error"}
          text="Senha com no mínimo 8 caracteres."
        />
        <Badge
          status={containsUppercase ? "success" : "error"}
          text="Senha com no mínimo 1 letra maiúscula."
        />
        <Badge
          status={containsLowercase ? "success" : "error"}
          text="Senha com no mínimo 1 letra minúscula."
        />
        <Badge
          status={containsNumber ? "success" : "error"}
          text="Senha com no mínimo 1 número."
        />
        <Badge
          status={containsCharSpecial ? "success" : "error"}
          text="Senha com no mínimo 1 caracteres especial."
        />
      </Space>
      <div className="space-evenly">
        <Button onClick={handleClick}>Voltar</Button>
        <Button
          color="secondary"
          type="submit"
          disabled={password.length < 8 || password !== confirmPassword}
        >
          Cadastrar
        </Button>
      </div>
    </Form>
  );
}

export default RegisterForm;