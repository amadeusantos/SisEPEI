import React from 'react';

import './style.css';
import { findNoticeFile } from '../../services/NoticeService';
import { base64ToFile } from '../../utils/file'
import Button from './Button';

function Modal({ id, closeModal, name, type, description, term, requirements, coordinator, filename }) {
  const baixarArquivo = async () => {
    try {
      const response = await findNoticeFile(id);
      console.log(response.toString());
      const file = base64ToFile(response.toString(), filename)
      const url = window.URL.createObjectURL(file);
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute(
        "download",
        file.name
      );
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    } catch (error) {
      warningNotification("Ocorreu um erro ao realizar o download");
    }
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
            <p>{description.substring(0, 255)}</p>
          </div>
        </div>
        <div className='modalProp'>
          <Button onClick={baixarArquivo}>Baixar Arquivo</Button>
        </div>
        <div
          style={{
            display: 'flex',
            width: '100%',
            paddingTop: '16px'
          }}
        >
          <Button onClick={() => closeModal(false)}>Fechar</Button>
        </div>
      </div>
    </div>
  )
}


export default Modal;