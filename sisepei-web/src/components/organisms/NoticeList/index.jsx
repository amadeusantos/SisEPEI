import { Plus } from "@phosphor-icons/react";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

import { SubTitle, Title } from "../../atoms";
import { Button } from "../../atoms";
import Filter from "../../molecules/Filter";
import "./style.css";
import { useFilteredNoticeList } from "./notice-list.store";
import { NotFoundError } from "../../atoms/NotFoundError";
import { NoticeCard } from "../../molecules";

export function NoticeList() {
  const navigation = useNavigate();
  const [order, setOrder] = useState("");
  const [filter, setFilter] = useState("");
  const { data: filteredNotices, isLoading, isFetching, refetch } = useFilteredNoticeList(filter, order);
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

      {filteredNotices?.length > 0 ? (
        filteredNotices?.map((notice) => (
          <NoticeCard
            key={notice.id}
            id={notice.id}
            name={notice.title}
            type={notice.axle}
            description={notice.description}
            term={notice.time}
            coordinator={notice.coordinator.name}
            requirements={notice.requirements}
            showDeleteButton={false}
            showEditButton={false}
            showShowButton={true}
            filename={notice.filename}
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
