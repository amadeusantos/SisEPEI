import { useQueryClient } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router-dom"

import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { editNotice } from "../../../services/NoticeService";
import { useFindNotice } from "./edit-notice.store";
import { base64ToFile } from "../../../utils/file";

export function EditNotice() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { data: notice, isLoading } = useFindNotice(id);
  const queryClient = useQueryClient();
  const defaultValues = {
    titulo: notice?.title,
    descricao: notice?.description,
    requisitos: notice?.requirements,
    edital: base64ToFile(notice?.file, notice?.title),
    prazo: notice?.time,
    tipo: notice?.axle,
  }

  async function onSubmit(data) {
    await editNotice({ id, ...data })
      .then(_ => {
        alert("Edital editado com sucesso")
        queryClient.invalidateQueries(["listNotices"])
        navigate('/')
      })
      .catch(_ => {
        alert("Ocorreu algum erro!")
      })
  }

  if (isLoading) {
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
