import React from 'react';
import './style.css';

function Modal({ closeModal }) {
    return (
        <div className='modalBackground'>
            <div className='modalBox'>
                <span>Nome:</span>
                <p>Tipo:</p>
                <span>Descrição:</span>
                <button onClick={() => closeModal(false)}>Fechar</button>
            </div>
        </div>
    )
}

export default Modal;