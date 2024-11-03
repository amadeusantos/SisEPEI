import { useParams } from "react-router-dom";

import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { useEditNotice, useCurrentNotice } from "./edit-notice.store";
import { base64ToFile } from "../../../utils/file";

export function EditNotice() {
  const { id } = useParams();
  const { data: notice, isLoading, isFetching } = useCurrentNotice();
  const { mutate } = useEditNotice();
  const defaultValues = {
    titulo: notice?.title,
    descricao: notice?.description,
    requisitos: notice?.requirements,
    edital: base64ToFile(notice?.file, notice?.title),
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
