import React, { useEffect, useState } from "react";
import { Modal, Button } from "react-bootstrap";
import styled from "styled-components";
import { getAccountDetails } from "../../utils/GetRequests";

const StyledModalContent = styled.div`
  padding: 20px;
`;

const AccountDetailsModal = ({ show, onHide, selectedAccount }) => {
  const [accountDetails, setAccountDetails] = useState(null);
  useEffect(() => {
   
    if (selectedAccount !== "") getAccountDetails(selectedAccount, setAccountDetails);
  }, [selectedAccount]);
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
              <p>
                <strong>IFSC:</strong> {accountDetails.ifsc}
              </p>
              <p>
                <strong>Is Disabled:</strong>{" "}
                {accountDetails.disabled ? "Yes" : "No"}
              </p>
              <p>
                <strong>Opening Date:</strong> {accountDetails.openeingDate}
              </p>
              <p>
                <strong>Customer:</strong>{" "}
                {sessionStorage.getItem("customerId")}
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
