import './App.css';
import { Route, Routes } from 'react-router-dom';
import { CadastroUsuarioGeral } from './pages/Cadastro/CadastroUsuarioGeral';

function App() {
  return (
    <>
    <div>
      <Routes>
        <Route path="/cadastro/usuario" element={<CadastroUsuarioGeral />} />
        {/* 
        <Route path="/cadastro/concluido" element={<CadastroConcluido />} />
        <Route path="/adm/mudanca/permicao" element={<MudancaPermicao />} />
        <Route path="/cadastro/edital" element={<CadastroEditais />} />
        */}
      </Routes>
    </div>
    </>
  );
  
}

export default App;
