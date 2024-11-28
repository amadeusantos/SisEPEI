import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./style.css";
import { Button, Field, SubTitle, Title } from "../../atoms";
import { TextField } from "../../molecules";
import { DateField } from "../../molecules/DateField";
import { SelectField } from "../../molecules/SelectField";
import { InboxOutlined } from "@ant-design/icons";
import { Upload } from "antd";

const { Dragger } = Upload;

const fileToBase64 = async (file) => {
  return new Promise((resolve) => {
    var reader = new FileReader();
    reader.onload = function (event) {
      resolve(event.target.result);
    };
    reader.readAsDataURL(file);
  });
};

export function NoticeForm({ defaultValues, onSubmit, title, buttonText }) {
  const navigate = useNavigate();

  const [titulo, setTitulo] = useState(defaultValues?.titulo ?? "");
  const [descricao, setDescricao] = useState(defaultValues?.descricao ?? "");
  const [requisitos, setRequisitos] = useState(defaultValues?.requisitos ?? "");
  const [edital, setEdital] = useState(defaultValues?.edital ?? undefined);
  const [prazo, setPrazo] = useState(defaultValues?.prazo ?? "");
  const [tipo, setTipo] = useState(defaultValues?.tipo ?? "");
  const [filename, setFilename] = useState(defaultValues?.filename ?? "");

  const setFile = async (file) => {
    const base64File = await fileToBase64(file);
    setFilename(file.name);
    setEdital(base64File);
    return false;
  };

  async function handleSubmit(event) {
    event.preventDefault();

    console.log(prazo);

    await onSubmit({
      title: titulo,
      description: descricao,
      requirements: requisitos,
      file: edital,
      axle: tipo,
      time: prazo,
      filename: filename,
    });

    handleClick();
  }

  const handleClick = () => {
    navigate("/");
  };

  return (
    <form onSubmit={handleSubmit}>
      <Title>{title}</Title>

      <SubTitle>
        Preencha o campos abaixo com as informaçoes pertinentes sobre o Edital
      </SubTitle>

      <TextField
        label="Titulo"
        name="title"
        value={titulo}
        onChange={setTitulo}
        rules={[{min: 8, message: "Coloque um título com mínimo 8 letra"}]}
        required
      />

      <TextField
        label="Descrição"
        name="description"
        value={descricao}
        onChange={setDescricao}
        type="TextArea"
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
        name="requirements"
        value={requisitos}
        onChange={setRequisitos}
        type="TextArea"
        rows={3}
        required
      />

      <SelectField
        label="Tipo"
        name="axle"
        onChange={setTipo}
        options={[
          { label: "Selecione um tipo de edital" },
          { value: "EXTENSAO", label: "extensão" },
          { value: "PESQUISA", label: "pesquisa" },
          { value: "INOVACAO", label: "inovação" },
        ]}
        value={tipo}
        required
      />

      <Field label="Edital" name="file" required>
        {edital ? (
          <Upload
            maxCount={1}
            onRemove={() => setEdital(null)}
            action={setFile}
            fileList={[{ name: filename, uid: "-1", status: "done" }]}
          />
        ) : (
          <Dragger
            fileList={[]}
            onRemove={() => setEdital(null)}
            accept=".pdf,.docx,.odt,.txt"
            maxCount={1}
            beforeUpload={setFile}
          >
            <p className="ant-upload-drag-icon">
              <InboxOutlined />
            </p>
            <p className="ant-upload-text">
              Arraste e solte seus arquivos para adicionar
            </p>
            <p className="ant-upload-hint">Selecione os arquivos</p>
          </Dragger>
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
