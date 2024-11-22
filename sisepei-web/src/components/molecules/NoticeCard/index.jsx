import { Eye, PencilSimple, Trash } from "@phosphor-icons/react";
import React, { useState } from "react";
import { useQueryClient } from "@tanstack/react-query";
import { Link } from "react-router-dom";

import "./style.css";
import { DeleteModal } from "../../molecules";
import { deleteNotice } from "../../../services/NoticeService";
import { ButtonAction } from "../../atoms";
import { NoticeModal } from "../../organisms";

export const NoticeCard = ({
  id,
  name,
  coordinator,
  type,
  description,
  term,
  requirements,
  filename,
}) => {
  const [openModal, setOpenModal] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const queryClient = useQueryClient();

  const handleConfirmDelete = async () => {
    await deleteNotice(id)
      .then((_) => {
        queryClient.invalidateQueries(["listNotices"]);
      })
      .catch((_) => {
        alert("Algum erro ocorreu!");
      });

    setShowModal(false);
  };

  const handleCancelDelete = () => {
    setShowModal(false);
  };

  const closeModal = () => {
    setOpenModal(false);
  };


  return (
    <div className="card-body" role='notice'>
      <div className="card-summary">
        <div className="nomeedital">
          <h3>Nome:</h3>
          <p>{name}</p>
        </div>
        <div className="type">
          <h3>Tipo:</h3>
          <p>{type}</p>
        </div>
        <div className="description">
          <h3>Descrição:</h3>
          <p>{description}</p>
        </div>
      </div>

      <div className="actions">
        {
          <div className="button-container">
            <Link className="link" to={`/edit/notices/${id}`}>
              <ButtonAction
                icon={<PencilSimple size={42} weight="fill" />}
                alt="editar"
                label="Editar"
                className="BotaoDeAcao"
              />
            </Link>
          </div>
        }

        {
          <div className="button-container">
            <ButtonAction
              icon={<Trash size={42} weight="fill" />}
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
        }

        <ButtonAction
          icon={<Eye size={42} weight="fill" />}
          alt="mostrar"
          label="Mostrar"
          onClick={() => setOpenModal(true)}
          className="BotaoDeAcao"
        />
      </div>
      {openModal && (
        <NoticeModal
          id={id}
          closeModal={closeModal}
          name={name}
          type={type}
          description={description}
          requirements={requirements}
          term={term}
          coordinator={coordinator}
          filename={filename}
        />
      )}
    </div>
  );
};
