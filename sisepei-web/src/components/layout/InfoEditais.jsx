import React from 'react';
import './style.css';
import { useState } from 'react';
import Modal from './Modal';
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
            
                <div className='view-container'>
                <button onClick={() => {setOpenModal(true);}}><svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" id="eye">
                    <path fill="none" d="M0 0h48v48H0z"></path>
                    <path d="M24 9C14 9 5.46 15.22 2 24c3.46 8.78 12 15 22 15 10.01 0 18.54-6.22 22-15-3.46-8.78-11.99-15-22-15zm0 25c-5.52 0-10-4.48-10-10s4.48-10 10-10 10 4.48 10 10-4.48 10-10 10zm0-16c-3.31 0-6 2.69-6 6s2.69 6 6 6 6-2.69 6-6-2.69-6-6-6z"></path></svg>
                </button>
                <p>Mostrar</p>
                </div>
             </div>
             {openModal && <Modal closeModal={closeModal} />}
         </div>

  );
};

export default Card;
