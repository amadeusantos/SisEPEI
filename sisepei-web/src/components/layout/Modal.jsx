import React from 'react';
import './style.css';
import { api } from '../../lib/Api';
import Cookies from 'js-cookie';

function Modal({id, closeModal, name, type, description, term, requirements, coordinator }) {
  const baixarArquivo = async () => {
    console.log("Baixando Arquivo")
    await api.get("edital/arquivo/" + id,
    {headers: {
      Authorization: `Bearer ${Cookies.get("token")}`
     }}
     ).then((resp) => {
      console.log(decodeURI(resp.data.edital));
  
    })
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