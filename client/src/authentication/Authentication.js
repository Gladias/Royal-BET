import './Authentication.css';
import Form from 'react-bootstrap/Form';
import { Link } from 'react-router-dom';

function FormControl(props) {
    if (props.helpText) {
        return (
            <>
            <Form.Control placeholder={props.placeholder} required aria-describedby={`${props.label}HelpBlock`} />
            <Form.Text id={`${props.label}HelpBlock`} muted>{props.helpText.content}</Form.Text>
            </>
        );

    } else {
        return (
            <Form.Control placeholder={props.placeholder} required />
        );
    }
}

export function InputRow(props) {
    const containsHelpText = props.helpText;
    const containsLink = props.link;

    return (
        <Form.Group controlId={props.id}>
            <Form.Label>{props.label}</Form.Label>

            <FormControl {...props} />

            {containsLink &&
                <Link to={props.link.address}>
                    {props.link.message}
                </Link>
            }
        </Form.Group>
    );
}

export function CheckBox(props) {
    return (
        <Form.Group className="form-checkbox" controlId={props.id}>
            <Form.Check custom type="checkbox" label={props.label} />
        </Form.Group>
    );
}