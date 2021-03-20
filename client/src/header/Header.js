import './Header.css';
import logo from '../assets/logo.png';
import Navbar from 'react-bootstrap/Navbar';

function Header() {
    return (
        <Navbar expand="md">
            <Navbar.Brand href="#">
                <img src={logo} className="logo" alt="logo" />
            </Navbar.Brand> 
        </Navbar>
    );
}

export default Header;