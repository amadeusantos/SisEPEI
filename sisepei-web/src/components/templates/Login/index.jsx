import React from "react";
import { LoginForm } from "../../organisms";
import "./style.css";

export function Login() {
  return (
    <div className="frame">
      <div className="intro">
        <h2>
          <strong>O que é?</strong>
        </h2>
        <br />
        <p id="paragrafo">
          O SisPEI - Sistema de Editais de Pesquisa, Extensão e Inovação é um
          sistema de gestão de editais.
        </p>
        <br />
      </div>
      <LoginForm />
    </div>
  );
}

export default Login;
