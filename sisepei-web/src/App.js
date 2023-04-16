import './App.css';
import { Route, Routes } from 'react-router-dom';
import { CadastroUsuarioGeral } from './pages/Cadastro/CadastroUsuarioGeral';
import { MudancaPermicao } from './pages/Cadastro/MudancaPermicao';
import { CadastroEditais } from './pages/Cadastro/CadastroEditais';

import Header from '../src/Components/Header/Index';

import Login from "./pages/Login/Login";
import React from 'react';

import PaginaCoordenadorInovacao from './pages/pagina-inicial-de-coordenador-de-inovacao/pg-inova-cood';
import { EscolhaDePersonalidade } from './pages/pagina-intermedio-login-dashboard/EscolhaDePersoladidade';

import PaginaInicial from './pages/pagina-inicial-usuario/Index';
import PgCoordPesquisa from './pages/paginaInicialCoordPesquisa/Index';
import PgCoordExtensao from './pages/pagina-coord-extensao/Index';



function App() {
  return (
    <>
    <div className="app">
      <Header/>
      <Routes>
        <Route path="/cadastro/usuario" element={<CadastroUsuarioGeral />} />
        <Route path="/adm/mudanca/permicao" element={<MudancaPermicao />} />
        <Route path="/edital" element={<CadastroEditais />} />
        <Route path="/" element={<Login />} />
        <Route path="/paginainicial" element={<PaginaInicial/>} />

        <Route path='/paginacoodenadorinovacao' element={<PaginaCoordenadorInovacao/>}/>
        <Route path='/intermedio' element={<EscolhaDePersonalidade />}/>


        <Route path="/paginainicialCoordPesquisa" element={<PgCoordPesquisa/>} />
        <Route path="/paginainicialCoordExtensao" element={<PgCoordExtensao/>} />


      </Routes>
    </div>
    </>
  );
  
}

export default App;
