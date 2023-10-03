import axios from "axios";
import React, { useEffect, useState } from "react";
import Table from "react-bootstrap/Table";
import styled from "styled-components";
import { getAccounts } from "../../utils/adminRequests";
import notifyError from "../../utils/toastify-services/notifyError";
import notifySuccess from "../../utils/toastify-services/notifySuccess";

const Container = styled.div`
  background-color: #f5f5f5;
  padding: 20px;
  margin: 20px;
  max-height: 400px;
  overflow-y: auto;
`;

const StyledAccountTable = styled(Table)`
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

  td.fail {
    color: green;
  }
  td.success {
    color: red;
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

const StatusButton = styled.button`
  background-color: ${(props) => (props.status ? "red" : "green")};
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 4px 8px;
  cursor: pointer;
`;

const AccountTable = () => {
  const [customerId, setCustomerId] = useState("");
  const [account, setAccount] = useState([]);
  const [toggleStatus, setToggleStatus] = useState(account.disabled);
  
  useEffect(() => {
    setToggleStatus(account.disabled);
  },[account])
  const handleSearch = () => {
    if (!customerId.trim()) {
        notifyError("Field cannot be empty.");
        return;
    }

    if (isNaN(customerId)) {
        notifyError("Field value must be a number.");
        return;
    }
    setAccount([]);
    getAccounts(customerId, setAccount);
  };
  useEffect(() => {
    
    const timerId = setTimeout(() => {
      if(customerId !== ""){
        getAccounts(customerId, setAccount);
      }
    }, 1000);

    // Return a cleanup function to cancel the timeout
    return () => {
      clearTimeout(timerId);
    };
  },[toggleStatus])
  const handleStatusButtonClick = (accountNo) => {
  setToggleStatus(!toggleStatus);
  const baseURL = `http://localhost:8080/admin/toggleAccountStatus/${accountNo}`;
      axios
        .post(baseURL)
        .then((response) => {
          console.log(response);
          notifySuccess(response.data);
        })
        .catch((error) => {

          console.log(error);
        });

    console.log(`Status button clicked for Account No: ${accountNo}`);
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
      <StyledAccountTable striped bordered hover responsive>
        <thead>
          <tr>
            <th>Account Type</th>
            <th>Account No</th>
            <th>Branch</th>
            <th>Balance</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {account.map((account, index) => (
            <tr key={index}>
              <td>{account.accountType}</td>
              <td>{account.accountNo}</td>
              <td>{account.branch}</td>
              <td>{account.balance}</td>
              <td>
                <StatusButton
                  onClick={() => handleStatusButtonClick(account.accountNo)}
                  status={account.disabled}
                  title="Change Account Status"
                >
                  {account.disabled ? "Disabled" : "Active"}
                </StatusButton>
              </td>
            </tr>
          ))}
        </tbody>
      </StyledAccountTable>
    </Container>
  );
};

export default AccountTable;
