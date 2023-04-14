import "./CadastroNovoAnimal.css"
import { useForm } from 'react-hook-form';
import axios from 'axios';


const CadastroNovoAnimal = () => {

    const { register , handleSubmit } = useForm();

    const onsubmit = (dataa: any) => axios.post('http://localhost:3333/animal/new/p', dataa)
    .then(()=>{console.log(dataa)})
    .catch(()=>{console.log("Deu ERRADO")})

    return (
        <>
            <form onSubmit={ () => handleSubmit(onsubmit) }>
            <fieldset>
                <h3>Cadastro de novo pet</h3>
                <label htmlFor="nome">Nome:</label>
                <input type="text" id="nome" {...register("nome")} required />
                <br/>
                <label htmlFor="raca">Raça:</label>
                <input type="text" id="raca" {...register("raca")} required/>
                <br/>
                <br/>
                <label htmlFor="idade">Idade:</label>
                <input type="number" id="idade" {...register("idade")} />
                <br/>
                <br/>
                <label htmlFor="genero">Genero:</label>
                <input type="text" id="genero" {...register("genero")} required/>
                <br/>
                <label htmlFor="personalidade">Personalidade:</label>
                <textarea id="personalidade" {...register("personalidade")} ></textarea>
                <br/>
                <label htmlFor="necessidadesMedicas">Condições médicas:</label>
                <textarea id="necessidadesMedicas" {...register("necessidadesMedicas")}></textarea>
                <br/>
                <label htmlFor="necessidadesComportamentais">Comportamento:</label>
                <textarea id="necessidadesComportamentais" {...register("necessidadesComportamentais")} ></textarea>
                <br/>
                <label htmlFor="foto">Foto:</label>
                <input type="file" id="foto" name="foto" />
                <br/>
                <br/>
                <button type="submit">Cadastrar pet</button>
            </fieldset>
            </form>
        </>
    );
}

export default CadastroNovoAnimal;