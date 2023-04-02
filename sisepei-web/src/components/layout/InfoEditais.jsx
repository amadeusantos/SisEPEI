import React from 'react';
import './style.css';
import { useState } from 'react';
import Modal from './Modal';
import BotaoDeAcao from './BotaoDeAcao';
export const Card = ({ name, type, description}) => {
    const [openModal, setOpenModal] = useState(false);

    const closeModal = () => {
        setOpenModal(false);
      };
    
  return (
      <div className="card-body">
        <div className="title">
            <h3>Nome:</h3>
            <p>{name}</p>
        </div>
        <div className='type'>
            <h3>Tipo:</h3>
            <p>{type}</p>
        </div>
        <div className='description'>
            <h3>Descrição:</h3>
            <p>{description}</p>
        </div>

        <div className="actions">
    
    <BotaoDeAcao
    src='https://cdn.discordapp.com/attachments/440326168491720705/1092108550400127108/delete.png'
    alt='excluir'
    label='Deletar'
    className='BotaoDeAcao'/>

    <div class="line"></div>

    <BotaoDeAcao
    src='https://cdn.discordapp.com/attachments/440326168491720705/1092098165030789140/pencil.png'
    alt='editar'
    label='Editar'
    className='BotaoDeAcao'/>

    <div class="line"></div>

    <BotaoDeAcao
    src='https://cdn.discordapp.com/attachments/440326168491720705/1092093148815167630/eye_1.png'
    alt='mostrar'
    label='Mostrar'
    onClick={() => setOpenModal(true)}
    className='BotaoDeAcao'/>

             </div>
             {openModal && (
        <Modal
          closeModal={closeModal}
          name={name}
          type={type}
          description={description}
        />
      )}
         </div>

  );
};

export default Card;
