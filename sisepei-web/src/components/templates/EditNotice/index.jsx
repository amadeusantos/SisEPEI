import { useQueryClient } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router-dom"

import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { editNotice } from "../../../services/NoticeService";
import { useFindNotice } from "./edit-notice.store";

export function EditNotice() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { data: notice, isLoading } = useFindNotice(id);
  const queryClient = useQueryClient();
  console.log(notice?.file)
  const defaultValues = {
    titulo: notice?.title,
    descricao: notice?.description,
    requisitos: notice?.requirements,
    edital: new File([new Blob([notice?.file])], notice?.title),
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
