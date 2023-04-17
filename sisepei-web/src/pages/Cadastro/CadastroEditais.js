import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api } from "../../lib/Api";
import "./CadastroEditais.css";
import Cookies from 'js-cookie';
import FormData from "form-data";

//Se der errado: usar esse link como ref: https://www.bezkoder.com/react-file-upload-axios/
//Usar formData pra lidar com o arquivo pdf que sej anexado e depois dar um append no arquivo pro
export function CadastroEditais(){
    //declaraçoes
    const navigate =  useNavigate();

    //constantes com useState que serao utilizadas
    const [titulo, setTitulo] = useState("");
    const [descricao, setDescricao] = useState("");
    const [requisitos, setRequisitos] = useState("");
    const [edital, setEdital] = useState();
    const [prazo,setPrazo] = useState("");
    const [tipo, setTipo] = useState("");
    const [errTitulo, setErrTitulo] = useState(false);

    const tipos = {
        COORDENADOR_EXTENSAO: "EXTENSAO",
        COORDENADOR_PESQUISA: "PESQUISA",
        COORDENADOR_INOVACAO: "INOVACAO"
    }


    async function cadastrarEdital(event) {
        event.preventDefault();
        //pelo oq eu vi somente isso aqui ja coloca o arquivo na request
        let bodyformData = new FormData();
        
        bodyformData.append("titulo", titulo);
        bodyformData.append("arquivo", edital);
        bodyformData.append("descricao", descricao);
        bodyformData.append("requisitos", requisitos);
        bodyformData.append("prazo", prazo);
        bodyformData.append("tipo", tipo);

        console.log("1");
        await api
        .post("/edital", bodyformData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                Authorization: `Bearer ${Cookies.get("token")}`
            }
          }
        )
        .then(() => alert("Usuario cadastrado com sucesso!"),
            // setTitulo(""),
            // setDescricao(""),
            // setRequisitos(""),
            // setEdital(""),
            // setPrazo(""),
            // setTipo(""),
            setErrTitulo(false)
        )
        .catch((err) => {
            console.log(err)
            setErrTitulo(true)
        });
    }

    const handleClick = () => {
        navigate('/');
    }

    return(
       
        <>
            <div id="divGeral">
                <div className="h3">
                <h3>Cadastro de Editais</h3>
                </div>

                <div className="p">
                <p>Preencha o campos abaixo com as informaçoes pertinentes sobre o Edital</p>
                </div>

                <label htmlFor="prazo">Prazo:</label>
                <input id="prazo" type="date" 
                onChange={(event)=> setPrazo(event.target.value)} />
                <br/>

                <label htmlFor="titulo">Titulo:</label>
                <input id="titulo" type="text" required
                onChange={(event)=> setTitulo(event.target.value)} />
                {errTitulo && <span id="errTitulo">Este Titulo ja esta em uso, tente novamnete.</span>}
                <br/>

                <label htmlFor="descricao">Descrição:</label>
                <textarea id="descricao" required
                onChange={(event)=> setDescricao(event.target.value)} />
                <br/>

                <label htmlFor="requisitos">Requisitos:</label>
                <textarea id="requisitos" required
                onChange={(event)=> setRequisitos(event.target.value)} />
                <br/>

                <label htmlFor="tipo">Tipo:</label>
                <select id="tipo" required
                onChange={(event)=> setTipo(event.target.value)}>
                    {Cookies.get("perfis").split(",").map((perfil) => <option value={tipos[perfil]}>{tipos[perfil]}</option>)}
                </select>

                <label htmlFor="edital">Edital:</label>
                <input id="edital" type="file" accept=".doc,.docx,.pdf,.txt"
                onChange={(event)=> {
                    setEdital(event.target.files.item(0))
                    }} />
                <br/>
                <button
                 onClick={(event) => (cadastrarEdital(event),setErrTitulo(false))}

                >Cadastrar</button> 
                <button onChange={handleClick}>Voltar</button>

            </div>
        </>
    );
     
}