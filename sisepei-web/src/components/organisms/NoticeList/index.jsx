import { Plus } from "@phosphor-icons/react";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

import Card from "../../atoms/InfoEditais";
import { SubTitle, Title } from "../../atoms";
import { Button } from "../../atoms";
import Filter from "../../molecules/Filter";
import "./style.css";
import { useFilteredNoticeList } from "./notice-list.store";
import { NotFoundError } from "../../atoms/NotFoundError";

export function NoticeList() {
  const navigation = useNavigate();
  const [order, setOrder] = useState("");
  const [filter, setFilter] = useState("");
  const { data: filteredCards, isLoading, isFetching, refetch } = useFilteredNoticeList(filter, order);
  const navigationCreateNotice = () => navigation("new/notices");

  if (isLoading || isFetching) {
    return null;
  }

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

      {filteredCards?.length > 0 ? (
        filteredCards?.map((card) => (
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
        <NotFoundError>
          <SubTitle>Nenhum edital encontrado...</SubTitle>
          <Button onClick={refetch}>Tentar novamente</Button>
        </NotFoundError>
      )}
    </div>
  );
}

export default NoticeList;
