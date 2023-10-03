import axios from "axios";
import React, { useState } from "react";
import Table from "react-bootstrap/Table";
import styled from "styled-components";
import notifySuccess from "../../utils/toastify-services/notifySuccess";
import notifyError from "../../utils/toastify-services/notifyError";
import { getTransactions } from "../../utils/adminRequests";


const Container = styled.div`
  background-color: #f5f5f5;
  padding: 20px;
  margin: 20px; /* Add margin to the container */
  max-height: 400px; /* Add max-height for scrolling */
  overflow-y: auto; /* Enable vertical scrolling */
`;

const StyledTransactionTable = styled(Table)`
  background-color: #fff;
  border: 1px solid #ddd;
  border-collapse: collapse;

  th {
    background-color: #007bff;
    color: #fff;
  }

  td,
  th {
    padding: 8px;
    text-align: left;
    border: 1px solid #ddd;
  }
`;

const SearchContainer = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
`;

const SearchInput = styled.input`
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-right: 10px;
  flex: 1;

  &:focus {
    outline: none;
    border-color: #007bff;
  }
`;

const SearchButton = styled.button`
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 8px 16px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;

const TransactionTable = () => {
  const [customerId, setCustomerId] = useState(""); 
  const [transactions, setTransactions] = useState([]);

  const handleSearch = () => {
    if (!customerId.trim()) {
        notifyError("Field cannot be empty.");
        return;
    }
    if (isNaN(customerId)) {
        notifyError("Field value must be a number.");
        return;
    }
    setTransactions([]);
    getTransactions(customerId, setTransactions);
  }

  return (
    <Container>
      <SearchContainer>
        <SearchInput
          type="text"
          placeholder="Customer ID"
          value={customerId}
          onChange={(e) => setCustomerId(e.target.value)}
        />
        <SearchButton onClick={handleSearch}>Search</SearchButton>
      </SearchContainer>
      <StyledTransactionTable striped bordered hover responsive>
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
              <td>{transaction.status}</td>
              <td>${transaction.amount}</td>
            </tr>
          ))}
        </tbody>
      </StyledTransactionTable>
    </Container>
  );
};

export default TransactionTable;
