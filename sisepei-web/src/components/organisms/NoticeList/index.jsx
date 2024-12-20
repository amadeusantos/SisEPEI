import { Plus } from "@phosphor-icons/react";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { Select } from "antd";
import { SubTitle, Title } from "../../atoms";
import { Button } from "../../atoms";
import Filter from "../../molecules/Filter";
import "./style.css";
import { useFilteredNoticeList } from "./notice-list.store";
import { NotFoundError } from "../../atoms/NotFoundError";
import { NoticeCard } from "../../molecules";
import { Loading } from "../../atoms/Loading";
import { useWhoami } from "../../../store/users.store";

export function NoticeList() {
  const { data: user, isLoading: isLoadingUser } = useWhoami();
  const navigation = useNavigate();
  const [order, setOrder] = useState("");
  const [filter, setFilter] = useState("");
  const {
    data: filteredNotices,
    isLoading,
    isFetching,
    refetch,
  } = useFilteredNoticeList(filter, order);
  const navigationCreateNotice = () => navigation("new/notices");
  const profiles = user ? user.profiles.map((profile) => profile.name) : [];

  if (isLoading || isFetching || isLoadingUser) {
    return <Loading />;
  }

  const handleChange = (value) => {
    if (value == "TODOS") {
      setFilter("");
    } else {
      setFilter(value);
    }
  };

  return (
    <div className="container-list-notice">
      <Title>Editais</Title>
      <div
        className={
          [
            "COORDENADOR_EXTENSAO",
            "COORDENADOR_PESQUISA",
            "COORDENADOR_INOVACAO",
          ].some((profileRequest) => profiles.includes(profileRequest))
            ? "button-search"
            : "button-search-flex"
        }
      >
        {[
          "COORDENADOR_EXTENSAO",
          "COORDENADOR_PESQUISA",
          "COORDENADOR_INOVACAO",
        ].some((profileRequest) => profiles.includes(profileRequest)) && (
          <Button color="terciary" onClick={navigationCreateNotice}>
            <Plus size={24} weight="bold" />
            Cadastrar edital
          </Button>
        )}
        <div className="filters">
          <Select
            defaultValue="TODOS"
            style={{ width: 120, height: 40, marginRight: 10 }}
            onChange={handleChange}
            options={[
              { value: "TODOS", label: "TODOS" },
              { value: "EXTENSAO", label: "EXTENSÃO" },
              { value: "PESQUISA", label: "PESQUISA" },
              { value: "INOVACAO", label: "INOVAÇÃO" },
            ]}
          />

          <Filter order={order} setOrder={setOrder} setFilter={setFilter} />
        </div>
      </div>
      <div className="content-list-notice">
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
              isCoordinator={notice.coordinator.id == user.id}
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
    </div>
  );
}

export default NoticeList;
