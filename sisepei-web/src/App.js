import './App.css';
import { Route, Routes } from 'react-router-dom';
import { CadastroUsuarioGeral } from './pages/Cadastro UsuarioGeral/CadastroUsuarioGeral';
import { CadastroConcluido } from './pages/Cadastro UsuarioGeral/CadastroConcluido';



function App() {
  return (
    <>
    <div>
      <Routes>
        <Route path="/usuario/geral/cadastro" element={<CadastroUsuarioGeral />} />
        <Route path="/usuario/geral/cadastro/concluido" element={<CadastroConcluido />} />
      </Routes>
    </div>
    </>
  );
  
}

export default App;
