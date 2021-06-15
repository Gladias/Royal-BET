/* eslint-disable no-unused-vars */
import './Login.css';
import '../authentication/Authentication.css';

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSignInAlt } from '@fortawesome/free-solid-svg-icons';
import Cookies from 'js-cookie';
import InputAlert from '../alert/Alert';
import BASE_API_URL from '../constants/const';
import { InputRow, CheckBox } from '../authentication/Authentication';

function Login(props) {
  const [credentials, setCredentials] = useState({
    login: '',
    password: '',
  });

  const [error, setError] = useState({
    message: '',
  });

  const { setToken } = props;

  const passwordLink = {
    address: '/reset-password',
    message: 'Forgot your password?',
  };

  const history = useHistory();

  const handleChange = (e) => {
    const { id, value } = e.target;
    setCredentials((prevState) => ({
      ...prevState,
      [id]: value,
    }));
  };

  const sendForm = () => {
    axios.post(`${BASE_API_URL}/auth/login`, credentials, { withCredentials: true })
      .then((response) => {
        setToken(Cookies.get('token'));
        history.push('/');
      })
      .catch(() => {
        setError(() => ({ message: 'No account found with provided credentials' }));
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    sendForm();
  };

  return (
    <div className="main">
      <Form className="authForm" onSubmit={handleSubmit}>
        <h1>Login</h1>
        <InputAlert errorMessage={error.message} />

        <InputRow
          id="login"
          value={credentials.login}
          onChange={handleChange}
          label="Username"
          placeholder="Enter username"
        />

        <InputRow
          id="password"
          type="password"
          value={credentials.password}
          onChange={handleChange}
          label="Password"
          placeholder="Enter password"
          link={passwordLink}
        />

        <CheckBox id="rememberMeCheckbox" label="Remember me" />

        <Button size="lg" variant="warning" type="submit">
          Log in
        </Button>
      </Form>

      <div className="registerLink">
        <h5>First time? </h5>
        <Link to="/register">
          Join us today
          {' '}
          <FontAwesomeIcon icon={faSignInAlt} />
        </Link>
      </div>
    </div>
  );
}

export default Login;
