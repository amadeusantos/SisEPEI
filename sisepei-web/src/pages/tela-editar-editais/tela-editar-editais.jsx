import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { api } from "../../lib/Api";
import "./style.css";

export function EditarEditais() {
  const navigate = useNavigate();
  const { id } = useParams();

  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [requisitos, setRequisitos] = useState("");
  const [edital, setEdital] = useState("");
  const [prazo, setPrazo] = useState("");
  const [tipo, setTipo] = useState("");
  const [coordenador, setCoordenador] = useState("");
  const [errTitulo, setErrTitulo] = useState(false);

  useEffect(() => {
    async function getEdital() {
      const response = await api.get(`/edital/${id}`);
      const edital = response.data;
      setTitulo(edital.titulo);
      setDescricao(edital.descricao);
      setRequisitos(edital.requisitos);
      setEdital(edital.edital);
      setPrazo(edital.prazo);
      setTipo(edital.tipo);
      setCoordenador(edital.coordenador);
    }

    getEdital();
  }, [id]);

  async function atualizarEdital(event) {
    event.preventDefault();

    await api
      .put(`/edital/${id}`, {
        titulo: titulo,
        descricao: descricao,
        requisitos: requisitos,
        edital: edital,
        prazo: prazo,
        tipo: tipo,
        coordenador: coordenador,
      })
      .then(() =>
        navigate("/cadastro/concluido", {
          state: { mensagem: "Edital atualizado com sucesso!" },
        })
      )
      .catch((err) => setErrTitulo(true));
  }

  return (
    <>
        <div id="divGeral">
            <h3>Edição de Edital</h3>
            <p>Edite as informações abaixo e clique em Salvar para atualizar o edital:</p>

            <label htmlFor="Coordenador">Coordenador:</label>
            <input id="Coordenador" type="text" value={coordenador} disabled />

            <br />

            <label htmlFor="tipo">Tipo:</label>
            <select id="tipo" required value={tipo} onChange={(event) => setTipo(event.target.value)}>
                <option value="">--- Selecione um Tipo ---</option>
                <option value={"extencao"}>Extenção</option>
                <option value={"inovacao"}>Inovação</option>
                <option value={"pesquisa"}>Pesquisa</option>
            </select>

            <br />

            <label htmlFor="prazo">Prazo:</label>
            <input id="prazo" type="date" value={prazo} onChange={(event) => setPrazo(event.target.value)} />

            <br />

            <label htmlFor="titulo">Titulo:</label>
            <input id="titulo" type="text" required value={titulo} onChange={(event) => setTitulo(event.target.value)} />
            {errTitulo && <span id="errTitulo">Este Titulo ja esta em uso, tente novamente.</span>}
            <br />

            <label htmlFor="descricao">Descrição:</label>
            <textarea id="descricao" required value={descricao} onChange={(event) => setDescricao(event.target.value)} />

            <br />

            <label htmlFor="requisitos">Requisitos:</label>
            <textarea id="requisitos" required value={requisitos} onChange={(event) => setRequisitos(event.target.value)} />

            <br />

            <label htmlFor="edital">Edital:</label>
            <input id="edital" type="file" accept=".doc,.docx,.pdf,.txt" onChange={(event) => setEdital(event.target.files[0])} />

            <br />

            <button disabled={titulo.length < 5} onClick={(event) => (atualizarEdital(event), setErrTitulo(false))}>
                Salvar
            </button>{""}
            <button onClick={() => navigate("/editais")}>Cancelar</button>
        </div>
    </>
);
        }

export default EditarEditais;