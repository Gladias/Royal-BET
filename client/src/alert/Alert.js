import Alert from 'react-bootstrap/Alert';
import React, {useState, useEffect} from 'react';

function InputAlert(props) {
    const [alertDisplay, toggleDisplay] = useState("none");
    
    const showAlert = () => {
        toggleDisplay("block");
    };

    const hideAlert = () => {
        toggleDisplay("none");
    };

    useEffect(() => {
        if (props.errorMessage) {
            showAlert();
        } else {
            hideAlert();
        }
    });

    return (
        <Alert className="" variant="danger" style={{display: alertDisplay}}>
            <span>{props.errorMessage}</span>
        </Alert> 
    );
};

export default InputAlert;