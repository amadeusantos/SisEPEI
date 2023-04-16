import React from 'react';
import './Style.css';
import BotaoCadastrar from '../../Components/layout/BotaoCadastrar';
import SearchBar from '../../Components/layout/SearchBar';
import Card from '../../Components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import Axios from 'axios';
import Filter from '../../Components/layout/Filter';


export function PgCoordPesquisa() {
  const [searchTerm, setSearchTerm] = useState('')
  const [cardsData, setCardsData] = useState([{ id: 1, name: "Edital 1", type: "Extensao", description: "Descrição 1" },
  { id: 2, name: "Edital 2", type: "Pesquisa", description: "Descrição 2" },
  { id: 3, name: "Edital 3", type: "Pesquisa", description: "Descrição 3" },
  { id: 4, name: "Edital 4", type: "Extensao", description: "Descrição 4" },]);
  useEffect(() => {
    console.log("Fetching cards...");
    Axios
    //inserir o link no get
      .get(``)
      .then((response) => {
        //comentei esse setCardsData porque ele impede que eu teste com os cards que tem ali em cima.
        // setCardsData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  const valorfiltroPesquisa = "Pesquisa"
  const filteredCards = cardsData.filter(card => card.name.toLowerCase().includes(searchTerm.toLowerCase())
      && card.type.toLowerCase().includes(valorfiltroPesquisa.toLowerCase()))  
  return ( 
    <div id='page1'>
      <h1 className='welcome'>Bem vindo!</h1>
      <hr className='myhr' />
      <h1 className='editaiswelcome'>Editais de Pesquisa</h1>

      <div className='button-search'>
        <BotaoCadastrar />
          <div className='search-filter'>
          <SearchBar searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
            <Filter/>
          </div>
      </div>

      {/* Array.isArray vai checar se a data sendo recebida pelo back é um array, caso contrário não vai renderizar nada,
      coloquei aqui pra não ficar dando erro, mas caso preciso é só remover. */}

      {Array.isArray(filteredCards) && filteredCards.map((card) => (
      <Card
        key={card.id}
        name={card.name}
        type={card.type}
        description={card.description}
      />
    ))}

    </div>
  );
}

export default PgCoordPesquisa;
