import { useState } from "react";
import { useNavigate } from "react-router-dom";

export function MudancaPermicao() {
    //declaraçoes
    const navigate = useNavigate();

    //constantes com useState que serao utilizadas
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [perfis, setPerfis] = useState([]);

    const [errBusca, setErrBusca] = useState(false);
    const [acertoBusca, setAcertoBusca] = useState(false);
    const [mostrarCheckBox, setMostrarCheckBox] = useState(false);
    const [adm, setAdm] = useState(false);
    const [ce, setCe] = useState(false);
    const [ci, setCi] = useState(false);
    const [cp, setCp] = useState(false);


    {/* Sobre os botoes de PODER: é mais facil fazer uma lista de nome em card com dois botoes do lado de cada um "retirar e colocar (poder)" e ai as que ja estiverem com poder o check bom fica com o tikzinho la */ }

    return (
        <>
            <div id="divGeral">
                <h1>**O adm cadastra o coordenador de curso a partir de um usuário geral ja cadastartdo**</h1>
                <br />
                <h3>Atribuir ou Retirar Permição/Poder de um determinado Usuário</h3>
                <p>Preencha o Cadastro com as informaçoes pertinentes sobre o Usuário</p>

                <fieldset id="buscaUsuario">
                    <label htmlFor="nome">Nome do usuário:</label>
                    <input id="nome" type="text"
                        onClick={(event) => setNome(event.target.value)} />
                    <br />
                    <label htmlFor="email">Email do usuário:</label>
                    <input id="email" type="email" required
                        onClick={(event) => setEmail(event.target.value)} />
                    <button
                        onClick={(event) => buscaCoordenador(event)}
                    >Buscar Usuário</button>
                    {errBusca && <span>Usuário nao encontrado no Banco de Dados, tente novamente.</span>}
                    {acertoBusca && <span>Usuário localizado no Banco de Dados, prossiga.</span>}
                </fieldset>

                <br />

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

                <br />
                <button
                    onClick={(event) => cadastrarMudanca(event)}
                    disabled={errBusca}
                >Mudar Atribuição de Poder</button>

                <button>Voltar</button>
            </div>
        </>
    );
}