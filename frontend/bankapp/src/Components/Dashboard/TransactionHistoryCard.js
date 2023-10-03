import React, { useState } from "react";
import { Card, Form, Table } from "react-bootstrap";
import styled from "styled-components";
import { getTransactions } from "../../utils/GetRequests";

const TransactionHistory = styled(Card)`
  .bg-primary.text-white {
    border-bottom: 0;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
    
  }
`;



const TransactionTable = styled(Table)`
  td.success {
    color: green;
  }

  td.fail {
    color: red;
  }
`;

const ScrollableTableContainer = styled.div`
  max-height: 48.5vh;
  overflow-y: auto;
  margin-top: 20px;
`;

const TransactionHistoryCard = ({ accounts }) => {
  const [selectedAccount, setSelectedAccount] = useState("");
  const [transactions, setTransactions] = useState([]);

  const handleChange = (event) => {
    setSelectedAccount(event.target.value);
    getTransactions(event.target.value, setTransactions);
  };

  return (
    <TransactionHistory>
      
        <Card.Header className="bg-primary text-white">
          Transaction History
        </Card.Header>
        <Card.Body>
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
          <ScrollableTableContainer>
            <TransactionTable striped bordered hover responsive>
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Transaction Type</th>
                  <th>Transaction ID</th>
                  <th>Account To</th>
                  <th>Status</th>
                  <th>Amount</th>
                </tr>
              </thead>
              <tbody>
                {transactions.map((transaction, index) => (
                  <tr key={index}>
                    <td>{transaction.timestamp}</td>
                    <td>{transaction.transType}</td>
                    <td>{transaction.transactionId}</td>
                    <td>{transaction.accTo}</td>
                    <td
                      className={
                        transaction.status === "SUCCESS" ? "success" : "fail"
                      }
                    >
                      {transaction.status}
                    </td>
                    <td>${transaction.amount.toFixed(2)}</td>
                  </tr>
                ))}
              </tbody>
            </TransactionTable>
          </ScrollableTableContainer>
        </Card.Body>
      
    </TransactionHistory>
  );
};

export default TransactionHistoryCard;
