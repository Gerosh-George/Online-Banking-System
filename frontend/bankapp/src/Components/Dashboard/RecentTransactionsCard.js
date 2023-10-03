import React from "react";
import styled from "styled-components";
import { Card, Table } from "react-bootstrap";

const RecentTransactionsCardComp = styled(Card)`
  .bg-primary.text-white {
    border-bottom: 0;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }
`;

const ScrollableTableContainer = styled.div`
  max-height: 62vh; /* Adjust the height as needed */
  overflow-y: auto;
`;

const TransactionTable = styled(Table)`
  td.success {
    color: green; /* Default font color for success */
  }

  /* Add style for fail (red) status cells */
  td.fail {
    color: red;
  }
`;

const RecentTransactionsCard = ({ transactions }) => {
  return (
    <RecentTransactionsCardComp>
      <Card.Header className="bg-primary text-white">
        Recent Transactions
      </Card.Header>
      <Card.Body>
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
                  <td>${transaction.amount}</td>
                </tr>
              ))}
            </tbody>
          </TransactionTable>
        </ScrollableTableContainer>
      </Card.Body>
    </RecentTransactionsCardComp>
  );
};

export default RecentTransactionsCard;
