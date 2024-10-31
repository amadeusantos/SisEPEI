import "./style.css";
import { NoticeForm } from "../../organisms/NoticeForm";
import { createNotice } from "../../../services/NoticeService";

export function CreateNotice() {
  return (
    <div id="divGeral">
      <NoticeForm title="Cadastro de Editais" buttonText="Cadastrar" onSubmit={createNotice} />
    </div>
  );
}
