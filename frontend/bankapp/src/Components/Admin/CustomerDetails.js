import axios from "axios";
import React, { useState } from "react";
import styled from "styled-components";
import notifySuccess from "../../utils/toastify-services/notifySuccess";
import notifyError from "../../utils/toastify-services/notifyError";
import { getCustomerDetails } from "../../utils/adminRequests";

// Styled component for the outer container
const Container = styled.div`
  background-color: #f5f5f5;
  padding: 20px;
  margin: 20px; /* Add margin to the container */
  max-height: 400px; /* Add max-height for scrolling */
  overflow-y: auto; /* Enable vertical scrolling */
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

const Header = styled.div`
  background-color: #007bff;
  color: #fff;
  padding: 8px;
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
`;

const CustomerDetailsDisplayContainer = styled.div`
  background-color: #fff; /* White background */
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  display: flex; /* Use flex layout */
  flex-wrap: wrap; /* Allow items to wrap to the next row if necessary */
`;

const FieldColumn = styled.div`
  flex-basis: 20%; /* Decreased width for the first column */
  padding: 5px;
  box-sizing: border-box; /* Include padding in width calculation */
`;

const BoldField = styled.span`
  font-weight: bold;
`;

const CustomerDetailsDisplay = ({ customerData }) => {
  if (!customerData) {
    return null;
  }

  return (
    <CustomerDetailsDisplayContainer>
      <FieldColumn>
        <p>
          <BoldField>Customer ID:</BoldField>
        </p>
        <p>
          <BoldField>Name:</BoldField>
        </p>
        <p>
          <BoldField>Date of Birth:</BoldField>
        </p>
        <p>
          <BoldField>Aadhar Number:</BoldField>
        </p>
        <p>
          <BoldField>Mobile:</BoldField>
        </p>
        <p>
          <BoldField>Email:</BoldField>
        </p>
        <p>
          <BoldField>Address:</BoldField>
        </p>
        <p>
          <BoldField>Father Name:</BoldField>
        </p>
        <p>
          <BoldField>Mother Name:</BoldField>
        </p>
      </FieldColumn>
      <FieldColumn>
        <p>{customerData.customerId}</p>
        <p>{customerData.name}</p>
        <p>{customerData.dob}</p>
        <p>{customerData.aadhar}</p>
        <p>{customerData.mobile}</p>
        <p>{customerData.email}</p>
        <p>{customerData.address}</p>
        <p>{customerData.fathername}</p>
        <p>{customerData.mothername}</p>
      </FieldColumn>
    </CustomerDetailsDisplayContainer>
  );
};

const CustomerDetails = () => {
  const [customerId, setCustomerId] = useState("");
  const [customerData, setCustomerData] = useState(null);

  const handleSearch = () => {
    if (!customerId.trim()) {
      notifyError("CustomerID cannot be empty.");
      return;
    }

    if (isNaN(customerId)) {
      notifyError("CustomerID must be a number.");
      return;
    }

    getCustomerDetails(customerId, setCustomerData);
  };

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
      <Header>Customer Information</Header>
      <CustomerDetailsDisplay customerData={customerData} />{" "}
    </Container>
  );
};

export default CustomerDetails;
