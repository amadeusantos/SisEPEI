import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";
import "./EditarEdital.css";
import Cookies from 'js-cookie';
import FormData from "form-data";

export function EdicaoEdital({ editalId }) {
  // Declarações
  const navigate = useNavigate();

  // Constantes com useState que serão utilizadas
  const [titulo, setTitulo] = useState("");
  const [descricao, setDescricao] = useState("");
  const [requisitos, setRequisitos] = useState("");
  const [tipo] = useState("");
  const [edital, setEdital] = useState();
  const [prazo, setPrazo] = useState("");
  const [errTitulo, setErrTitulo] = useState(false);

  useEffect(() => {
    // Função para buscar os dados do edital do backend e preencher os campos
    const buscarEdital = async () => {
      try {
        const response = await api.get(`/edital/${editalId}`, {
          headers: {
            Authorization: `Bearer ${Cookies.get("token")}`
          }
        });
        const { titulo, descricao, requisitos, prazo } = response.data;
        setTitulo(titulo);
        setDescricao(descricao);
        setRequisitos(requisitos);
        setPrazo(prazo);
      } catch (err) {
        console.log(err);
      }
    };
    buscarEdital();
  }, [editalId]);

  async function editarEdital(event) {
    event.preventDefault();
    let bodyformData = new FormData();

    bodyformData.append("titulo", titulo);
    bodyformData.append("descricao", descricao);
    bodyformData.append("requisitos", requisitos);
    bodyformData.append("prazo", prazo);

    console.log("1");
    await api
      .put(`/edital/${editalId}`, bodyformData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${Cookies.get("token")}`
        }
      })
      .then(() => {
        alert("Edital editado com sucesso!");
        // Redirecionar para a página de listagem de editais após a edição
        navigate('/listagem-editais');
      })
      .catch((err) => {
        console.log(err);
        setErrTitulo(true);
      });
  }

  const handleClick = () => {
    navigate('/');
  }
  return (
    <>
      <div id="divGeral">
        <div className="h3">
          <h3>Editar Edital</h3>
        </div>
  
        <div className="p">
          <p>Edite as informações do Edital abaixo:</p>
        </div>
  
        <label htmlFor="titulo">Título:</label>
        <input
          id="titulo"
          type="text"
          required
          value={titulo}
          onChange={(event) => setTitulo(event.target.value)}
        />
        {errTitulo && (
          <span id="errTitulo">
            Este Título já está em uso, tente novamente.
          </span>
        )}
        <br />
  
        <label htmlFor="descricao">Descrição:</label>
        <textarea
          id="descricao"
          required
          value={descricao}
          onChange={(event) => setDescricao(event.target.value)}
        />
        <br />
  
        <label htmlFor="requisitos">Requisitos:</label>
        <textarea
          id="requisitos"
          required
          value={requisitos}
          onChange={(event) => setRequisitos(event.target.value)}
        />
        <br />
  
        <label htmlFor="tipo">Tipo:</label>
        <select id="tipo" disabled>
          <option value={tipo}>{tipo}</option>
        </select>
  
        <label htmlFor="edital">Edital:</label>
        <input
          id="edital"
          type="file"
          accept=".doc,.docx,.pdf,.txt"
          onChange={(event) => {
            setEdital(event.target.files.item(0));
          }}
        />
        <br />
        <button
          onClick={(event) => {
            editarEdital(event);
            setErrTitulo(false);
          }}
        >
          Salvar Edital
        </button>
        <button onClick={handleClick}>Voltar</button>
      </div>
    </>
  );
        }

export default EdicaoEdital;