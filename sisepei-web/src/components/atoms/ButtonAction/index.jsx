export function ButtonAction({className, onClick, icon, label}) {
    return (
      <div className='edit-container'>
        <button className={className} onClick={onClick}>
          {icon}
        </button>
        <span>{label}</span>
      </div>
    );
  }