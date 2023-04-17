import React, {useState} from 'react';
import Button from 'react-bootstrap/Button';
import { api } from '../../lib/Api';
import { useNavigate } from "react-router-dom";
import Form from 'react-bootstrap/Form';
import './Login.css';
import Cookies from 'js-cookie';

const Login = () => {

    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    const navigate =  useNavigate();

    async function handleSubmit(e){
        e.preventDefault();
        
        await api.post('api/auth/authenticate', {email: email, senha: senha})
        
        .then((res) =>{
            Cookies.set("token", res.data.token);
            navigate('/paginainicial')
        })

        .catch((error) => {
            console.log(error);
        })
    };
    const handleClick = () => {
        navigate('/cadastro/usuario');
    }

    return (
        <div className='frame'>
            <div className='intro'>
                <h2><strong>O que é?</strong></h2>
                <br />
                <p id='paragrafo' >O SisPEI - Sistema de Editais de Pesquisa, Extensão e Inovação é 
                um sistema de gestão de editais.</p>
                <br />
            </div>
            
            <div className="login">
                <h1 className='title'>Login</h1>
                <br />
                <Form action='' onSubmit={handleSubmit} className='form'>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        
                        <Form.Label>Email:</Form.Label>
                        <Form.Control type='email' name='email' required placeholder='Digite aqui seu email' 
                        value={email} onChange={(e) => setEmail(e.target.value)}/>
                        
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">

                        <Form.Label>Senha:</Form.Label>
                        <Form.Control type='password' name='password' required placeholder='Digite aqui sua senha' 
                        value={senha} onChange={(e) => setSenha(e.target.value)}/>
                        
                    </Form.Group>
                    <div className='buttons'>
                        <Button variant="primary" id="entry-button" type="submit" >Entrar</Button>
                        <Button variant="primary" id="register-button" onClick={handleClick} >Cadastre-se</Button>
                    </div>
                </Form>
               
            </div>
        </div>
    );
}

    export default Login;