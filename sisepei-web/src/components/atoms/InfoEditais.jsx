import { Eye, PencilSimple, Trash } from '@phosphor-icons/react';
import React, { useState } from 'react';
import { useQueryClient } from '@tanstack/react-query';
import { Link } from "react-router-dom";

import './style.css';
import Modal from './Modal';
import BotaoDeAcao from './BotaoDeAcao';
import DeleteModal from './DeleteModal';
import { deleteNotice } from '../../services/NoticeService'

export const Card = ({ id, name, coordinator, type, description, term, requirements, showEditButton, showDeleteButton }) => {
  const [openModal, setOpenModal] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const queryClient = useQueryClient();

  const handleConfirmDelete = async () => {
    await deleteNotice(id)
      .then(_ => { queryClient.invalidateQueries(["listNotices"]) })
      .catch(_ => { alert('Algum erro ocorreu!') })

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
      <div className="card-summary">
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
      </div>

      <div className="actions">

        {(

          <div className="button-container">
            <Link className="link" to={`/edit/notices/${id}`}>
              <BotaoDeAcao
                src={<PencilSimple size={42} weight="fill" />}
                alt="editar"
                label="Editar"
                className="BotaoDeAcao"
              />
            </Link>
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
          id={id}
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