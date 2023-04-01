import './App.css';
import PaginaInicial from './components/pages/pagina-inicial-usuario';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<PaginaInicial/>} />
      </Routes>
    </Router>
  );
}

export default App;
