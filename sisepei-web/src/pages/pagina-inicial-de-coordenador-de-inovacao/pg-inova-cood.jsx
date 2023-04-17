import React from 'react';
import './Style.css';
import SearchBar from '../../Components/layout/SearchBar';
import Card from '../../Components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import Axios from 'axios';
import Filter from '../../Components/layout/Filter';


export function PaginaCoordenadorInovacao() {
  const [searchTerm, setSearchTerm] = useState('')
  const [cardsData, setCardsData] = useState([{ id: 1, name: "Edital 1", coordinator: "Cleverton", type: "Extensão", term: "20/04/2023", requirements: "Nenhum", description: "Lorem ipsum sit amet, consectetur adipiscing elit. Nulla facilisi. Vestibulum consectetur, sapien auctor consectetur fermentum, sem mauris bibendum mauris, id congue diam tellus ut nisl. Sed ac luctus leo. Donec euismod justo eu libero pretium ultricies. Sed aliquam, nibh vel ullamcorper feugiat, mi purus egestas nunc, vel sodales mi massa in metus. Aenean auctor, ipsum a tincidunt malesuada, felis dui convallis tortor, in fringilla elit elit nec ante. Sed tristique nibh nibh, non dapibus massa pulvinar eu. Sed efficitur metus nec odio blandit interdum. Sed blandit lacus ac elit imperdiet, non" },
  { id: 2, name: "Edital 2", coordinator: "Bruno", type: "Pesquisa", term: "20/04/2023", requirements: "Doutorado", description: "Lorem ipsum sit amet, consectetur adipiscing elit. Nulla facilisi. Vestibulum consectetur, sapien auctor consectetur fermentum, sem mauris bibendum mauris, id congue diam tellus ut nisl. Sed ac luctus leo. Donec euismod justo eu libero pretium ultricies. Sed aliquam, nibh vel ullamcorper feugiat, mi purus egestas nunc, vel sodales mi massa in metus. Aenean auctor, ipsum a tincidunt malesuada, felis dui convallis tortor, in fringilla elit elit nec ante. tur metus nec odio blandit interdum. Sed blandit lacus ac elit imperdiet, non" },
  { id: 3, name: "Edital 3", coordinator: "Cleyton", type: "Inovação", term: "20/04/2023", requirements: "Mestrado", description: "Lorem ipsum sit amet, consectetur adipiscing elit. Nulla facilisi. Vestibulum consectetur, sapien auctor consectetur fermentum, sem mauris bibendum mauris, id congue diam tellus ut nisl. Sed ac luctus leo. Donec euismod justo eu libero pretium ultricies. Sed aliquam, nibh vel ullamcorper feugiat, mi purus egestas nunc, vel sodales mi massa in metus. Aenean auctor, ipsum a tincidunt malesuada, felis dui convallis tortor, in fringilla elit elit nec ante. Sed tristique nibh nibh, non dapibus massa pulvinar eu. Sed efficitur metus nec odio blandit interdum. Sed blandit lacus ac elit imperdiet, non" },
  { id: 4, name: "Edital 4", coordinator: "Helaine", type: "Pesquisa", term: "20/04/2023", requirements: "Graduação", description: "Lorem ipsum sit amet, consectetur adipiscing elit. Nulla facilisi. Vestibulum consectetur, sapien auctor consectetur fermentum, sem mauris bibendum mauris, id congue diam tellus ut nisl. Sed ac luctus leo. Donec euismod justo eu libero pretium ultricies. Sed aliquam, nibh vel ullamcorper feugiat, mi purus egestas nunc, vel sodales mi massa in metus. Aenean auctor, ipsum a tincidunt malesuada, felis dui convallis tortor, in fringilla elit elit nec ante. Sed tristique nibh nibh, non dapibus massa pulvinar eu. Sed efficitur metus nec odio blandit interdum. Sed blandit lacus ac elit imperdiet, non" },]);
  useEffect(() => {
    console.log("Fetching cards...");
    Axios
      //inserir o link no get
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
