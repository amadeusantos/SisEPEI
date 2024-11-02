import { useQueryClient } from "@tanstack/react-query";
import { useNavigate, useParams } from "react-router-dom";
import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { useEditNotice, useFindNotice } from "./edit-notice.store";
import { base64ToFile } from "../../../utils/file";

export function EditNotice() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { data: notice, isLoading } = useFindNotice(id);
  const queryClient = useQueryClient();

  const onSuccess = () => {
    alert("Edital editado com sucesso");
    queryClient.invalidateQueries(["listNotices"]);
    navigate("/");
  };

  const onError = () => {
    alert("Ocorreu algum erro!");
  };
  const { mutate } = useEditNotice({ onSuccess, onError });
  const defaultValues = {
    titulo: notice?.title,
    descricao: notice?.description,
    requisitos: notice?.requirements,
    edital: base64ToFile(notice?.file, notice?.title),
    prazo: notice?.time,
    tipo: notice?.axle,
  };

  async function onSubmit(data) {
    mutate({ id, ...data });
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
