import './Authentication.css';
import Form from 'react-bootstrap/Form';
import React from 'react';
import { Link } from 'react-router-dom';

function FormControl(props) {
  const { value, type, onChange, placeholder, label, helpText } = props;

  if (helpText) {
    return (
      <>
        <Form.Control
          value={value}
          type={type}
          onChange={onChange}
          placeholder={placeholder}
          required
          aria-describedby={`${label}HelpBlock`}
        />

        <Form.Text id={`${label}HelpBlock`} muted>{props.helpText.content}</Form.Text>
      </>
    );
  }

  return (
    <Form.Control value={value} type={type} onChange={onChange} placeholder={placeholder} required />
  );
}

export function InputRow(props) {
  const { link, id, label } = props;

  return (
    <Form.Group controlId={id}>
      <Form.Label>{label}</Form.Label>

      <FormControl {...props} />

      {link
        && (
        <Link to={link.address}>
          {link.message}
        </Link>
        )}
    </Form.Group>
  );
}

export function CheckBox(props) {
  const { id, label } = props;
  return (
    <Form.Group className="form-checkbox" controlId={id}>
      <Form.Check custom type="checkbox" label={label} required />
    </Form.Group>
  );
}
