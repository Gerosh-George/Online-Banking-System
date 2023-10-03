import React, { useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const AdminCreateAccount = ({ show, onHide }) => {
  const [customerId, setCustomerId] = useState("");

  const handleInputChange = (e) => {
    setCustomerId(e.target.value);
  };
  const navigate = useNavigate();

  const handleSubmit = () => {
    sessionStorage.setItem("customerId", customerId);

    console.log("Customer ID:", customerId);
    navigate("/account");

    // Close the modal
    onHide();
  };

  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>Create Account</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group controlId="formInput">
            <Form.Label>Customer ID:</Form.Label>
            <Form.Control
              type="text"
              name="CustomerId"
              placeholder="Enter customer ID..."
              value={customerId}
              onChange={handleInputChange}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onHide}>
          Close
        </Button>
        <Button variant="primary" onClick={handleSubmit}>
          Next
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AdminCreateAccount;
