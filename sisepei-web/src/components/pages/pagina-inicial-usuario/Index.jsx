import React from 'react';
import TopBar from '../../layout/topbar';
import './style.css';
import BotaoCadastrar from '../../layout/BotaoCadastrar';
import SearchBar from '../../layout/SearchBar';

export function PaginaInicial(props) {
  return (
    <body id='page1'>
      <TopBar />
      <h1 className='welcome'>Bem vindo!</h1>
      <hr className='myhr' />
      <h1 className='editaiswelcome'>Editais</h1>
      <div className='button-search'>
        <BotaoCadastrar />
        <SearchBar />
      </div>
    </body>
  );
}

export default PaginaInicial;

