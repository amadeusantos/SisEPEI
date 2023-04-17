import React from 'react';
import './Style.css';
import SearchBar from '../../Components/layout/SearchBar';
import Card from '../../Components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import Axios from 'axios';
import Filter from '../../Components/layout/Filter';


export function PaginaCoordenadorInovacao() {
  const [searchTerm, setSearchTerm] = useState('')
  const [cardsData, setCardsData] = useState([]);
  useEffect(() => {
    console.log("Fetching cards...");
    Axios
      .get(`/{id}`)
      .then((response) => {
        setCardsData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

    const valorfiltroInovacao = "inovação"
    const filteredCards = cardsData.filter(card => card.name.toLowerCase().includes(searchTerm.toLowerCase())
        && card.type.toLowerCase().includes(valorfiltroInovacao.toLowerCase()))

  return (
    <div id='page1'>
      <h1 className='welcome'>Bem vindo!</h1>
      <hr className='myhr' />
      <h1 className='editaiswelcome'>Editais de Inovação</h1>

      <div className='button-search'>
        <div className='search-filter'>
          <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
          <Filter />
        </div>
      </div>

      {Array.isArray(filteredCards) && filteredCards.map((card) => (
        <Card
          id={card.id}
          name={card.name}
          type={card.type}
          description={card.description}
          term={card.term}
          requirements={card.requirements}
          coordinator={card.coordinator}
          showDeleteButton={false} showEditButton={false} showShowButton={true}
        />
      ))}

    </div>
  );
}

export default PaginaCoordenadorInovacao;
