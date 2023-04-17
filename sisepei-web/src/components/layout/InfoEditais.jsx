import React from 'react';
import './style.css';
import { useState } from 'react';
import Modal from './Modal';
import BotaoDeAcao from './BotaoDeAcao';
import { useNavigate } from 'react-router-dom';

export const Card = ({id, name, coordinator, type, description, term, requirements, showEditButton, showDeleteButton }) => {
  const [openModal, setOpenModal] = useState(false);


  const [nameEdit, setNameEdit] = useState(name);
  const [coordinatorEdit, setCoordinatorEdit] = useState(coordinator);
  const [typeEdit, setTypeEdit] = useState(type);
  const [descriptionEdit, setDescriptionEdit] = useState(description);
  const [termEdit, setTermEdit] = useState(term);
  const [requirementsEdit, setRequirementsEdit] = useState(requirements);


  const navigate = useNavigate();

  const handleEditClick = () => {
    // Definir os valores dos estados com as informações do edital atual
    setNameEdit(name);
    setCoordinatorEdit(coordinator);
    setTypeEdit(type);
    setDescriptionEdit(description);
    setTermEdit(term);
    setRequirementsEdit(requirements);
  
    // Navegar para a página de edição do edital, passando as informações do edital como parâmetros
    navigate(`/editaredital/${id}`, {
      state: {
        name: nameEdit,
        coordinator: coordinatorEdit,
        type: typeEdit,
        description: descriptionEdit,
        term: termEdit,
        requirements: requirementsEdit
      }
    });
  };
  

    const closeModal = () => {
        setOpenModal(false);
      };

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
            <div ></div>
            <BotaoDeAcao
              src="https://cdn.discordapp.com/attachments/440326168491720705/1092108550400127108/delete.png"
              alt="deletar"
              label="Deletar"
              className="BotaoDeAcao"
            />
            <div ></div>
          </div>
        )}
        {showEditButton && (
          <div className="button-container">
            <div></div>
            <BotaoDeAcao
              src="https://cdn.discordapp.com/attachments/440326168491720705/1092098165030789140/pencil.png"
              alt="editar"
              label="Editar"
              className="BotaoDeAcao"
              onClick={handleEditClick}
            />
            <div></div>
          </div>
        )}

        <BotaoDeAcao
          src='https://cdn.discordapp.com/attachments/440326168491720705/1092093148815167630/eye_1.png'
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