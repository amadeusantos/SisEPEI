import React from "react";
import { LoginForm } from "../../organisms";
import "./style.css";

export function Login() {
  return (
    <main className="frame">
      <div className="intro">
        <h2>
          <strong>O que é?</strong>
        </h2>
        <p id="paragrafo">
          O SisPEI - Sistema de Editais de Pesquisa, Extensão e Inovação é um
          sistema de gestão de editais.
        </p>
      </div>
      <LoginForm />
    </main>
  );
}

export default Login;
