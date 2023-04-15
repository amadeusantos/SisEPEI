import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";

export function MudancaPermicao(){
    //declaraçoes
    const navigate =  useNavigate();

    //constantes com useState que serao utilizadas
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [perfis,setPerfis] = useState([]);

    const [errBusca, setErrBusca] = useState(false);
    const [acertoBusca, setAcertoBusca] = useState(false);
    const [mostrarCheckBox, setMostrarCheckBox] = useState(false);
    const [adm, setAdm] = useState(false);
    const [ce, setCe] = useState(false);
    const [ci, setCi] = useState(false);
    const [cp, setCp] = useState(false);


    async function buscaCoordenador(event) {
        event.preventDefault();
        
        //Esse link de busca pode estar errado
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
            Poderes(perfis),
            setAcertoBusca(true)
        ) )
        .catch((err) => (console.log(err) , setErrBusca(true)));
    }

    function Poderes(perfis) { 
        for (let index = 0; index < perfis.length; index++) {
            if(perfis[index] === "ADMINISTRADOR"){
                setAdm(true);
            }
            if(perfis[index] === "COORDENADOR_EXTENSAO"){
                setCe(true);
            }
            if(perfis[index] === "COORDENADOR_PESQUISA"){
                setCp(true);
            }
            if(perfis[index] === "COORDENADOR_INOVACAO"){
                setCi(true);
            }
        }
    }

    async function cadastrarMudanca(event) {
        event.preventDefault();

        organizaTipos();

        await api
        .post("/adm/mudarPoder", { 
            nome: nome,
            email: email,
            perfis: perfis
        })
        .then(() => (navigate("/"),
            setNome(""),
            setEmail(""),
            setPerfis([]),
            setErrBusca(false)
        ))
        .catch((err) => console.log(err));
    }

    function organizaTipos() { 
        
        setPerfis([]); //seto a lista de string pra vazia e vo concatenando oq eu quero que seja adicionado

        if(adm){
            perfis.concat("ADMINISTRADOR");
        }
        if(ce){
            perfis.concat("COORDENADOR_EXTENSAO");
        }
        if(ci){
            perfis.concat("COORDENADOR_INOVACAO");
        }
        if(cp){
            perfis.concat("COORDENADOR_PESQUISA");
        }

        setPerfis(perfis);
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
                    {acertoBusca && <span>Usuario localizado no Banco de Dados, prossiga.</span>}
                </fieldset>

                <br/>

                <fieldset id="setTipo" hidden={!mostrarCheckBox}>

                    <label htmlFor="adm" >Administrador: </label><span>{adm}</span>
                    <button id="atribuirPoder"
                    onClick={setAdm(true)}
                    >Atribuir Porder</button>
                    <button id="removerPoder"
                    onClick={setAdm(false)}
                    >Remover Porder</button>

                    <label htmlFor="adm" >Coordenador de Extenção: </label><span>{ce}</span>
                    <button id="atribuirPoder"
                    onClick={setCe(true)}
                    >Atribuir Porder</button>
                    <button id="removerPoder"
                    onClick={setCe(false)}
                    >Remover Porder</button> 

                    <label htmlFor="adm" >Coordenador de Pesquisa: </label><span>{cp}</span>
                    <button id="atribuirPoder"
                    onClick={setCp(true)}
                    >Atribuir Porder</button>
                    <button id="removerPoder"
                    onClick={setCp(false)}
                    >Remover Porder</button>  

                    <label htmlFor="adm" >Coordenador de Inovação: </label><span>{ci}</span>
                    <button id="atribuirPoder"
                    onClick={setCi(true)}
                    >Atribuir Porder</button>
                    <button id="removerPoder"
                    onClick={setCi(false)}
                    >Remover Porder</button>    
                    
                    {/**
                    <input type="checkbox" id="adm" value={"Administrador"} checked={adm} 
                    />
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
                     */}
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