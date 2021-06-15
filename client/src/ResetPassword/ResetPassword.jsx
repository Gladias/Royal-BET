import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import React from 'react';
import { InputRow } from '../authentication/Authentication';

function ResetPassword() {
  return (
    <div className="main">
      <Form className="authForm resetForm">
        <h1>Reset password</h1>
        <div>
          <h5>
            Tell us email address associated with your account, and we will send
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
