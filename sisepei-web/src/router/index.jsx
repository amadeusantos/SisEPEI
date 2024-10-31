import { Navigate, Route, Routes } from "react-router-dom";
import { Login, Home, Register, CreateNotice, EditNotice } from "../components/templates";
import React from "react";
import { EscolhaDePersonalidade } from "../components/templates/pagina-intermedio-login-dashboard/EscolhaDePersoladidade";
import EditarEditais from "../components/templates/tela-editar-editais/tela-editar-editais";

export default function Router() {
  return (
    <Routes>
      <Route path="/cadastro/usuario" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<Home />} />

      <Route path="/new/notices" element={<CreateNotice />} />
      <Route path="/edit/notices/:id" element={<EditNotice />} />
      <Route path="/intermedio" element={<EscolhaDePersonalidade />} />
      <Route path="/editaredital" element={<EditarEditais />} />

      <Route path="*" element={<Navigate to={"/"} />} />
    </Routes>
  );
}
