import React from 'react';
import { Modal, Button } from 'react-bootstrap';
import styled from 'styled-components';

const StyledModalContent = styled.div`
  padding: 20px;
`;

const StyledDiv = styled.div`
  margin-bottom: 15px;
`;

const UserProfile = ({ show, onHide, customerDetails }) => {
  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>User Profile</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <StyledModalContent>
          <StyledDiv>
            <strong>Name:</strong> {customerDetails.name}
          </StyledDiv>
          <StyledDiv>
            <strong>Email:</strong> {customerDetails.email}
          </StyledDiv>
          <StyledDiv>
            <strong>Phone:</strong> {customerDetails.mobile}
          </StyledDiv>
          <StyledDiv>
            <strong>Aadhar:</strong> {customerDetails.aadhar}
          </StyledDiv>
          <StyledDiv>
            <strong>Date of Birth:</strong> {customerDetails.dob}
          </StyledDiv>
          <StyledDiv>
            <strong>Address:</strong> {customerDetails.address}
          </StyledDiv>
          <StyledDiv>
            <strong>Fathername:</strong> {customerDetails.fathername}
          </StyledDiv>
          <StyledDiv>
            <strong>Mothername:</strong> {customerDetails.mothername}
          </StyledDiv>
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

export default UserProfile;
