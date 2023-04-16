import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";

export function EscolhaDePersonalidade(){

    const navigate =  useNavigate();
    const [ce, setCe] = useState(false);
    const [ci, setCi] = useState(false);
    const [cp, setCp] = useState(false);
    const [adm, setAdm] = useState(false);


    useEffect(() => {
        const token  = Cookies.get("token");
        
        let perfil = token.perfis

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

            <button id="" type="button" onClick={navigate("")} //falta rota!!
            disabled={!ce}
            >Coordenador de Extensão</button>

            <button id="" type="button" onClick={navigate("")} //falta rota!!
            disabled={!cp}
            >Coordenador de Pesquisa</button>

            <button id="" type="button" onClick={navigate("/paginacoodenadorinovacao")}
            disabled={!ci}
            >Coordenador de Inovação</button>

            <button id="" type="button" onClick={navigate("")} //falta rota!!
            disabled={!adm}
            >Administrador</button>

            <button id="" type="button" onClick={navigate("/paginainicial")}
            >Usuario Padrão</button>

        </fieldset>
        </>
    );


}