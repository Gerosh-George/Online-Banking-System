import React, { useState, useEffect } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import styled from "styled-components";
import mockAccounts, {
  mockAccounts2,
  mockTransactionData,
} from "../../utils/data";
import { getTransactions } from "../../utils/GetRequests";

const CustomModal = styled(Modal)`
  .custom-modal-content {
    width: 1000px; /* Set a fixed width for the modal */
  }
`;

// Define styled components for the elements
const TransactionHistoryContainer = styled.div`
  max-height: 400px; /* Set the maximum height to enable scrolling */
  overflow-y: auto; /* Enable vertical scrolling */
  margin-top: 20px;
`;

const TransactionHistoryHeader = styled.h2`
  color: #333;
  font-size: 24px;
  margin-bottom: 20px;
`;

const TransactionTable = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

const TableHead = styled.thead`
  background-color: #007bff;
  color: #fff;
`;

const TableHeader = styled.th`
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
`;

const TableBody = styled.tbody`
  tr:nth-child(even) {
    background-color: #f2f2f2;
  }
`;

const TableRow = styled.tr`
  &:hover {
    background-color: #ddd;
  }
`;

const TableData = styled.td`
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
`;

function TransactionHistory({ show, onHide, accounts }) {
  const [selectedAccount, setSelectedAccount] = useState("");
  const [transactions, setTransactions] = useState([]);
  const handleChange = (event) => {
    setSelectedAccount(event.target.value);
    getTransactions(event.target.value, setTransactions);
  };
  return (
    <CustomModal show={show} onHide={onHide} centered>
      <Modal.Header closeButton>
        <Modal.Title>Transaction History</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form.Group controlId="accountSelect">
          <Form.Label>Select Account:</Form.Label>
          <Form.Control
            as="select"
            value={selectedAccount}
            onChange={handleChange}
          >
            <option value="">Select an account</option>
            {accounts.map((account, index) => (
              <option key={index} value={account.accountNo}>
                {account.accountNo}
              </option>
            ))}
          </Form.Control>
        </Form.Group>
        <TransactionHistoryContainer>
          <TransactionHistoryHeader>
            Transaction History
          </TransactionHistoryHeader>
          <TransactionTable>
            <TableHead>
              <tr>
                <TableHeader>Transaction ID</TableHeader>
                <TableHeader>Status</TableHeader>
                <TableHeader>Account To</TableHeader>
                <TableHeader>Account From</TableHeader>
                <TableHeader>Type</TableHeader>
                <TableHeader>Amount</TableHeader>
                
              </tr>
            </TableHead>
            <TableBody>
              {transactions.map((transaction) => (
                <TableRow key={transaction.transactionId}>
                  <TableData>{transaction.transactionId}</TableData>
                  <TableData>{transaction.status}</TableData>
                  <TableData>{transaction.accTo}</TableData>
                  <TableData>{transaction.accFrom}</TableData>
                  <TableData>{transaction.transType}</TableData>
                  <TableData>${transaction.amount.toFixed(2)}</TableData>

                </TableRow>
              ))}
            </TableBody>
          </TransactionTable>
        </TransactionHistoryContainer>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onHide}>
          Close
        </Button>
      </Modal.Footer>
    </CustomModal>
  );
}

export default TransactionHistory;
