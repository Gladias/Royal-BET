import './Register.css'
import { InputRow, CheckBox } from '../authentication/Authentication'

import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'

function Register() {
    return (
        <div className="main">
            <Form className="authForm registrationForm">
                <h1>Register</h1>

                <InputRow id="login" label="Username" placeholder="Enter username"/>

                <InputRow id="password" label="Password" placeholder="Enter password"/>
                <InputRow id="password" label="Password" placeholder="Repeat password"/>

                <InputRow id="email" label="Email" placeholder="Enter email"/>

                <CheckBox id="termsCheckbox" label="I accept the terms of service" />

                <Button size="lg" variant="warning" type="submit">
                    Register
                </Button>
            </Form>
        </div>
    );
}

export default Register;