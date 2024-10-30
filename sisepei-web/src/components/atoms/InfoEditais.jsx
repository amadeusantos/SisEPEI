import React from 'react';
import './style.css';
import { useState } from 'react';
import Modal from './Modal';
import BotaoDeAcao from './BotaoDeAcao';
import DeleteModal from './DeleteModal';
import { Eye, PencilSimple, Trash } from '@phosphor-icons/react';
export const Card = ({id, name, coordinator, type, description, term, requirements, showEditButton, showDeleteButton }) => {
  const [openModal, setOpenModal] = useState(false);
    const [showModal, setShowModal] = useState(false);
  
    const handleConfirmDelete = () => {
      setShowModal(false);
    };
  
    const handleCancelDelete = () => {
      setShowModal(false);
    };

    const closeModal = () => {
      setOpenModal(false)
    }


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

        {(

          <div className="button-container">
            <div ></div>
            <BotaoDeAcao
              src={<PencilSimple size={42} weight="fill" />}
              alt="editar"
              label="Editar"
              className="BotaoDeAcao"
            />
            <div ></div>
          </div>
        )}

         {(

          <div className="button-container">
            <BotaoDeAcao
              src={<Trash size={42} weight="fill" />}
              alt="deletar"
              label="Deletar"
              onClick={() => setShowModal(true)}
              className="BotaoDeAcao"
            />
              {showModal && (
                <DeleteModal
                  onConfirm={handleConfirmDelete}
                  onCancel={handleCancelDelete}
                />
              )}
          </div>
          
      )}

        <BotaoDeAcao
          src={<Eye size={42} weight="fill" />}
          alt='mostrar'
          label='Mostrar'
          onClick={() => setOpenModal(true)}
          className='BotaoDeAcao' />

      </div>
      {openModal && (
        <Modal
          id ={id}
          closeModal={closeModal}
          name={name}
          type={type}
          description={description}
          requirements={requirements}
          term={term}
          coordinator={coordinator}
        />
      )}
    </div>

  );
};

export default Card;