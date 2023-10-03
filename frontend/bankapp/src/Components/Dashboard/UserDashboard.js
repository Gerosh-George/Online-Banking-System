import React, { useState } from "react";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import RecentTransactionsCard from "./RecentTransactionsCard"; // Assuming you have a TransactionTable component
import AccountDetails from "./AccountDetails";
import TransactionHistoryCard from "./TransactionHistoryCard";
import AccountStatements from "./AccountStatements";

const UserDashboard = ({
  customerDetails,
  setCustomerDetails,
  transactions,
  setTransactions,
}) => {
  const [selectedTab, setSelectedTab] = useState(0);

  const handleTabChange = (event, newValue) => {
    setSelectedTab(newValue);
  };

  return (
    <>
      <Tabs value={selectedTab} onChange={handleTabChange} centered>
        <Tab label="Account Details" />
        <Tab label="Recent Transactions" />
        <Tab label="Transactions History" />
        <Tab label="Account Statements" />
      </Tabs>

      {selectedTab === 0 && (
        <AccountDetails accounts={customerDetails.account} />
      )}
      {selectedTab === 1 && (
        <RecentTransactionsCard transactions={transactions} />
      )}
      {selectedTab === 2 && (
        <TransactionHistoryCard
          transactions={transactions}
          accounts={customerDetails.account}
        />
      )}

      {selectedTab === 3 && (
        <AccountStatements
          transactions={transactions}
          accounts={customerDetails.account}
        />
      )}
    </>
  );
};

export default UserDashboard;
