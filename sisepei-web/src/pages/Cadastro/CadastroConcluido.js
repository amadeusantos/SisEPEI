import { useNavigate } from "react-router-dom";


export function CadastroConcluido(){

    const navigate =  useNavigate();

    return (
        <>
            <div>
                <h2>Cadastro Concluido</h2>
                <button onClick={navigate(-1)}>Voltar</button>
            </div>
        </>
    );
}