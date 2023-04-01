import React from 'react';
import { Link } from 'react-router-dom';
import './style.css';

function TopBar() {
  return (
    <div>
      <div className="TopBar">
        <Link to="/">
          <img src="https://cdn.discordapp.com/attachments/440326168491720705/1091843197157777429/image.png" alt="Logo da TopBar, está escrito SisEPEI" width="100"/>
        </Link>
      </div>
    </div>
  );
}

export default TopBar;
