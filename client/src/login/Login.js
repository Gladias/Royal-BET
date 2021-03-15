import './Login.css';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function Login() {
    return (
        <div className="main">
            <Form className="loginForm">
                <h1>LOGIN</h1>
                <Form.Group controlId="login">
                    <Form.Label>Username</Form.Label>
                    <Form.Control placeholder="Enter username" required />
                </Form.Group>

                <Form.Group controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Enter password" required />
                </Form.Group>

                <Form.Group className="remember-row" controlId="rememberMeCheckbox">
                    <Form.Check custom type="checkbox" label="Remember me" />
                </Form.Group>

                <Button size="lg" variant="warning" type="submit">
                    Log In
                </Button>
            </Form>
        </div>
    );
}

export default Login;