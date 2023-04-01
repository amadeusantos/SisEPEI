import './style.css';
import { useNavigate } from 'react-router-dom';
export function BotaoCadastrar(){
    const navigate = useNavigate()
    const handleClick = () =>{
        //Colocar a rota correta
        navigate('/cadastrar')
    }

    return(
            <button className="my-button" onClick={handleClick}>Cadastrar Edital</button>
    )
}
export default BotaoCadastrar;