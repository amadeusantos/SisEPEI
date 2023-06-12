import React from 'react';
import './Style.css';
import BotaoCadastrar from '../../Components/layout/BotaoCadastrar';
import SearchBar from '../../Components/layout/SearchBar';
import Card from '../../Components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import Axios from 'axios';
import Filter from '../../Components/layout/Filter';


export function PgCoordExtensao() {
  const [searchTerm, setSearchTerm] = useState('')
  const [cardsData, setCardsData] = useState([]);
  useEffect(() => {
    console.log("Fetching cards...");
  }, []);

    const filteredCards = cardsData.filter(card => card.titulo.toLowerCase().includes(searchTerm.toLowerCase()));
  return ( 
    <div id='page1'>
      <h1 className='welcome'>Bem vindo!</h1>
      <hr className='myhr' />
      <h1 className='editaiswelcome'>Editais de Extensão</h1>

      <div className='button-search'>
        <BotaoCadastrar />
          <div className='search-filter'>
          <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
          </div>
      </div>


      {Array.isArray(filteredCards) && filteredCards.map((card) => (
        <Card
        key={card.id}
        id={card.id}
        name={card.titulo}
        type={card.tipo}
        description={card.descricao}
        term={card.prazo}
        coordinator={card.coordenador.nome}
        requirements={card.requisitos}
        showDeleteButton={true} showEditButton={true} showShowButton={true}
        />
      ))}

    </div>
  );
}

export default PgCoordExtensao;
