import React from 'react';

function ActionButton(props) {
  return (
    <div className='edit-container'>
      <button onClick={props.onClick}>
        <img src={props.src} alt={props.alt} />
      </button>
      <span>{props.label}</span>
    </div>
  );
}
export default ActionButton