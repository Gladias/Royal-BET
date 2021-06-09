/* eslint-disable no-unused-vars */
import './Header.css';
import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Navbar from 'react-bootstrap/Navbar';
import Cookies from 'js-cookie';
import { Link } from 'react-router-dom';
// import { useHistory } from 'react-router';
import logo from '../assets/logo.png';

function Header(props) {
  const { token, setToken } = props;

  const logout = () => {
    Cookies.remove('token');
    setToken(Cookies.get('token'));
  };

  return (
    <Navbar expand="md">
      <Navbar.Brand href="/">
        <img src={logo} className="logo" alt="logo" />
      </Navbar.Brand>
      <div>
        <Button variant="warning" size="lg">
          { token
            ? (
              <Link to="/profile">
                My profile
              </Link>
            )
            : (
              <Link to="/login">
                Log in
              </Link>
            )}
        </Button>
        <Button variant="warning" size="lg">
          { token
            ? (
              <Link to="/login" onClick={logout}>
                Log out
              </Link>
            )
            : (
              <Link to="/register">
                Register
              </Link>
            )}
        </Button>
      </div>
    </Navbar>
  );
}

export default Header;
