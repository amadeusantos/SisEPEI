import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { Button, SubTitle, Title } from "../../atoms";
import { TextField } from "../../molecules";
import { Alert, Form } from "react-bootstrap";
import { api } from "../../../services/api";
import "./style.css";

export function RegisterForm() {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [alertShow, setAlertShow] = useState(false);
  const [errors, setErrors] = useState([]);

  const validation = (input) => {
    validationFunction["required"](input);

    if (Object.keys(validationFunction).includes(input.id)) {
      validationFunction[input.id](input);
    }
  };

  const validationFunction = {
    name: (input) => {
      if (input.value === undefined || input === "") {
        setErrors((values) => [...values, "É nescessário um nome!"]);
      }
    },
    email: (input) => {
      if (!/^[\w|\.]+@upe.br/gm.test(input.value)) {
        setErrors((values) => [
          ...values,
          "Email inválido! digite seu email institucional.",
        ]);
      }
    },
    password: (input) => {
      if (!/[A-Z]/.test(input.value)) {
        setErrors((values) => [
          ...values,
          "A senha deve conter um letra maiúscula!",
        ]);
      }
      if (!/[a-z]/.test(input.value)) {
        setErrors((values) => [
          ...values,
          "A senha deve conter um letra minúscula!",
        ]);
      }
      if (!/[0-9]/.test(input.value)) {
        setErrors((values) => [...values, "A senha deve conter um número!"]);
      }
      if (!/[!@#$%^&*(),.?":{}|<>]/.test(input.value)) {
        setErrors((values) => [
          ...values,
          "A senha deve conter um caracter especial!",
        ]);
      }
    },
    required: (input) => {
      if (input.required && (input.value === undefined || input.value === "")) {
        setErrors((values) => [...values, `${input.name} é nescessário!`]);
      }
    },
  };

  async function registerUser(event) {
    event.preventDefault();

    setAlertShow(false);
    setErrors([]);

    for (let i = 0; i < event.target.length; i++) {
      validation(event.target[i]);
    }

    if (errors.length > 0) {
      setAlertShow(true);
    }
    console.log(errors);

    api
      .post("api/auth/register", {
        name: name,
        email: email,
        password: password,
      })
      .then(() => {
        alert("Usuário cadastrado com sucesso!")
        navigate("/login")
      })
      .catch((err) => setErrors((values) => [...values, err.data.message]));
  }

  const handleClick = () => {
    navigate("/login");
  };
  return (
    <Form id="divGeral" onSubmit={registerUser} action="">
      <Title>Cadastro de Usuário Geral</Title>
      <SubTitle>Preencha o Cadastro com as informações pertinentes</SubTitle>
      <TextField
        label="Nome"
        name="name"
        type="name"
        onChange={setName}
        value={name}
        required
      />
      <TextField
        label="Email"
        name="email"
        type="email"
        onChange={setEmail}
        value={email}
        required
      />
      <TextField
        label="Senha"
        name="password"
        onChange={setPassword}
        value={password}
        type="password"
        required
      />
      <TextField
        label="Confirme sua senha"
        name="confirmPassword"
        onChange={setConfirmPassword}
        value={confirmPassword}
        type="password"
        required
      />
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
      <Alert variant="warning" show={alertShow}>
        {errors.map((err) => (
          <p>{err}</p>
        ))}
      </Alert>
    </Form>
  );
}

export default RegisterForm;
