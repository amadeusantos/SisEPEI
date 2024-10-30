import React from 'react';
import './style.css';
function BotaoDeAcao(props) {
  return (
    <div className='edit-container'>
      <button className={props.className} onClick={props.onClick}>
        {props.src}
      </button>
      <span>{props.label}</span>
    </div>
  );
}
export default BotaoDeAcao;