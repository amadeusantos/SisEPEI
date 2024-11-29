import { useParams } from "react-router-dom";

import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { useEditNotice, useCurrentNotice } from "./edit-notice.store";

export function EditNotice() {
  const { id } = useParams();
  const { data: notice, isLoading, isFetching } = useCurrentNotice();
  const { mutate } = useEditNotice();
  const defaultValues = {
    titulo: notice?.title,
    descricao: notice?.description,
    requisitos: notice?.requirements,
    edital: notice?.file,
    prazo: notice?.time,
    tipo: notice?.axle,
    filename: notice?.filename
  };

  async function onSubmit(data) {
    mutate({ id, ...data });
  }

  if (isLoading || isFetching) {
    return null;
  }

  return (
    <div id="divGeral">
      <NoticeForm
        title="Edição de Edital"
        buttonText="Editar"
        onSubmit={onSubmit}
        defaultValues={defaultValues}
      />
    </div>
  );
}
