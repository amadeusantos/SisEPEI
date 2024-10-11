import Card from "../../atoms/InfoEditais";
import { SubTitle, Title } from "../../atoms";
import { Button } from "../../atoms";
import { Plus } from "@phosphor-icons/react";
import { useState, useEffect } from "react";
import Filter from "../../molecules/Filter";
import "./style.css";
import { useSearchParams } from "react-router-dom";
import { api } from "../../../services/Api";
import Cookies from "js-cookie";

export function NoticeList() {
  const [order, setOrder] = useState("");
  const [filter, setFilter] = useState("");
  const [cardsData, setCardsData] = useState([]);

  const filteredCards = cardsData
    .filter(
      (card) =>
        card.titulo.toLowerCase().includes(filter.toLowerCase()) ||
        card.tipo.toLowerCase().includes(filter.toLowerCase())
    )
    .sort((a, b) => {
      switch (order) {
        case "asc":
          return new Date(a.time).getTime() < new Date(b.time).getTime()
            ? -1
            : 1;
        case "desc":
          return new Date(a.time).getTime() > new Date(b.time).getTime()
            ? -1
            : 1;
        default:
          return 0;
      }
    });

  useEffect(() => {
    api
      .get("usuarios/perfil", {
        headers: {
          Authorization: `Bearer ${Cookies.get("token")}`,
        },
      })
      .then((res) => {
        console.log(res.data.perfis);
        Poderes(res.data.perfis);
      });
    console.log("Fetching cards...");
    api

      .get(`edital`, {
        headers: {
          Authorization: `Bearer ${Cookies.get("token")}`,
        },
      })
      .then((response) => {
        //comentei esse setCardsData porque ele impede que eu teste com os cards que tem ali em cima.
        setCardsData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div className="container-list-notice">
      <Title>Editais</Title>

      <div className="button-search">
        <Button color="terciary">
          <Plus size={24} weight="bold" />
          Cadastrar edital
        </Button>
        <Filter order={order} setOrder={setOrder} setFilter={setFilter} />
      </div>

      {filteredCards.length > 0 ? (
        filteredCards.map((card) => (
          <Card
            key={card.id}
            id={card.id}
            name={card.titulo}
            type={card.tipo}
            description={card.descricao}
            term={card.prazo}
            coordinator={card.coordenador.nome}
            requirements={card.requisitos}
            showDeleteButton={false}
            showEditButton={false}
            showShowButton={true}
          />
        ))
      ) : (
        <SubTitle>nenhum edital encontrado</SubTitle>
      )}
    </div>
  );
}

export default NoticeList;
