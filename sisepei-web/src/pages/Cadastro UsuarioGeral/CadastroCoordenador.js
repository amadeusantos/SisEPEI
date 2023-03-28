import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";


export function CadastroCoordenador(){
    //declaraçoes
    const navigate =  useNavigate();
    let encontradoNoBancoDeDados = false;

    //constantes com useState que serao utilizadas
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [tipo, setTipo] = useState("");
    
    async function buscaCoordenador(event) {
        event.preventDefault();

        await api
        .get("/cadastro/coordenador/busca", {
            nome: nome,
            email: email
        })
        .then(() => (encontradoNoBancoDeDados = true) )
        .catch((err) => console.log(err));
    }

    async function cadastrarCoordenador(event) {
        event.preventDefault();

        await api
        .post("/cadastro/coordenador", {
            nome: nome,
            email: email,
            tipo: tipo
        })
        .then(() => navigate("/cadastro/coordenador"))
        .catch((err) => console.log(err));

        setNome("");
        setEmail("");
        setTipo("");
        encontradoNoBancoDeDados = false;
    }

    return(
        <>
            <div id="divGeral">
                <h1>O adm cadastra o coordenador de curso a partir de um usuario geral ja cadastartdo</h1>
                <br/>
                <h3>Cadastro de Coordenador no Sistema</h3>
                <p>Preencha o Cadastro com as informaçoes pertinentes sobre o coordenador</p>

                <fieldset id="buscaUsuario">
                    <label htmlFor="nome">Nome:</label>
                    <input id="nome" type="text" required
                    onClick={(event)=> setNome(event.target.value)} />
                    <br/>
                    <label htmlFor="email">Email:</label>
                    <input id="email" type="email" required
                    onClick={(event)=> setEmail(event.target.value)} />
                    <button
                    onClick={(event) => buscaCoordenador(event)}
                    >Cadastrar Coordenador</button>
                </fieldset>

                <br/>
                
                <fieldset id="setTipo">
                    <label htmlFor="selectCoordenador">Selecione o Tipo de Coordenador:</label>
                    <select id="selectCoordenador" required>
                        <option value="" disabled>--- Select ---</option>
                        <option value="extensao">Coordenador de Extenção</option>
                        <option value="pesquisa">Coordenador de Pesquisa</option>
                        <option value="inovacao">Coordenador de Inovação</option>
                    </select>

                    <br/>
                    <br/>
                    
                </fieldset>

                {/* Aqui oq acontece é que o botao fica desabilitado ate a funçao buscaCoordenador retornar algo de util */}
                <button
                onClick={(event) => cadastrarCoordenador(event)}
                disabled={encontradoNoBancoDeDados === false}
                >Cadastrar Coordenador</button>

                <button>Voltar</button>
            </div>
        </>
    );
}