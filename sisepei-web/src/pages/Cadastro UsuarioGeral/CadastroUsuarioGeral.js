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
    const [senha, setSenha] = useState("");
    const [confirmaSenha, setConfirmaSenha] = useState("");

    async function cadastrarUsuarioGeral(event) {
        event.preventDefault();

        await api
        .post("/cadastro/usuario/geral", {
            nome: nome,
            email: email,
            senha: senha,
        })
        .then(() => navigate("/cadastro/concluido"))
        .catch((err) => console.log(err));

        setNome("")
        setEmail("")
        setSenha("")
        setConfirmaSenha("")
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
                onClick={(event)=> setEmail(event.target.value)} />
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
                 onClick={(event) => cadastrarUsuarioGeral(event)}
                >Cadastrar</button> <button>Voltar</button>
            </div>
        </>
    );
}