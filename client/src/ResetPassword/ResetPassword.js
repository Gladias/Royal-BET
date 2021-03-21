import { InputRow, CheckBox } from '../authentication/Authentication';

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function ResetPassword() {
    return (
        <div className="main">
            <Form className="authForm resetForm">
                <h1>Reset password</h1>
                <div>
                    <h5>
                        Tell us email address associated with your account, and we'll send
                        you an email with link to reset your password.
                    </h5>
                </div>

                <InputRow id="email" label="Email" placeholder="Enter email" />

                <Button size="lg" variant="warning" type="submit">
                    Reset Password
                </Button>
            </Form>
        </div>
    );
}

export default ResetPassword;