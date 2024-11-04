import Button from "../../atoms/Button";
import { useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import { TextField } from "../../molecules";
import { useState } from "react";
import "./style.css";
import { Title } from "../../atoms";
import { useLogin } from "./login-form.store";

export function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const { mutate } = useLogin(() => navigate("/"));

  async function handleSubmit(e) {
    e.preventDefault();

    mutate({ email, password });
  }

  const handleClick = () => {
    navigate("/register");
  };

  return (
    <div className="login">
      <Title>Login</Title>
      <br />
      <Form role="form" action="" onSubmit={handleSubmit} className="form">
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
