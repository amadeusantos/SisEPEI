import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./style.css";
import Cookies from "js-cookie";
import FormData from "form-data";
import { Button, Field, SubTitle, Title } from "../../atoms";
import { TextField } from "../../molecules";
import { DateField } from "../../molecules/DateField";
import { SelectField } from "../../molecules/SelectField";
import { api } from "../../../services/api"

export function NoticeForm() {
  //declaraçoes
  const navigate = useNavigate();

  //constantes com useState que serao utilizadas
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [requisitos, setRequisitos] = useState("");
  const [edital, setEdital] = useState();
  const [prazo, setPrazo] = useState("");
  const [tipo, setTipo] = useState("");
  const [errTitulo, setErrTitulo] = useState(false);

  const tipos = {
    COORDENADOR_EXTENSAO: "EXTENSAO",
    COORDENADOR_PESQUISA: "PESQUISA",
    COORDENADOR_INOVACAO: "INOVACAO",
  };

  async function cadastrarEdital(event) {
    event.preventDefault();
    //pelo oq eu vi somente isso aqui ja coloca o arquivo na request
    let bodyformData = new FormData();

    bodyformData.append("titulo", titulo);
    bodyformData.append("arquivo", edital);
    bodyformData.append("descricao", descricao);
    bodyformData.append("requisitos", requisitos);
    bodyformData.append("prazo", prazo);
    bodyformData.append("tipo", tipo);

    await api
      .post("/edital", bodyformData, {
        headers: {
          "Content-Type": "multipart/form-data",
          // Authorization: `Bearer ${Cookies.get("token")}`
        },
      })
      .then(
        () => alert("Usuario cadastrado com sucesso!"),
        // setTitulo(""),
        // setDescricao(""),
        // setRequisitos(""),
        // setEdital(""),
        // setPrazo(""),
        // setTipo(""),
        setErrTitulo(false)
      )
      .catch((err) => {
        console.log(err);
        setErrTitulo(true);
      });
  }

  const handleClick = () => {
    navigate("/");
  };

  const handleDragOver = (e) => {
    e.preventDefault();
    e.stopPropagation();
  };

  // Função chamada quando o arquivo é solto na área
  const handleDrop = (e) => {
    e.preventDefault();
    e.stopPropagation();

    const droppedFiles = Array.from(e.dataTransfer.files);

    // Filtrando os arquivos permitidos
    const acceptedFiles = droppedFiles.filter((file) =>
      [
        "application/pdf",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        "application/vnd.oasis.opendocument.text",
      ].includes(file.type)
    );

    // Armazenando os arquivos
    setEdital(acceptedFiles[0]);
  };

  // Função chamada quando o usuário clica para selecionar arquivos
  const handleFileSelect = (e) => {
    const selectedFiles = Array.from(e.target.files);

    const acceptedFiles = selectedFiles.filter((file) =>
      [
        "application/pdf",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        "application/vnd.oasis.opendocument.text",
      ].includes(file.type)
    );
    console.log(acceptedFiles[0]);
    setEdital(acceptedFiles[0]);
  };
  return (
    <form action="" onSubmit={cadastrarEdital}>
      <Title>Cadastro de Editais</Title>

      <SubTitle>
        Preencha o campos abaixo com as informaçoes pertinentes sobre o Edital
      </SubTitle>

      <TextField
        label="Titulo"
        name="title"
        value={titulo}
        onChange={setTitulo}
        type="text"
        required
      />

      <TextField
        label="Descrição"
        name="description"
        value={descricao}
        onChange={setDescricao}
        type="text"
        as="textarea"
        rows={3}
        required
      />

      <DateField
        label="Prazo"
        name="time"
        onChange={setPrazo}
        value={prazo}
        required
      />

      <TextField
        label="Requisitos"
        name="description"
        value={requisitos}
        onChange={setRequisitos}
        type="text"
        as="textarea"
        rows={3}
        required
      />

      <SelectField
        label="Tipo"
        name="axle"
        onChange={setTipo}
        values={[
          { label: "Selecione um tipo de edital" },
          { value: "EXTENSAO", label: "extensão" },
          { value: "PESQUISA", label: "pesquisa" },
          { value: "INOVACAO", label: "inovação" },
        ]}
        required
      />

      <Field label="Edital" name="file">
        {edital ? (
          <label
            style={{
              padding: "10px 20px",
              backgroundColor: "#007bff",
              color: "#fff",
              border: "none",
              borderRadius: "5px",
              marginTop: "10px",
            }}
          >
            {edital.name}
          </label>
        ) : (
          <div
            onDragOver={handleDragOver}
            onDrop={handleDrop}
            style={{
              border: "2px dashed #cccccc",
              borderRadius: "10px",
              padding: "20px",
              textAlign: "center",
              cursor: "pointer",
              backgroundColor: "#f9f9f9",
              marginBottom: "20px",
            }}
          >
            <p>Arraste e solte seus arquivos para adicionar</p>
            <input
              type="file"
              multiple
              accept=".pdf,.docx,.odt,.txt"
              onChange={handleFileSelect}
              style={{ display: "none" }}
              id="fileInput"
            />
            <label
              htmlFor="fileInput"
              style={{
                padding: "10px 20px",
                backgroundColor: "#007bff",
                color: "#fff",
                border: "none",
                borderRadius: "5px",
                cursor: "pointer",
                marginTop: "10px",
              }}
            >
              Selecione os arquivos
            </label>
          </div>
        )}
      </Field>

      <div className="space-evenly">
        <Button onClick={handleClick}>Voltar</Button>
        <Button color="secondary" type="submit">
          Cadastrar
        </Button>
      </div>
    </form>
  );
}
