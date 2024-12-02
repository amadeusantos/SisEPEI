import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./style.css";
import { Button, Field, SubTitle, Title } from "../../atoms";
import { TextField } from "../../molecules";
import { DateField } from "../../molecules/DateField";
import { SelectField } from "../../molecules/SelectField";
import { InboxOutlined } from "@ant-design/icons";
import { Form, Upload } from "antd";
import dayjs from "dayjs";
import { useWhoami } from "../../../store/users.store";
import { Loading } from "../../atoms/Loading";

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
  const { data: user, isLoading: isLoadingUser } = useWhoami();
  const [titulo, setTitulo] = useState(defaultValues?.titulo ?? "");
  const [descricao, setDescricao] = useState(defaultValues?.descricao ?? "");
  const [requisitos, setRequisitos] = useState(defaultValues?.requisitos ?? "");
  const [edital, setEdital] = useState(defaultValues?.edital ?? undefined);
  const [prazo, setPrazo] = useState(defaultValues?.prazo ?? "");
  const [tipo, setTipo] = useState(defaultValues?.tipo ?? undefined);
  const [filename, setFilename] = useState(defaultValues?.filename ?? "");
  const x = [
    ...user.profiles.map((p) => p.name),
    defaultValues?.tipo,
    undefined,
  ];
  const axleAllowed = [
    { label: "Selecione um tipo de edital" },
    { value: "EXTENSAO", label: "extensão", profile: "COORDENADOR_EXTENSAO" },
    { value: "PESQUISA", label: "pesquisa", profile: "COORDENADOR_PESQUISA" },
    { value: "INOVACAO", label: "inovação", profile: "COORDENADOR_INOVACAO" },
  ].filter((p) => x.includes(p.profile) || x.includes(p.value));

  const setFile = async (file) => {
    const base64File = await fileToBase64(file);
    setFilename(file.name);
    setEdital(base64File);
    return false;
  };

  async function handleSubmit() {
    await onSubmit({
      title: titulo,
      description: descricao,
      requirements: requisitos,
      file: edital,
      axle: tipo,
      time: prazo,
      filename: filename,
    });
  }

  const handleClick = () => {
    navigate("/");
  };

  if (isLoadingUser) {
    return <Loading />;
  }

  return (
    <Form
      id="divGeral"
      initialValues={{
        title: defaultValues?.titulo,
        description: defaultValues?.descricao,
        requirements: defaultValues?.requisitos,
        axle: defaultValues?.tipo || undefined,
        time: defaultValues?.prazo ? dayjs(defaultValues?.prazo): undefined,
      }}
      onFinish={handleSubmit}
    >
      <Title>{title}</Title>

      <SubTitle>
        Preencha o campos abaixo com as informaçoes pertinentes sobre o Edital
      </SubTitle>

      <TextField
        label="Título"
        name="title"
        value={titulo}
        onChange={setTitulo}
        rules={[
          {
            required: true,
            message: "Digite uma título!",
          },
        ]}
        required
      />

      <TextField
        label="Descrição"
        name="description"
        value={descricao}
        onChange={setDescricao}
        type="TextArea"
        rules={[
          {
            required: true,
            message: "Digite uma descrição!",
          },
        ]}
        rows={3}
        required
      />

      <DateField
        label="Prazo"
        name="time"
        onChange={setPrazo}
        value={prazo}
        rules={[
          {
            required: true,
            message: "Digite uma um prazo!",
          },
        ]}
        required
      />

      <TextField
        label="Requisitos"
        name="requirements"
        value={requisitos}
        onChange={setRequisitos}
        rules={[
          {
            required: true,
            message: "Digite os requisitos para ser aceito!",
          },
        ]}
        type="TextArea"
        rows={3}
        required
      />

      <SelectField
        label="Tipo"
        name="axle"
        onChange={setTipo}
        options={axleAllowed}
        rules={[
          {
            required: true,
            message: "Selecione um eixo!",
          },
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
            fileList={
              edital ? [{ name: filename, uid: "-1", status: "done" }] : []
            }
            rules={[
              {
                required: true,
                message: "Selecione um arquivo!",
              },
            ]}
            required
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
        <Button color="secondary" type="submit" disabled={!edital}>
          {buttonText}
        </Button>
      </div>
    </Form>
  );
}
