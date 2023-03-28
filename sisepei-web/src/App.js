import './App.css';
import { Route, Routes } from 'react-router-dom';
import { CadastroUsuarioGeral } from './pages/Cadastro UsuarioGeral/CadastroUsuarioGeral';
import { CadastroConcluido } from './pages/Cadastro UsuarioGeral/CadastroConcluido';
import { CadastroEditais } from './pages/Cadastro UsuarioGeral/CadastroEditais';
import { CadastroCoordenador } from './pages/Cadastro UsuarioGeral/CadastroCoordenador';




function App() {
  return (
    <>
    <div>
      <Routes>
        <Route path="/cadastro/usuario/geral" element={<CadastroUsuarioGeral />} />
        <Route path="/cadastro/concluido" element={<CadastroConcluido />} />
        <Route path="/cadastro/coordenador" element={<CadastroCoordenador />} />
        <Route path="/cadastro/edital" element={<CadastroEditais />} />
      </Routes>
    </div>
    </>
  );
  
}

export default App;
