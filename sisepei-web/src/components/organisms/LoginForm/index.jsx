import Button from "../../atoms/Button";
import { useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import { TextField } from "../../molecules";
import { useState } from "react";
import { login } from "../../../services/AuthenticationService";
import "./style.css";
import { Title } from "../../atoms";

export function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();

    await login({ email, password });

    navigate("/");
  }

  const handleClick = () => {
    navigate("/cadastro/usuario");
  };

  return (
    <div className="login">
      <Title>Login</Title>
      <br />
      <Form action="" onSubmit={handleSubmit} className="form">
        <TextField
          label="Email"
          name="email"
          value={email}
          onChange={setEmail}
          type="email"
          required
          placeholder="Digite aqui seu email"
        />
        <TextField
          label="Senha"
          name="password"
          value={password}
          onChange={setPassword}
          type="password"
          required
          placeholder="Digite aqui sua senha"
        />
        <div className="buttons">
          <Button type="submit" color="secundary">
            Entrar
          </Button>
          <Button onClick={handleClick}>Cadastre-se</Button>
        </div>
      </Form>
    </div>
  );
}

export default LoginForm;
