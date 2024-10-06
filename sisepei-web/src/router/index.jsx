import { Route, Routes } from "react-router-dom";
import { Login } from "../components/templates";
import { CadastroUsuarioGeral } from "../components/templates/Cadastro/CadastroUsuarioGeral";
import { MudancaPermicao } from "../components/templates/Cadastro/MudancaPermicao";
import { CadastroEditais } from "../components/templates/Cadastro/CadastroEditais";
import PaginaInicial from "../components/templates/pagina-inicial-usuario/Index";
import React from "react";
import PaginaCoordenadorInovacao from "../components/templates/pagina-inicial-de-coordenador-de-inovacao/pg-inova-cood";
import PgCoordPesquisa from "../components/templates/paginaInicialCoordPesquisa/Index";
import PgCoordExtensao from "../components/templates/pagina-coord-extensao/Index";
import { EscolhaDePersonalidade } from "../components/templates/pagina-intermedio-login-dashboard/EscolhaDePersoladidade";
import EditarEditais from "../components/templates/tela-editar-editais/tela-editar-editais";

export default function Router() {
  return (
    <Routes>
      <Route path="/cadastro/usuario" element={<CadastroUsuarioGeral />} />
      <Route path="/adm/mudanca/permicao" element={<MudancaPermicao />} />
      <Route path="/edital" element={<CadastroEditais />} />
      <Route path="/" element={<Login />} />
      <Route path="/paginainicial" element={<PaginaInicial />} />
      <Route
        path="/paginacoodenadorinovacao"
        element={<PaginaCoordenadorInovacao />}
      />
      <Route path="/intermedio" element={<EscolhaDePersonalidade />} />
      <Route path="/paginainicialcoordpesquisa" element={<PgCoordPesquisa />} />
      <Route path="/paginainicialcoordextensao" element={<PgCoordExtensao />} />
      <Route path="/editaredital" element={<EditarEditais />} />
    </Routes>
  );
}
