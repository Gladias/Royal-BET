import './Header.css';
import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import logo from '../assets/logo.png';

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
