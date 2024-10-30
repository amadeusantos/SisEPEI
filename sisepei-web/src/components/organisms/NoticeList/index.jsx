import Card from "../../atoms/InfoEditais";
import { SubTitle, Title } from "../../atoms";
import { Button } from "../../atoms";
import { Plus } from "@phosphor-icons/react";
import { useState, useEffect } from "react";
import Filter from "../../molecules/Filter";
import "./style.css";
import { useNavigate, useSearchParams } from "react-router-dom";
import Cookies from "js-cookie";
import { listNotices } from "../../../services/NoticeService";
import { useNoticeList } from "./useNoticeList";

export function NoticeList() {
  const navigation = useNavigate();
  const [order, setOrder] = useState("");
  const [filter, setFilter] = useState("");
  const [cardsData, setCardsData] = useState([]);
  const { data, error, isLoading, isError } = useNoticeList();

  const navigationCreateNotice = () => navigation("new/notices");

  if (isLoading) {
    return;
  }

  if (isError && (error.status == 403 || error.status == 401)) {
    return navigation("/login")
  }

  const filteredCards = data
    .filter(
      (card) =>
        card.title.toLowerCase().includes(filter.toLowerCase()) ||
        card.axle.toLowerCase().includes(filter.toLowerCase())
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

  const listNoticesQuery = async () => {
    const data = await listNotices();
    console.log(data)
    setCardsData(data);
  };


  return (
    <div className="container-list-notice">
      <Title>Editais</Title>

      <div className="button-search">
        <Button color="terciary" onClick={navigationCreateNotice}>
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
            name={card.title}
            type={card.axle}
            description={card.description}
            term={card.time}
            coordinator={card.coordinator.name}
            requirements={card.requirements}
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
