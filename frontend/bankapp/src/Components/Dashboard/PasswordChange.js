import React, { useState } from "react";
import { Modal, Button, Form, Tab, Tabs } from "react-bootstrap";
import styled from "styled-components";
import { updatePassword } from "../../utils/GetRequests.js";
import notifySuccess from "../../utils/toastify-services/notifySuccess";
import notifyError from "../../utils/toastify-services/notifyError";

// Define the styled component for the modal content
const StyledModalContent = styled.div`
  padding: 20px;
`;

const PasswordChange = ({ show, onHide }) => {
  const [formData, setFormData] = useState({
    otp: "101010",
    newPassword: "",
    confirmPassword: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if new password and confirm password match
    const customerId = sessionStorage.getItem("customerId");
    if (formData.newPassword !== formData.confirmPassword) {
      notifyError("New password and confirm password do not match");
      return;
    }

    updatePassword(formData.otp, customerId, formData.newPassword);

    setFormData({
      otp: "",
      newPassword: "",
      confirmPassword: "",
    });

    onHide();
  };

  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>Password Change</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <StyledModalContent>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="otp">
              <Form.Label>OTP</Form.Label>
              <Form.Control
                type="password"
                name="OTP"
                value={formData.currentPassword}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group controlId="newPassword">
              <Form.Label>New Password</Form.Label>
              <Form.Control
                type="password"
                name="newPassword"
                value={formData.newPassword}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group controlId="confirmPassword">
              <Form.Label>Confirm New Password</Form.Label>
              <Form.Control
                type="password"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <div style={{ display: "flex", justifyContent: "center" }}>
              <Button variant="primary" type="submit">
                Change Password
              </Button>
            </div>
          </Form>
        </StyledModalContent>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onHide}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default PasswordChange;
