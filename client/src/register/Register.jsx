import './Register.css';

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router';
import InputAlert from '../alert/Alert';
import BASE_API_URL from '../constants/const';
import { InputRow, CheckBox } from '../authentication/Authentication';

function RegistrationForm() {
  const [credentials, setCredentials] = useState({
    login: '',
    password: '',
    passwordConfirm: '',
    email: '',
  });

  const [error, setError] = useState({
    message: '',
  });

  const history = useHistory();

  const handleChange = (e) => {
    const { id, value } = e.target;
    setCredentials((prevState) => ({
      ...prevState,
      [id]: value,
    }));
  };

  const isFormValid = () => {
    let errorMessage;

    if (credentials.password !== credentials.passwordConfirm) {
      errorMessage = 'Provided passwords do not match';
    } else if (!(credentials.login.length >= 2 && credentials.login.length <= 30)) {
      errorMessage = 'Login must contain between 2 to 30 characters';
    }

    if (errorMessage) {
      setError(() => ({ errorMessage }));
      return false;
    }
    return true;
  };

  const sendForm = () => {
    axios.post(`${BASE_API_URL}/auth/register`, credentials)
      .then(() => {
        history.push('/');
      })
      .catch((e) => {
        let errorMessage = '';

        if (e.response.data.errors) {
          const { field, defaultMessage } = e.response.data.errors[0];
          errorMessage = `${field} ${defaultMessage}`;
        } else {
          errorMessage = e.response.data.message;
        }

        setError(() => ({ errorMessage }));
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (isFormValid()) {
      sendForm();
    }
  };

  return (
    <div className="main">
      <Form className="authForm registrationForm" onSubmit={handleSubmit}>
        <h1>Register</h1>
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
        />

        <InputRow
          id="passwordConfirm"
          type="password"
          value={credentials.passwordConfirm}
          onChange={handleChange}
          label="Repeat Password"
          placeholder="Repeat password"
        />

        <InputRow
          id="email"
          value={credentials.email}
          onChange={handleChange}
          label="Email"
          placeholder="Enter email"
        />

        <CheckBox id="termsCheckbox" label="I accept the terms of service" />

        <Button size="lg" variant="warning" type="submit">
          Register
        </Button>
      </Form>
    </div>
  );
}

export default RegistrationForm;
