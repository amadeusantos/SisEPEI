import './App.css';
import { Route, Routes } from 'react-router-dom';
import { CadastroUsuarioGeral } from './pages/Cadastro/CadastroUsuarioGeral';
import { CadastroConcluido } from './pages/Cadastro/CadastroConcluido';
//import { MudancaPermicao } from './pages/Cadastro/MudancaPermicao';
import { CadastroEditais } from './pages/Cadastro/CadastroEditais';


function App() {
  return (
    <>
    <div>
      <Routes>
        <Route path="/cadastro/usuario" element={<CadastroUsuarioGeral />} />
        <Route path="/cadastro/concluido" element={<CadastroConcluido />} />
        {/** 
        <Route path="/adm/mudanca/permicao" element={<MudancaPermicao />} />
        */}
        <Route path="/edital" element={<CadastroEditais />} />
      </Routes>
    </div>
    </>
  );
  
}

export default App;
