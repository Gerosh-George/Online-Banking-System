import React from "react";
import { Modal, Button } from "react-bootstrap";
import styled from "styled-components";

// Define the styled component for the modal content
const StyledModalContent = styled.div`
  padding: 20px;
`;

const AccountDetailsModal = ({ show, onHide, accountDetails }) => {
    
  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>Account Details</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <StyledModalContent>
          {accountDetails ? (
            <div>
              <p>
                <strong>Account Number:</strong> {accountDetails.accountNo}
              </p>
              <p>
                <strong>Account Type:</strong> {accountDetails.accountType}
              </p>
              <p>
                <strong>Balance:</strong> {accountDetails.balance}
              </p>
              <p>
                <strong>Branch:</strong> {accountDetails.branch}
              </p>
            </div>
          ) : (
            <p>Loading account details...</p>
          )}
        </StyledModalContent>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={() => onHide(!show)}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AccountDetailsModal;
