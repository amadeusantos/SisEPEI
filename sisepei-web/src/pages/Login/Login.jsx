import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Login.css';


const Login = () => {
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
                <Form action='' className='form'>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        
                        <Form.Label>Email:</Form.Label>
                        <Form.Control type='email' name='email' id='email' placeholder='Digite aqui seu email' />
                        
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicPassword">

                        <Form.Label htmlFor='password'>Senha:</Form.Label>
                        <Form.Control type='password' name='password' id='password' placeholder='Digite aqui sua senha' />
                        
                    </Form.Group>
                    <div className='buttons'>
                        <Button variant="primary" id='entry-button' type="submit">Entrar</Button>
                        <Button variant="primary" id='register-button' onClick='' >Cadastre-se</Button>
                    </div>
                </Form>
               
            </div>
        </div>
    );
}

    export default Login;