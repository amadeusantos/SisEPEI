import React from 'react';
import './style.css';

function SearchBar() {
  return (
    <div className='search-bar'>
      <input 
        type="text" 
        placeholder='Buscar...' 
      />
    </div>
  );
}

export default SearchBar;
