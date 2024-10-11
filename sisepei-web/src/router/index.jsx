import { Route, Routes } from "react-router-dom";
import { Login, Home, Register } from "../components/templates";
import { MudancaPermicao } from "../components/templates/Cadastro/MudancaPermicao";
import { CadastroEditais } from "../components/templates/Cadastro/CadastroEditais";
import React from "react";
import { EscolhaDePersonalidade } from "../components/templates/pagina-intermedio-login-dashboard/EscolhaDePersoladidade";
import EditarEditais from "../components/templates/tela-editar-editais/tela-editar-editais";

export default function Router() {
  return (
    <Routes>
      <Route path="/cadastro/usuario" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<Home />} />

      <Route path="/adm/mudanca/permicao" element={<MudancaPermicao />} />
      <Route path="/edital" element={<CadastroEditais />} />
      <Route />
      <Route path="/intermedio" element={<EscolhaDePersonalidade />} />
      <Route path="/editaredital" element={<EditarEditais />} />
    </Routes>
  );
}
