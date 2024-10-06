import Button from "../../atoms/Button";
import { api } from "../../../services/Api";
import { useNavigate } from "react-router-dom";
import Form from "react-bootstrap/Form";
import { InputText } from "../../molecules";
import { useState } from "react";
import Cookies from "js-cookie";
import "./style.css";

export function LoginForm() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    console.log(e);

    await api
      .post("api/auth/authenticate", { email: email, password: senha })

      .then((res) => {
        Cookies.set("token", res.data.token);
        navigate("/paginainicial");
      })

      .catch((error) => {
        console.log(error);
      });
  }
  const handleClick = () => {
    navigate("/cadastro/usuario");
  };
  return (
    <div className="login">
      <h1 className="title">Login</h1>
      <br />
      <Form action="" onSubmit={handleSubmit} className="form">
        <InputText
          label="email"
          name="email"
          value={email}
          onChange={setEmail}
          type="email"
          required
          placeholder="Digite aqui seu email"
        />
        <InputText
          label="senha"
          name="password"
          value={senha}
          onChange={setSenha}
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
