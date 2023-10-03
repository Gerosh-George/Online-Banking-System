import React, { useState } from "react";
import { Modal, Button, Form, Tab, Tabs } from "react-bootstrap";
import styled from "styled-components";
import { withdrawRequest } from "../../utils/GetRequests";

const StyledModalContent = styled.div`
  padding: 20px;
`;

const centerButtonStyle = {
  display: "flex",
  justifyContent: "center",
  marginTop: "20px",
};

const WithdrawalComponent = ({
  show,
  onHide,
  accounts,
  globalRefresh,
  setGlobalRefresh,
}) => {
  const [withdrawalAmount, setWithdrawalAmount] = useState("");
  const [selfTransferAmount, setSelfTransferAmount] = useState("");
  const [sourceAccount, setSourceAccount] = useState(""); // Manage source account
  const [targetAccount, setTargetAccount] = useState(""); // Manage target account
  const [activeTab, setActiveTab] = useState("withdrawal"); // Manage active tab

  const handleWithdraw = () => {
    withdrawRequest(
      {
        transType: "WITHDRAW",
        amount: withdrawalAmount,
        accFrom: sourceAccount,
        accTo: sourceAccount,
      },
      globalRefresh,
      setGlobalRefresh
    );
    onHide();
  };

  const handleSelfTransfer = () => {
    withdrawRequest(
      {
        transType: "SELF",
        amount: withdrawalAmount,
        accFrom: sourceAccount,
        accTo: targetAccount,
      },
      globalRefresh,
      setGlobalRefresh
    );
    onHide();
  };

  return (
    <Modal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>Transfer Money</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <StyledModalContent>
          <Tabs
            activeKey={activeTab}
            onSelect={(key) => setActiveTab(key)} // Update active tab state
          >
            <Tab eventKey="withdrawal" title="Withdrawal">
              <Form>
                <Form.Group controlId="withdrawalAmount">
                  <Form.Label>Amount:</Form.Label>
                  <Form.Control
                    type="number"
                    placeholder="Enter the amount to withdraw"
                    value={withdrawalAmount}
                    onChange={(e) => setWithdrawalAmount(e.target.value)}
                  />
                </Form.Group>
                <Form.Group controlId="sourceWithdrawalBankAccount">
                  <Form.Label>Bank Account:</Form.Label>
                  <Form.Control
                    as="select"
                    value={sourceAccount}
                    onChange={(e) => setSourceAccount(e.target.value)}
                  >
                    <option value="">Select an account</option>
                    {accounts.map((account, index) => (
                      <option key={index} value={account.accountNo}>
                        {account.accountNo}
                      </option>
                    ))}
                  </Form.Control>
                </Form.Group>
              </Form>
              <div style={centerButtonStyle}>
                <Button variant="primary" onClick={handleWithdraw}>
                  Withdraw
                </Button>
              </div>
            </Tab>
            <Tab eventKey="selfTransfer" title="Self Transfer">
              <Form>
                <Form.Group controlId="selfTransferAmount">
                  <Form.Label>Amount:</Form.Label>
                  <Form.Control
                    type="number"
                    placeholder="Enter the amount to transfer"
                    value={selfTransferAmount}
                    onChange={(e) => setSelfTransferAmount(e.target.value)}
                  />
                </Form.Group>
                <Form.Group controlId="sourceWithdrawalBankAccount">
                  <Form.Label>Bank Account:</Form.Label>
                  <Form.Control
                    as="select"
                    value={sourceAccount}
                    onChange={(e) => setSourceAccount(e.target.value)}
                  >
                    <option value="">Select an account</option>
                    {accounts.map((account, index) => (
                      <option key={index} value={account.accountNo}>
                        {account.accountNo}
                      </option>
                    ))}
                  </Form.Control>
                </Form.Group>
                <Form.Group controlId="targetWithdrawalBankAccount">
                  <Form.Label>Bank Account:</Form.Label>
                  <Form.Control
                    as="select"
                    value={targetAccount}
                    onChange={(e) => setTargetAccount(e.target.value)}
                  >
                    <option value="">Select an account</option>
                    {accounts.map((account, index) => (
                      <option key={index} value={account.accountNo}>
                        {account.accountNo}
                      </option>
                    ))}
                  </Form.Control>
                </Form.Group>
              </Form>
              <div style={centerButtonStyle}>
                <Button variant="primary" onClick={handleSelfTransfer}>
                  Self Transfer
                </Button>
              </div>
            </Tab>
          </Tabs>
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

export default WithdrawalComponent;
