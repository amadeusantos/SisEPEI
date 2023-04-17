import React from 'react';
import './Style.css';
import SearchBar from '../../Components/layout/SearchBar'
import Card from '../../Components/layout/InfoEditais';
import { useEffect, useState } from 'react';
import Filter from '../../Components/layout/Filter';
import Cookies from 'js-cookie';
import { api } from '../../lib/Api';


export function PaginaInicial() {
  const [searchTerm, setSearchTerm] = useState('')

  const [cardsData, setCardsData] = useState([]);

  function Poderes(perfis) {
    var permicoes = [perfis.map((perfil) => {
      if(perfil.nome !== "ADMINISTRADOR") {
        return perfil.nome
      }
    })];
    Cookies.set("perfis", permicoes);
    console.log(Cookies.get("perfis"))
}

  useEffect(() => {
    api.get("usuarios/perfil", {
      headers: {
          Authorization: `Bearer ${Cookies.get("token")}`
      }
    }).then((res) => {
      console.log(res.data.perfis)
      Poderes(res.data.perfis)
    })
    console.log("Fetching cards...");
    api

      .get(`edital`,
       {headers: {
        Authorization: `Bearer ${Cookies.get("token")}`
       }})
      .then((response) => {
        //comentei esse setCardsData porque ele impede que eu teste com os cards que tem ali em cima.
        setCardsData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  const filteredCards = cardsData.filter(card => card.titulo.toLowerCase().includes(searchTerm.toLowerCase())

      || card.tipo.toLowerCase().includes(searchTerm.toLowerCase()))
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
        id={card.id}
        name={card.titulo}
        type={card.tipo}
        description={card.descricao}
        term={card.prazo}
        coordinator={card.coordenador.nome}
        requirements={card.requisitos}
        showDeleteButton={false} showEditButton={false} showShowButton={true}
      />
    ))}


    </div>
  );
}

export default PaginaInicial;
