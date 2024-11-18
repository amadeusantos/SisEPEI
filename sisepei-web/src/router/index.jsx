import { Navigate, Route, Routes } from "react-router-dom";
import { Login, Home, Register, CreateNotice, EditNotice } from "../components/templates";
import React from "react";
import { EscolhaDePersonalidade } from "../components/templates/pagina-intermedio-login-dashboard/EscolhaDePersoladidade";
import { PermissionsPage } from "../components/templates/Permissions";
export default function Router() {
  return (
    <Routes>
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<Home />} />

      <Route path="/new/notices" element={<CreateNotice />} />
      <Route path="/edit/notices/:id" element={<EditNotice />} />
      <Route path="/intermedio" element={<EscolhaDePersonalidade />} />

      <Route path="/admin/permissions" element={<PermissionsPage />} />

      <Route path="*" element={<Navigate to={"/"} />} />
    </Routes>
  );
}
