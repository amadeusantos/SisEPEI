import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";
import './CadastroUsuarioGeral.css';


export function CadastroUsuarioGeral(){
    //declaraçoes
    const navigate =  useNavigate();

    //constantes com useState que serao utilizadas
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState(""); //A senha deve ter no minimo 6 caracteres **logica no disable
    const [confirmaSenha, setConfirmaSenha] = useState("");
    const [errEmail, setErrEmail] = useState(false);

    async function cadastrarUsuarioGeral(event) {
        event.preventDefault();

        await api
        .post("/cadastro/usuario/geral", {
            nome: nome,
            email: email,
            senha: senha,
        })
        .then(() => (navigate("/cadastro/concluido"),
            setNome(""),
            setEmail(""),
            setSenha(""),
            setConfirmaSenha("")
        ))
        .catch((err) => setErrEmail(true)); //Aqui, se o usuario tentar cadastrar um email ja cadastrado, um erro vai ser lancado, e eu teri de lidar com ele de alguma forma. Em call com amadeu, pensei em colocar uma div dentro de {boolean && <div>} que ai quando a variavel foltasse pra false, a div apareceia, toda vermelhinha, avisando que o email ja esta cadastradono bd e que agora esse emial nao pde ser cadastrado nomevanet e que o usuario, se quiser continuar o cadatro, ele deve utilizar outro email. 
    }
    

    return(
        <>
            <div id="divGeral">
                <h3>Cadastro de Usuario Geral</h3>
                <p>Preencha o Cadastro com as informaçoes pertinentes</p>

                <label htmlFor="nome">Nome:</label>
                <input id="nome" type="text" required
                onClick={(event)=> setNome(event.target.value)} />
                <br/>
                <label htmlFor="email">Email:</label>
                <input id="email" type="email" required
                onClick={(event) => (setEmail(event.target.value) , setErrEmail(false))} />
                {/* fazer uma logica ocm um botao para para verificar se no banco ja existe um emial igual a esse que o ccara esta tentando se cadastrar */}
                {
                errEmail && <span id="ErroEmail">ERRO: Email ja cadastrado!, utilize outro endereço de Email.</span>
                }{/* Esse texto do Span tem que ser VERMELHO!!! */}
                <br/>
                <label htmlFor="senha">Senha:</label>
                <input id="senha" type="password" required
                 onClick={(event)=> setSenha(event.target.value)} />
                <br/>
                <label htmlFor="confirmaSenha">Comfirme sua senha:</label>
                <input id="confirmaSenha" type="password" required
                 onClick={(event)=> setConfirmaSenha(event.target.value)} />
                <br/>
                <button
                 disable={senha.length>6  && senha !== confirmaSenha }
                 onClick={(event) => cadastrarUsuarioGeral(event)}
                >Cadastrar</button> <button>Voltar</button>
            </div>
        </>
    );
}