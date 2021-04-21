import './Register.css';
import { InputRow, CheckBox } from '../authentication/Authentication';
import { BASE_API_URL } from '../constants/const'

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import React, {useState} from 'react';
import axios from 'axios';
import InputAlert from '../alert/Alert';
import { useHistory } from 'react-router';

function RegistrationForm(props) {
    const [state, setState] = useState({
        login : "",
        password : "",
        passwordConfirm : "",
        email : "",
        errorMessage : ""
    });

    let history = useHistory();

    const handleChange = (e) => {
        const {id, value} = e.target;
        setState(prevState => ({
            ...prevState,
            [id] : value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (isFormValid()) {
            sendForm();
        }
    };

    const isFormValid = () => {
        let message;

        if (state.password !== state.passwordConfirm) {
            message = "Provided passwords do not match";
        } else if (!(2 <= state.login.length && state.login.length <= 30)) {
            message = "Login must contain between 2 to 30 characters";
        }
        
        if (message) {
            setState(prevState => ({
                ...prevState,
                errorMessage : message
            }));
            return false;
        }
        return true;
    };

    const sendForm = () => {
        const payload = {
            "login": state.login,
            "password": state.password,
            "passwordConfirm": state.passwordConfirm,
            "email": state.email
        };

        axios.post(BASE_API_URL + "/auth/register", payload)
            .then(function () {
                history.push("/");
            })
            .catch(function (error) {
                let message = "";
                console.log(error.response.data);

                if (error.response.data.errors) {
                    let { field, defaultMessage }  = error.response.data.errors[0];
                    message = field + " " + defaultMessage;
                } else {
                    message = error.response.data.message;
                }
                
                setState(prevState => ({
                    ...prevState,
                    errorMessage : message
                }));
            });
    };

    return (
        <div className="main">
            <Form className="authForm registrationForm" onSubmit={handleSubmit}>
                <h1>Register</h1>
                <InputAlert errorMessage={state.errorMessage}/>
                <InputRow id="login" value={state.login} onChange={handleChange} label="Username" placeholder="Enter username"/>

                <InputRow id="password" type="password" value={state.password} onChange={handleChange} label="Password" placeholder="Enter password"/>
                <InputRow id="passwordConfirm" type="password" value={state.passwordConfirm} onChange={handleChange} label="Repeat Password" placeholder="Repeat password"/>

                <InputRow id="email" value={state.email} onChange={handleChange} label="Email" placeholder="Enter email"/>

                <CheckBox id="termsCheckbox" label="I accept the terms of service" />

                <Button size="lg" variant="warning" type="submit">
                    Register
                </Button>
            </Form>
        </div>
    );
}

export default RegistrationForm;