import React from 'react';
import './style.css';

export const Card = ({ name, type, description, date, children }) => {
  return (
      <div className="card-body">
        <div className="title">
            <h3>Nome</h3>
          <h4>{name}</h4>
        </div>
        <div className='type'>
            <h3>Tipo</h3>
            <h4>{type}</h4>
        </div>
        <div className='description'>
            <p>{description}</p>
        </div>

        <div className='date'>
        <p>{date}</p>
        </div>

        <div className="actions">
                <button>Edit</button>
                <button>Delete</button>
                <button>View</button>
        </div>
      </div>

  );
};

export default Card;
