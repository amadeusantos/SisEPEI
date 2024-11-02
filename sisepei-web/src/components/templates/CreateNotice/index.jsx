import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { useCreateNotice } from "./create-notice.store";
import { useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";

export function CreateNotice() {
  const navigate = useNavigate();
  const queryClient = useQueryClient();
  const onSuccess = () => {
    alert("Edital editado com sucesso");
    queryClient.invalidateQueries(["listNotices"]);
    navigate("/");
  };
  const { mutate } = useCreateNotice({ onSuccess });
  return (
    <div id="divGeral">
      <NoticeForm
        title="Cadastro de Editais"
        buttonText="Cadastrar"
        onSubmit={mutate}
      />
    </div>
  );
}
