import React from 'react';
import styled from 'styled-components';

// Define the styled components for the page
const WithdrawalContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #f5f5f5;
`;

const WithdrawalForm = styled.form`
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  width: 100%;
`;

const FormGroup = styled.div`
  margin-bottom: 20px;
`;

const Label = styled.label`
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
`;

const Button = styled.button`
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
`;

const WithdrawalComponent = () => {
  return (
    <WithdrawalContainer>
      <WithdrawalForm>
        <h2>Withdraw Funds</h2>
        <FormGroup>
          <Label>Amount:</Label>
          <Input type="number" placeholder="Enter the amount to withdraw" />
        </FormGroup>
        <FormGroup>
          <Label>Bank Account:</Label>
          <Input type="text" placeholder="Enter your bank account number" />
        </FormGroup>
        <Button type="submit">Withdraw</Button>
      </WithdrawalForm>
    </WithdrawalContainer>
  );
};

export default WithdrawalComponent;
