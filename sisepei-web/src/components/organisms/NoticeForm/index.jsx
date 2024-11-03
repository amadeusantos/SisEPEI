import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./style.css";
import { Button, Field, SubTitle, Title } from "../../atoms";
import { TextField } from "../../molecules";
import { DateField } from "../../molecules/DateField";
import { SelectField } from "../../molecules/SelectField";

const fileToBase64 = async (file) => {
  return new Promise(resolve => {
    var reader = new FileReader();
    reader.onload = function (event) {
      resolve(event.target.result);
    };
    reader.readAsDataURL(file);
  });
};

export function NoticeForm({
  defaultValues,
  onSubmit,
  title,
  buttonText
}) {
  const navigate = useNavigate();

  const [titulo, setTitulo] = useState(defaultValues?.titulo ?? "");
  const [descricao, setDescricao] = useState(defaultValues?.descricao ?? "");
  const [requisitos, setRequisitos] = useState(defaultValues?.requisitos ?? "");
  const [edital, setEdital] = useState(defaultValues?.edital ?? undefined);
  const [prazo, setPrazo] = useState(defaultValues?.prazo ?? "");
  const [tipo, setTipo] = useState(defaultValues?.tipo ?? "");
  const [filename, setFilename] = useState(defaultValues?.filename ?? "");

  async function handleSubmit(event) {
    event.preventDefault();

    const file = await fileToBase64(edital);

    await onSubmit({ title: titulo, description: descricao, requirements: requisitos, file, axle: tipo, time: prazo, filename: edital?.name });

    handleClick();
  }

  const handleClick = () => {
    navigate("/");
  };

  const handleDragOver = (e) => {
    e.preventDefault();
    e.stopPropagation();
  };

  const handleDrop = (e) => {
    e.preventDefault();
    e.stopPropagation();

    const droppedFiles = Array.from(e.dataTransfer.files);

    const acceptedFiles = droppedFiles.filter((file) =>
      [
        "application/pdf",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        "application/vnd.oasis.opendocument.text",
      ].includes(file.type)
    );

    setFilename(acceptedFiles[0].name)
    setEdital(acceptedFiles[0]);
  };

  const handleFileSelect = (e) => {
    const selectedFiles = Array.from(e.target.files);

    const acceptedFiles = selectedFiles.filter((file) =>
      [
        "application/pdf",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        "application/vnd.oasis.opendocument.text",
      ].includes(file.type)
    );

    setFilename(acceptedFiles[0].name)
    setEdital(acceptedFiles[0]);
  };
  return (
    <form action="" onSubmit={handleSubmit}>
      <Title>{title}</Title>

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
        value={tipo}
        required
      />

      <Field label="Edital" name="file">
        {edital ? (
          <div
            className="editalContent"
            onDragOver={handleDragOver}
            onDrop={handleDrop}
            style={{
              cursor: "pointer",
            }}
          >
            <input
              type="file"
              multiple
              accept=".pdf,.docx,.odt,.txt"
              onChange={handleFileSelect}
              style={{ display: "none", cursor: "pointer" }}
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
                marginTop: "10px",
                cursor: "pointer"
              }}
            >
              {filename}
            </label>
          </div>
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
          {buttonText}
        </Button>
      </div>
    </form>
  );
}
