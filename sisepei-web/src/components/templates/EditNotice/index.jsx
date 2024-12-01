import { useNavigate, useParams } from "react-router-dom";

import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { useEditNotice, useCurrentNotice } from "./edit-notice.store";
import { Loading } from "../../atoms/Loading";
import { useWhoami } from "../../../store/users.store";

export function EditNotice() {
  const { id } = useParams();
  const { data: user, isLoading: isLoadingUser } = useWhoami();
  const { data: notice, isLoading, isFetching } = useCurrentNotice();
  const navigate = useNavigate();
  const { mutate } = useEditNotice();
  const defaultValues = {
    titulo: notice?.title,
    descricao: notice?.description,
    requisitos: notice?.requirements,
    edital: notice?.file,
    prazo: notice?.time,
    tipo: notice?.axle,
    filename: notice?.filename,
  };

  async function onSubmit(data) {
    mutate({ id, ...data });
  }

  if (isLoading || isFetching || isLoadingUser) {
    return <Loading />;
  }

  if (notice.coordinator.id != user.id) {
    navigate("/");
  }

  return (
    <main>
      <NoticeForm
        title="Edição de Edital"
        buttonText="Editar"
        onSubmit={onSubmit}
        defaultValues={defaultValues}
      />
    </main>
  );
}
