import React from 'react';
import './Style.css';
import SearchBar from '../../Components/layout/SearchBar';
import Card from '../../Components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import Filter from '../../Components/layout/Filter';
import { api } from '../../lib/Api';
import Cookies from 'js-cookie';
import BotaoCadastrar from '../../Components/layout/BotaoCadastrar';


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

          <div className='search-filter'>
          <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
            <BotaoCadastrar/>
          </div>

      </div>

      {/* Array.isArray vai checar se a data sendo recebida pelo back é um array, caso contrário não vai renderizar nada,
      coloquei aqui pra não ficar dando erro, mas caso preciso é só remover. */}

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
