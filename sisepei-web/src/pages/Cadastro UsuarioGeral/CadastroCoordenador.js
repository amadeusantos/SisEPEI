import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";


export function CadastroCoordenador(){
    //declaraçoes
    const navigate =  useNavigate();

    //constantes com useState que serao utilizadas
    const [tipo, setTipo] = useState("");
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [confirmaSenha, setConfirmaSenha] = useState("");

    async function cadastrarCoordenador(event) {
        event.preventDefault();

        await api
        .post("usuario/coordenador/cadastro", {
            tipo: tipo,
            nome: nome,
            email: email,
            senha: senha,
        })
        .then(() => navigate("cadastro/concluido"))
        .catch((err) => console.log(err));

        setTipo("");
        setNome("");
        setEmail("");
        setSenha("");
        setConfirmaSenha("");
    }
    
    return(
        <>
            <div id="divGeral">
                <h1>Quem faz cadastro de Coordenador é o ADMIN !! Mas o adm que define a sneha do Coordenador ou terá um sistema de "primeira acesso" onde o coordenador sera aconselhado a definir uma nova senha?</h1>
                <h3>Cadastro de Coordenador no Sistema</h3>
                <p>Preencha o Cadastro com as informaçoes pertinentes sobre o coordenador</p>

                <label htmlFor="selectCoordenador">Selecione o Tipo de Coordenador:</label>
                <select id="selectCoordenador" required>
                    <option value="" disabled>--- Select ---</option>
                    <option value="extensao">Coordenador de Extenção</option>
                    <option value="pesquisa">Coordenador de Pesquisa</option>
                    <option value="inovacao">Coordenador de Inovação</option>
                </select>

                <label htmlFor="nome">Nome:</label>
                <input id="nome" type="text" required
                onClick={(event)=> setNome(event.target.value)} />
                <br/>
                <label htmlFor="email">Email:</label>
                <input id="email" type="email" required
                onClick={(event)=> setEmail(event.target.value)} />
                <br/>
                <label htmlFor="senha">Senha:</label>
                <input id="senha" type="password" 
                 onClick={(event)=> setSenha(event.target.value)} />
                <br/>
                <label htmlFor="confirmaSenha">Comfirme sua senha:</label>
                <input id="confirmaSenha" type="password" 
                 onClick={(event)=> setConfirmaSenha(event.target.value)} />
                <br/>
                <button
                 onClick={(event) => cadastrarCoordenador(event)}
                >Cadastrar</button> <button>Voltar</button>
            </div>
        </>
    );
}