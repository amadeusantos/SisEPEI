import React from 'react';
import './style.css';
import { useState } from 'react';
import Modal from './Modal';
import BotaoDeAcao from './BotaoDeAcao';
export const Card = ({ name, type, description, showEditButton, showDeleteButton, showShowButton}) => {
    const [openModal, setOpenModal] = useState(false);

    const closeModal = () => {
        setOpenModal(false);
      };
    //   const deletar = () => {
    //    console.log("Deletando...");
    //     Axios
    //       .delete("", {
    //         cardId: id
    //         //inserir informação do card aqui pra poder deletar, provavelmente vai ser o ID
    //       })
    //       .then((response) => {
    //         console.log(response)
    //       })
    //       .catch((error) => {
    //         console.log(error);
    //       });
    //   } 
  return (
      <div className="card-body">
        <div className="nomeedital">
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
    
        {showDeleteButton && (
          
          <div className="button-container">
            <div className="line"></div>
            <BotaoDeAcao
              src="https://cdn.discordapp.com/attachments/440326168491720705/1092108550400127108/delete.png"
              alt="deletar"
              label="Deletar"
              className="BotaoDeAcao"
            />
            <div className="line"></div>
          </div>
        )}
    {showEditButton && (
          
          <div className="button-container">
            <div className="line"></div>
            <BotaoDeAcao
              src="https://cdn.discordapp.com/attachments/440326168491720705/1092098165030789140/pencil.png"
              alt="editar"
              label="Editar"
              className="BotaoDeAcao"
            />
            <div className="line"></div>
          </div>
        )}
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