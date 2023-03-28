import './App.css';
import { Route, Routes } from 'react-router-dom';
import { CadastroUsuarioGeral } from './pages/Cadastro UsuarioGeral/CadastroUsuarioGeral';
import { CadastroConcluido } from './pages/Cadastro UsuarioGeral/CadastroConcluido';
import { CadastroCoordenador } from './pages/Cadastro UsuarioGeral/CadastroCoordenador';
import { CadastroEditais } from './pages/Cadastro UsuarioGeral/CadastroEditais';



function App() {
  return (
    <>
    <div>
      <Routes>
        <Route path="/usuario/geral/cadastro" element={<CadastroUsuarioGeral />} />
        <Route path="/cadastro/concluido" element={<CadastroConcluido />} />
        <Route path="usuario/coordenador/cadastro" element={<CadastroCoordenador />} />
        <Route path="/edital/cadastro" element={<CadastroEditais />} />
      </Routes>
    </div>
    </>
  );
  
}

export default App;
