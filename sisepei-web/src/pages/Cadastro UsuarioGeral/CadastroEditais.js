import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";


export function CadastroEditais(){
    //declaraçoes
    const navigate =  useNavigate();

    //constantes com useState que serao utilizadas
    const [titulo, setTitulo] = useState("");
    const [descricao, setDescricao] = useState("");
    const [criterio, setCriterio] = useState("");
    const [requisito, setRequisitos] = useState("");

    async function cadastrarEdital(event) {
        event.preventDefault();

        await api
        .post("/edital/cadastro", {
            titulo: titulo,
            descricao: descricao,
            criterio: criterio,
            requisito: requisito,
        })
        .then(() => navigate("/cadastro/concluido"))
        .catch((err) => console.log(err));

        setTitulo("");
        setDescricao("");
        setCriterio("");
        setRequisitos("");
    }
    
    return(
        <>
            <div id="divGeral">
                <h3>Cadastro de Editais</h3>
                <p>Preencha o campos abaixo com as informaçoes necessarias</p>

                <label htmlFor="titulo">Titulo:</label>
                <input id="titulo" type="text" required
                onClick={(event)=> setTitulo(event.target.value)} />
                <br/>

                <label htmlFor="descricao">Descrição:</label>
                <textarea id="descricao" 
                onClick={(event)=> setDescricao(event.target.value)} />
                
                <label htmlFor="criterios">Criterios:</label>
                <textarea id="criterios" 
                onClick={(event)=> setCriterio(event.target.value)} />
                
                <label htmlFor="requisitos">Requisitos:</label>
                <textarea id="requisitos" 
                onClick={(event)=> setRequisitos(event.target.value)} />

                <button
                 onClick={(event) => cadastrarEdital(event)}
                >Cadastrar</button> <button>Voltar</button>
            </div>
        </>
    );
}