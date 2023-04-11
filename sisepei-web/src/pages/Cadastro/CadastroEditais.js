import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";
import "./CadastroEditais.css";


export function CadastroEditais(){
    //declaraçoes
    const navigate =  useNavigate();

    //constantes com useState que serao utilizadas
    const [titulo, setTitulo] = useState("");
    const [descricao, setDescricao] = useState("");
    const [requisitos, setRequisitos] = useState("");
    const [edital, setEdital] = useState("");
    const [prazo,setPrazo] = useState("");
    const [tipo, setTipo] = useState("");
    const [coordenador, setCoordenador] = useState(""); //Tem que ver como passar o coordenador, pq ele ja vai estar logado e nao necessariamente necessita colocar o nome dele denovo la ne.
    const [errTitulo, setErrTitulo] = useState(false);

    async function cadastrarEdital(event) {
        event.preventDefault();

        await api
        .post("/edital", {
            titulo: titulo,
            descricao: descricao,
            requisitos: requisitos,
            edital: edital,
            prazo: prazo,
            tipo: tipo,
            coordenador: coordenador
        })
        .then(() => (navigate("/cadastro/concluido"),
            setTitulo(""),
            setDescricao(""),
            setRequisitos(""),
            setEdital(""),
            setPrazo(""),
            setTipo(""),
            setCoordenador(""),
            setErrTitulo(false)
        ))
        .catch((err) => setErrTitulo(true));
    }
    
    return(
        <>
            <div id="divGeral">
                <h3>Cadastro de Editais</h3>
                <p>Preencha o campos abaixo com as informaçoes pertinentes sobre o Edital</p>

                <label htmlFor="Coordenador">Coordenador:</label>
                <input id="Coordenador" type="text" 
                onClick={(event)=> setCoordenador(event.target.value)} />
                <br/>

                <label htmlFor="tipo">Tipo:</label>
                <select id="tipo" required
                onChange={(event)=> setTitulo(event.target.value)}>
                    <option selected >--- Selecione um Tipo ---</option>
                    <option value={"extencao"}>Extenção</option>
                    <option value={"inovacao"}>Inovação</option>
                    <option value={"pesquisa"}>Pesquisa</option>
                </select>

                <br/>

                <label htmlFor="prazo">Prazo:</label>
                <input id="prazo" type="date" 
                onClick={(event)=> setPrazo(event.target.value)} />
                <br/>

                <label htmlFor="titulo">Titulo:</label>
                <input id="titulo" type="text" required
                onClick={(event)=> setTitulo(event.target.value)} />
                {errTitulo && <span id="errTitulo">Este Titulo ja esta em uso, tente novamnete.</span>}
                <br/>

                <label htmlFor="descricao">Descrição:</label>
                <textarea id="descricao" required
                onClick={(event)=> setDescricao(event.target.value)} />
                <br/>

                <label htmlFor="requisitos">Requisitos:</label>
                <textarea id="requisitos" required
                onClick={(event)=> setRequisitos(event.target.value)} />
                <br/>

                <label htmlFor="edital">Edital:</label>
                <input id="edital" type="file" accept=".doc,.docx,.pdf,.txt"
                onClick={(event)=> setEdital(event.target.value)} />
                <br/>

                <button
                 disabled={ titulo.length < 5 }
                 onClick={(event) => (cadastrarEdital(event),setErrTitulo(false))}
                >Cadastrar</button> <button>Voltar</button>
            </div>
        </>
    );
}