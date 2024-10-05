import React from 'react';
import './Style.css';
import SearchBar from '../../components/layout/SearchBar';
import Card from '../../components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import { api } from '../../lib/Api';
import Cookies from 'js-cookie';
import BotaoCadastrar from '../../components/layout/BotaoCadastrar';


export function PaginaCoordenadorInovacao() {
  const [searchTerm, setSearchTerm] = useState('')
  const [cardsData, setCardsData] = useState([]);
  useEffect(() => {
    console.log("Fetching cards...");
    
    api.get(`edital/tipo/INOVACAO`,  {
      headers: {
          Authorization: `Bearer ${Cookies.get("token")}`
      }
    })
      .then((response) => {
        setCardsData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

    const filteredCards = cardsData.filter(card => card.titulo.toLowerCase().includes(searchTerm.toLowerCase()));

  return (
      <div id='page1'>
      <h1 className='welcome'>Bem vindo!</h1>
      <hr className='myhr' />
      <h1 className='editaiswelcome'>Editais de Inovação</h1>

      <div className='button-search'>
      <BotaoCadastrar/>
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

export default PaginaCoordenadorInovacao;
