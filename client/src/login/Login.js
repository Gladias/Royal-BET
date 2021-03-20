import './Login.css'
import '../authentication/Authentication.css'
import { InputRow, CheckBox } from '../authentication/Authentication'

import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSignInAlt } from '@fortawesome/free-solid-svg-icons'

function Login() {

    const passwordLink = {
        address: "#",
        message: "Forgot your password?"
    };

    return (
        <div className="main">
            <Form className="authForm">
                <h1>Login</h1>

                <InputRow id="login" label="Username" placeholder="Enter username"/>

                <InputRow id="password" label="Password" placeholder="Enter password" link={passwordLink}/>

                <CheckBox id="rememberMeCheckbox" label="Remember me" />

                <Button size="lg" variant="warning" type="submit">
                    Log in
                </Button>
            </Form>

            <div className="register">
                <h5>First time? </h5>
                <a href="#"> Join us today <FontAwesomeIcon icon={faSignInAlt} /> </a>
            </div> 
        </div>
    );
}

export default Login;