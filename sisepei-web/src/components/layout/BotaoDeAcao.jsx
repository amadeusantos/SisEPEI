import React from 'react';

function BotaoDeAcao(props) {
  return (
    <div className='edit-container'>
      <button className={props.className} onClick={props.onClick}>
        <img src={props.src} alt={props.alt} />
      </button>
      <span>{props.label}</span>
    </div>
  );
}
export default BotaoDeAcao;