import React from 'react';
import styled from 'styled-components';

// Define the styled component for the Accounts table
const StyledAccountsTable = styled.table`
  width: 100%;
  border-collapse: collapse;

  th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }

  th {
    background-color: #007bff;
    color: #fff;
  }

  tr:nth-child(even) {
    background-color: #f2f2f2;
  }
`;

const AccountDetails = () => {
  // Sample account data
  const accounts = [
    {
      accountNumber: '12345678',
      accountType: 'Savings',
      balance: '$5,000.00',
    },
    {
      accountNumber: '98765432',
      accountType: 'Checking',
      balance: '$2,000.00',
    },
  ];

  return (
    <div>
      
      <StyledAccountsTable>
        <thead>
          <tr>
            <th>Account Number</th>
            <th>Account Type</th>
            <th>Balance</th>
          </tr>
        </thead>
        <tbody>
          {/* Map through the sample account data */}
          {accounts.map((account, index) => (
            <tr key={index}>
              <td>{account.accountNumber}</td>
              <td>{account.accountType}</td>
              <td>{account.balance}</td>
            </tr>
          ))}
        </tbody>
      </StyledAccountsTable>
    </div>
  );
};

export default AccountDetails;
