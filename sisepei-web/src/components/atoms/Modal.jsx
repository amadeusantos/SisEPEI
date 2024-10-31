import React from 'react';
import './style.css';
import Cookies from 'js-cookie';
import { findNoticeFile } from '../../services/NoticeService';

function base64ToFile(base64String, fileName) {
  const base64Data = base64String.split(',')[1];

  const typeMime = base64String.split(',')[0].replace("data:", "").replace(" ", "").replace(";base64", "");

  const byteCharacters = atob(base64Data);
  const byteNumbers = new Array(byteCharacters.length);

  for (let i = 0; i < byteCharacters.length; i++) {
      byteNumbers[i] = byteCharacters.charCodeAt(i);
  }

  const byteArray = new Uint8Array(byteNumbers);

  return new File([byteArray], `${fileName}.${typeMime.split("/")[1]}`, { type: typeMime });
}

function Modal({id, closeModal, name, type, description, term, requirements, coordinator }) {
  const baixarArquivo = async () => {
    try {
      const response = await findNoticeFile(id);
      console.log(response.toString());
      const file = base64ToFile(response.toString(), `${name}-${coordinator}`)
      const url = window.URL.createObjectURL(file);
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute(
        "download",
        file.name
      ); // Nome do arquivo a ser baixado
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