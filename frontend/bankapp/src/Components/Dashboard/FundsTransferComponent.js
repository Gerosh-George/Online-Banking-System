import React, { useState } from "react";
import { Modal, Button, Form, Tab, Tabs } from "react-bootstrap";
import styled from "styled-components";
import { transferFunds } from "../../utils/GetRequests";

// Define the styled component for the modal content
const StyledModalContent = styled.div`
  padding: 20px;
`;

// Define a custom CSS class for centering the buttons
const centerButtonStyle = {
  display: "flex",
  justifyContent: "center",
  marginTop: "20px", // Add some spacing above the buttons
};

const FundsTransferComponent = ({
  show,
  onHide,
  targetAccounts,
  globalRefresh,
  setGlobalRefresh,
}) => {
  const [transferAmount, setTransferAmount] = useState("");
  const [targetAccount, setTargetAccount] = useState("");
  const [transferType, setTransferType] = useState("rtgs"); // Default to RTGS
  const [sourceAccount, setSourceAccount] = useState("");

  const handleTransfer = () => {
    let transferData = {
      amount: transferAmount,
      accFrom: sourceAccount,
      accTo: targetAccount,
    };
    if (transferType === "rtgs") {
      // Perform RTGS transfer
      transferData = { ...transferData, transType: "RTGS" };
    } else if (transferType === "neft") {
      // Perform NEFT transfer
      transferData = { ...transferData, transType: "NEFT" };
    }
    console.log(transferData);
    transferFunds(transferData, globalRefresh, setGlobalRefresh);

    onHide();
  };

  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>Funds Transfer</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <StyledModalContent>
          <Tabs
            activeKey={transferType}
            onSelect={(key) => setTransferType(key)} // Update transfer type
          >
            <Tab eventKey="rtgs" title="RTGS">
              <Form>
                <Form.Group controlId="rtgsTransferAmount">
                  <Form.Label>Amount:</Form.Label>
                  <Form.Control
                    type="number"
                    placeholder="Enter the RTGS transfer amount"
                    value={transferAmount}
                    onChange={(e) => setTransferAmount(e.target.value)}
                  />
                </Form.Group>

                <Form.Group controlId="rtgsSourceAccount">
                  <Form.Label>Source Account Number:</Form.Label>
                  <Form.Control
                    as="select"
                    value={sourceAccount}
                    onChange={(e) => setSourceAccount(e.target.value)}
                  >
                    <option value="">Select an account</option>
                    {targetAccounts.map((account, index) => (
                      <option key={index} value={account.accountNo}>
                        {account.accountNo}
                      </option>
                    ))}
                  </Form.Control>
                </Form.Group>
                <Form.Group controlId="rtgsTargetAccount">
                  <Form.Label> Target Bank Account:</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter your bank account number"
                    value={targetAccount}
                    onChange={(e) => setTargetAccount(e.target.value)}
                  />
                </Form.Group>
              </Form>
            </Tab>
            <Tab eventKey="neft" title="NEFT">
              <Form>
                <Form.Group controlId="neftTransferAmount">
                  <Form.Label>Amount:</Form.Label>
                  <Form.Control
                    type="number"
                    placeholder="Enter the NEFT transfer amount"
                    value={transferAmount}
                    onChange={(e) => setTransferAmount(e.target.value)}
                  />
                </Form.Group>
                <Form.Group controlId="neftTargetAccount">
                  <Form.Label>Source Account Number:</Form.Label>
                  <Form.Control
                    as="select"
                    value={sourceAccount}
                    onChange={(e) => setSourceAccount(e.target.value)}
                  >
                    <option value="">Select an account</option>
                    {targetAccounts.map((account, index) => (
                      <option key={index} value={account.accountNo}>
                        {account.accountNo}
                      </option>
                    ))}
                  </Form.Control>
                </Form.Group>
                <Form.Group controlId="neftTargetAccount">
                  <Form.Label> Target Bank Account:</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter your bank account number"
                    value={targetAccount}
                    onChange={(e) => setTargetAccount(e.target.value)}
                  />
                </Form.Group>
              </Form>
            </Tab>
          </Tabs>
          <div style={centerButtonStyle}>
            <Button variant="primary" onClick={handleTransfer}>
              Transfer
            </Button>
          </div>
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

export default FundsTransferComponent;
