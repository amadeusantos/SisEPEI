import { useLocation } from 'react-router-dom';

import './Header.css';
import { logout } from '../../../services/AuthenticationService';

const Header = () => {
    const location = useLocation();
    const isRegisterOrLoginCurrentRoute = ['/login', '/register'].includes(location.pathname)

    return (
        <div className="header">

            <h2>SisPEI</h2>

            {!isRegisterOrLoginCurrentRoute && (
                <button type='button' onClick={logout}>
                    <span>logout</span>
                </button>
            )}
        </div>
    )
}

export default Header;
