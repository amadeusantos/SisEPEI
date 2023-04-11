import React from 'react';
import './style.css';

function Modal({ closeModal, name, type, description }) {
    return (
      <div className='modalBackground'>
        <div className='modalBox'>
          <div className='modalContent'>
            <div className='modalProp'>
              <span>Nome:</span>
              <p>{name}</p>
            </div>
            <div className='modalProp'>
              <span>Tipo:</span>
              <p>{type}</p>
            </div>
            <div className='modalProp'>
              <span>Descrição:</span>
              <p>{description}</p>
            </div>
          </div>
          <button onClick={() => closeModal(false)}>Fechar</button>
        </div>
      </div>
    )
  }
  

export default Modal;