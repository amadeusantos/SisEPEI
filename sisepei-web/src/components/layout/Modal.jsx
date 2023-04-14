import React from 'react';
import './style.css';

function Modal({ closeModal, name, type, description, term, requirements, coordinator }) {
  const baixarArquivo = () => {
    console.log("Baixando Arquivo")
  }
  return (
    <div className='modalBackground'>
      <div className='modalBox'>
        <div className='modalContent'>
          <div className='modalProp'>
            <span>Título:</span>
            <p>{name}</p>
          </div>
          <div className='modalProp'>
            <span>Coordenador:</span>
            <p>{coordinator}</p>
          </div>
          <div className='modalProp'>
            <span>Tipo:</span>
            <p>{type}</p>
          </div>
          <div className='modalProp'>
            <span>Prazo:</span>
            <p>{term}</p>
          </div>
          <div className='modalProp'>
            <span>Requisitos:</span>
            <p>{requirements}</p>
          </div>
          <div className='modalProp'>
            <span>Descrição:</span>
            <p>{description.substring(0, 600)}</p>
          </div>
          <div className='modalProp'>
            <span>Baixar Edital:</span>
            <button className="baixar"onClick={baixarArquivo}>Baixar Arquivo</button>
          </div>
        </div>
        <button className="fechar"onClick={() => closeModal(false)}>Fechar</button>
      </div>
    </div>
  )
}


export default Modal;