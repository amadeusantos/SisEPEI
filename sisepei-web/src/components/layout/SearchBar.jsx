import React from 'react';
import './style.css';

function SearchBar ({searchTerm, setSearchTerm}){
  return (
    <div className='search-bar'>
      <input 
        type="text" 
        placeholder='Buscar...' 
        value={searchTerm}
        onChange={event => 
          {setSearchTerm(event.target.value)
          }}
      />
    </div>
  );
}

export default SearchBar;