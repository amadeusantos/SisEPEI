import { Navigate, Route, Routes } from "react-router-dom";
import {
  Login,
  Home,
  Register,
  CreateNotice,
  EditNotice,
} from "../components/templates";
import React from "react";
import { EscolhaDePersonalidade } from "../components/templates/pagina-intermedio-login-dashboard/EscolhaDePersoladidade";
import { PermissionsPage } from "../components/templates/Permissions";
import { PrivateRoute } from "./router-private";

export default function Router() {
  return (
    <Routes>
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route
        path="/"
        element={
          <PrivateRoute>
            <Home />
          </PrivateRoute>
        }
      />

      <Route
        path="/new/notices"
        element={
          <PrivateRoute profiles={["COORDENADOR_EXTENSAO", "COORDENADOR_PESQUISA", "COORDENADOR_INOVACAO"]}>
            <CreateNotice />
          </PrivateRoute>
        }
      />

      <Route
        path="/edit/notices/:id"
        element={
          <PrivateRoute profiles={["COORDENADOR_EXTENSAO", "COORDENADOR_PESQUISA", "COORDENADOR_INOVACAO"]}>
            <EditNotice />
          </PrivateRoute>
        }
      />

<Route
        path="/admin/permissions"
        element={
          <PrivateRoute profiles={["ADMINISTRADOR"]}>
            <PermissionsPage />
          </PrivateRoute>
        }
      />

      <Route path="/intermedio" element={<EscolhaDePersonalidade />} />

      <Route path="*" element={<Navigate to={"/"} />} />
    </Routes>
  );
}
