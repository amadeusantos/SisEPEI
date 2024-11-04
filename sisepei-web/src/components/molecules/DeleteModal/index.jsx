import React from "react";
import "./style.css";
import { Button } from "../../atoms";

export const DeleteModal = ({ onConfirm, onCancel }) => {
  return (
    <div className="modalBackground">
      <div className="modalBox">
        <div className="modalContent">
          <h3>Deletar Edital</h3>
          <p>Deseja realmente deletar este edital?</p>
          <div className="modal-buttons">
            <Button onClick={onCancel}>Cancelar</Button>
            <Button onClick={onConfirm}>Deletar</Button>
          </div>
        </div>
      </div>
    </div>
  );
};
