import React from 'react';
import './style.css';
  
const DeleteModal = ({ onConfirm, onCancel }) => {
  return (
    <div className='modalBackground'>
      <div className='modalBox'>
        <div className='modalContent'>
          <h3>Deletar Edital</h3>
            <p>Deseja realmente deletar este edital?</p>
          <div className="modal-buttons">
            <button onClick={onCancel}>Cancelar</button>
            <button onClick={onConfirm}>Deletar</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DeleteModal;