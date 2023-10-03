import React, { useState } from "react";
import { Card, Form, Table, Button, Row, Col } from "react-bootstrap";
import styled from "styled-components";
import {
  getTransactions,
  postAccountStatements,
} from "../../utils/GetRequests";
import notifyError from "../../utils/toastify-services/notifyError";

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
const FormRow = styled(Row)`
  display: flex;
  align-items: flex-end;
`;

const SubmitButton = styled(Button)`
  background-color: #007bff;
  color: #fff;

  @media (max-width: 576px) {
    margin-top: 20px;
  }
  
`;

const AccountStatements = ({ accounts }) => {
  const [selectedAccount, setSelectedAccount] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  const handleChange = (event) => {
    setSelectedAccount(event.target.value);
  };

  const handleStartDateChange = (event) => {
    setStartDate(event.target.value);
  };

  const handleEndDateChange = (event) => {
    setEndDate(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (selectedAccount !== "" && startDate !== "" && endDate !== "") {
      postAccountStatements(
        selectedAccount,
        startDate,
        endDate,
        setTransactions
      );
    } else {
      notifyError("Please fill in all the fields");
    }
  };

  return (
    <TransactionHistory>
      <Card.Header className="bg-primary text-white">
        Transaction History
      </Card.Header>
      <Card.Body>
        <Form onSubmit={handleSubmit}>
          <FormRow>
            <Col md={3}>
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
            </Col>
            <Col md={3}>
              <Form.Group controlId="startDate">
                <Form.Label>Start Date:</Form.Label>
                <Form.Control
                  type="date"
                  value={startDate}
                  onChange={handleStartDateChange}
                />
              </Form.Group>
            </Col>
            <Col md={3}>
              <Form.Group controlId="endDate">
                <Form.Label>End Date:</Form.Label>
                <Form.Control
                  type="date"
                  value={endDate}
                  onChange={handleEndDateChange}
                />
              </Form.Group>
            </Col>
            <Col md={3}>
              <SubmitButton variant="primary" type="submit">
                Submit
              </SubmitButton>
            </Col>
          </FormRow>
        </Form>

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

export default AccountStatements;
