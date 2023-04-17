import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import './EscolhaDePersonalidade.css';
import Cookies from 'js-cookie';

export function EscolhaDePersonalidade(){

    const navigate =  useNavigate();

    const cookie = Cookies.get('token');

    const [ce, setCe] = useState(false);
    const [ci, setCi] = useState(false);
    const [cp, setCp] = useState(false);
    const [adm, setAdm] = useState(false);

    useEffect(() => {

        let perfil = cookie.perfis;

        for (let index = 0; index < perfil.length; index++) {
            if(perfil[index] === "ADMINISTRADOR"){
                setAdm(true);
            }
            if(perfil[index] === "COORDENADOR_EXTENSAO"){
                setCe(true);
            }
            if(perfil[index] === "COORDENADOR_PESQUISA"){
                setCp(true);
            }
            if(perfil[index] === "COORDENADOR_INOVACAO"){
                setCi(true);
            }
        }
        
    }, []);

    
   
    return(
        <>
        <fieldset id="menu intermedio">

            <button id="" type="button" onClick={navigate("/paginainicialCoordExtensao")} 
            disabled={!ce}
            >Coordenador de Extensão</button>

            <button id="" type="button" onClick={navigate("/paginainicialCoordPesquisa")} 
            disabled={!cp}
            >Coordenador de Pesquisa</button>

            <button id="" type="button" onClick={navigate("/paginacoodenadorinovacao")}
            disabled={!ci}
            >Coordenador de Inovação</button>
            
            {/**
            //Aqui nesse link pro ADM tem que ver como vai ser pq ele nao tem pagina inicial ne,
            //ai se precisar, so pra deixar as coisas funcinando, o adm fica somenmte com essa 
            funcionalidade de mudar poder e pa
             */}
            <button id="" type="button" onClick={navigate("/adm/mudanca/permicao")} 
            disabled={!adm}
            >Administrador</button>

            <button id="" type="button" onClick={navigate("/paginainicial")}
            >Usuario Padrão</button>

        </fieldset>
        </>
    );


}