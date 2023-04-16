import React from 'react';
import './Style.css';
import SearchBar from '../../Components/layout/SearchBar'
import Card from '../../Components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import Axios from 'axios';
import Filter from '../../Components/layout/Filter';


export function PaginaInicial() {
  const [searchTerm, setSearchTerm] = useState('')

  const [cardsData, setCardsData] = useState([{ id: 1, name: "Edital 1", type: "Tipo 1", description: "Descrição 1" },
  { id: 2, name: "Edital 2", type: "Tipo 2", description: "Lorem ipsum sit amet, consectetur adipiscing elit. Nulla facilisi. Vestibulum consectetur, sapien auctor consectetur fermentum, sem mauris bibendum mauris, id congue diam tellus ut nisl. Sed ac luctus leo. Donec euismod justo eu libero pretium ultricies. Sed aliquam, nibh vel ullamcorper feugiat, mi purus egestas nunc, vel sodales mi massa in metus. Aenean auctor, ipsum a tincidunt malesuada, felis dui convallis tortor, in fringilla elit elit nec ante. Sed tristique nibh nibh, non dapibus massa pulvinar eu. Sed efficitur metus nec odio blandit interdum. Sed blandit lacus ac elit imperdiet, non gravida metus pharetra. Sed non lacus tincidunt, bibendum metus eget, vestibulum ex. In hac habitasse platea dictumst. Maecenas rhoncus orci a libero efficitur, sit amet semper felis dignissim. Suspendisse potenti." },
  { id: 3, name: "Edital 3", type: "Tipo 1", description: "Descrição 3" },
  { id: 4, name: "Edital 4", type: "Tipo 2", description: "Descrição 4" },]);
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
  const filteredCards = cardsData.filter(card => card.name.toLowerCase().includes(searchTerm.toLowerCase())

      || card.type.toLowerCase().includes(searchTerm.toLowerCase()))
  return ( 

    <div id='page1'>
      <h1 className='welcome'>Bem vindo!</h1>
      <hr className='myhr' />
      <h1 className='editaiswelcome'>Editais</h1>

      <div className='button-search'>

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
        showDeleteButton={false} showEditButton={false} showShowButton={true}
      />
    ))}


    </div>
  );
}

export default PaginaInicial;
