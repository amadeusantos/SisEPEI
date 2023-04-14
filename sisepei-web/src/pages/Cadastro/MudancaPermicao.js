import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";

export function MudancaPermicao(){
    //declaraçoes
    const navigate =  useNavigate();

    //constantes com useState que serao utilizadas
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [tipo, setTipo] = useState("");
    const [errBusca, setErrBusca] = useState(false);
    const [mostrarCheckBox, setMostrarCheckBox] = useState(false);
    const [perfis,setPerfis] = useState([]);

    let adm = false;
    let ce = false;
    let ci = false;
    let cp = false;

    async function buscaCoordenador(event) {
        event.preventDefault();

        await api
        .get("/adm/mudanca/permicao/busca", {
            nome: nome,
            email: email
        })
        .then((res) => (
            setNome(res.data.nome),
            setEmail(res.data.email),
            setPerfis(res.data.perfis),
            setMostrarCheckBox(true),
            setCheckBoxs(perfis)
        ) )
        .catch((err) => (console.log(err) , setErrBusca(true)));
    }

    function setCheckBoxs(perfis) { 
        for (let index = 0; index < perfis.length; index++) {
            if(perfis[index] === "adm"){
                adm = true;
            }
            if(perfis[index] === "Coordenador de Extenção"){
                adm = true;
            }
            if(perfis[index] === "Coordenador de Pesquisa"){
                adm = true;
            }
            if(perfis[index] === "Coordenador de Inovação"){
                adm = true;
            }
        }
    }

    async function cadastrarMudanca(event) {
        event.preventDefault();

        await api
        .post("/adm/mudarPoder", { 
            nome: nome,
            email: email,
            tipos: tipo
        })
        .then(() => (navigate("/"),
            setNome(""),
            setEmail(""),
            setTipo(""),
            setErrBusca(false)
        ))
        .catch((err) => console.log(err));
    }

    {/* Sobre os botoes de PODER: é mais facil fazer uma lista de nome em card com dois botoes do lado de cada um "retirar e colocar (poder)" e ai as que ja estiverem com poder o check bom fica com o tikzinho la */}

    return(
        <>
            <div id="divGeral">
                <h1>**O adm cadastra o coordenador de curso a partir de um usuario geral ja cadastartdo**</h1>
                <br/>
                <h3>Atribuir ou Retirar Permição/Poder de um determinado Usuario</h3>
                <p>Preencha o Cadastro com as informaçoes pertinentes sobre o Usuario</p>

                <fieldset id="buscaUsuario">
                    <label htmlFor="nome">Nome do usuario:</label>
                    <input id="nome" type="text" 
                    onClick={(event)=> setNome(event.target.value)} />
                    <br/>
                    <label htmlFor="email">Email do usuario:</label>
                    <input id="email" type="email" required
                    onClick={(event)=> setEmail(event.target.value)} />
                    <button
                    onClick={(event) => buscaCoordenador(event)}
                    >Buscar Usuario</button>
                    {errBusca && <span>Usuario nao encontrado no Banco de Dados, tente novamente.</span>}
                </fieldset>

                <br/>

                <fieldset id="setTipo" hidden={!mostrarCheckBox}>
                    <input type="checkbox" id="adm" value={"Administrador"} checked={adm} 
                    onChange={}/>
                    <label for="adm">Administrador</label>
                    <br/>

                    <input type="checkbox" id="Coordenador de Extenção" value={"Coordenador de Extenção"} checked={ce} onChange={}/>
                    <label for="Coordenador de Extenção">Coordenador de Extenção</label>
                    <br/>

                    <input type="checkbox" id="Coordenador de Pesquisa" value={"Coordenador de Pesquisa"} checked={cp} onChange={}/>
                    <label for="Coordenador de Pesquisa">Coordenador de Pesquisa</label>
                    <br/>

                    <input type="checkbox" id="Coordenador de Inovação" value={"Coordenador de Inovação"} checked={ci} onChange={}/>
                    <label for="Coordenador de Inovação">Coordenador de Inovação</label>
                    <br/>
                </fieldset>

                <br/>
                <button
                onClick={(event) => cadastrarMudanca(event)}
                disabled={errBusca}
                >Mudar Atribuição de Poder</button>

                <button>Voltar</button>
            </div>
        </>
    );
}