import React from "react";
import styled from "styled-components";
import { Button, Card, Nav, NavItem, NavLink } from "react-bootstrap";
import WithdrawalComponent from "./WithdrawalComponent";
import FundsTransferComponent from "./FundsTransferComponent";
import UserProfile from "./UserProfile";
import PasswordChange from "./PasswordChange";
import TransactionHistory from "./TransactionHistory";
import CheckBalanceCard from "./CheckBalanceCard";
import CustomerDetailModal from "./CustomerDetailModal";


const StyledSidebar = styled(Card)`
  height: 80vh;
  background: #f7f7f7;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
  flex-direction: column;
  padding: 20px;
  display: flex;
  flex-grow: 1;
  overflow-y: auto;

  .font-weight-bold {
    font-weight: bold;
  }

  a {
    text-decoration: none;
    color: #333;
    transition: color 0.3s;
    font-weight: bold; /* Added this line to make links bold */

    &:hover {
      color: #007bff;
    }
  }
`;

const ProfileCardContainer = styled(Card)`
  background-color: #fff;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  padding: 20px;
`;

const ProfileCardTitle = styled.h4`
  font-size: 24px;
  margin-bottom: 20px;
  color: #333;
`;

const ProfileCardLink = styled.a`
  text-decoration: none;
  cursor: pointer;
`;

const ProfileName = styled.div`
  font-size: 24px; /* Bigger font size for name */
  color: #007bff; /* Same color as the balance */
  margin-bottom: 10px;
`;

const ProfileEmail = styled.div`
  font-size: 16px; /* Smaller font size for email */
  color: #555;
`;

const SidebarNavItem = styled(NavItem)`
  margin-bottom: 10px;

  &:last-child {
    margin-bottom: 0;
  }
`;

const Sidebar = ({ customerDetails, globalRefresh, setGlobalRefresh }) => {
  const [showWithdrawalModal, setShowWithdrawalModal] = React.useState(false);
  const [showFundsTransferComponent, setShowFundsTransferComponent] =
    React.useState(false);
  const [showUserProfileModal, setShowUserProfileModal] = React.useState(false);
  const [showPasswordChangeModal, setShowPasswordChangeModal] =
    React.useState(false);
  const [showTransactionHistoryModal, setShowTransactionHistoryModal] =
    React.useState(false);

  const [showCustomerDetailModal, setShowCustomerDetailModal] =
    React.useState(false);

  return (
    <StyledSidebar>
      <SidebarNavItem>
        <Card.Title className="font-weight-bold">Dashboard</Card.Title>
      </SidebarNavItem>

      <CheckBalanceCard
        accounts={customerDetails.account}
        globalRefresh={globalRefresh}
      />
      <WithdrawalComponent
        show={showWithdrawalModal}
        onHide={() => setShowWithdrawalModal(!showWithdrawalModal)}
        accounts={customerDetails.account}
        globalRefresh={globalRefresh}
        setGlobalRefresh={setGlobalRefresh}
      />
      <FundsTransferComponent
        show={showFundsTransferComponent}
        onHide={setShowFundsTransferComponent}
        targetAccounts={customerDetails.account}
        globalRefresh={globalRefresh}
        setGlobalRefresh={setGlobalRefresh}
      />
      <TransactionHistory
        show={showTransactionHistoryModal}
        onHide={() =>
          setShowTransactionHistoryModal(!showTransactionHistoryModal)
        }
        accounts={customerDetails.account}
      />
      <UserProfile
        show={showUserProfileModal}
        onHide={() => setShowUserProfileModal(!showUserProfileModal)}
        customerDetails={customerDetails}
      />
      <PasswordChange
        show={showPasswordChangeModal}
        onHide={() => setShowPasswordChangeModal(!showPasswordChangeModal)}
      />
      <CustomerDetailModal
        show={showCustomerDetailModal}
        onHide={() => setShowCustomerDetailModal(!showCustomerDetailModal)}
        customerDetails={customerDetails}
        globalRefresh={globalRefresh}
        setGlobalRefresh={setGlobalRefresh}
      />
      <ProfileCardLink onClick={() => setShowUserProfileModal(true)}>
        <ProfileCardContainer>
          <ProfileName>{customerDetails.name}</ProfileName>
          <ProfileEmail>{customerDetails.email}</ProfileEmail>
        </ProfileCardContainer>
      </ProfileCardLink>
      <SidebarNavItem>
        <NavLink onClick={() => setShowWithdrawalModal(true)}>
          Transfer Money (Within Bank)
        </NavLink>
      </SidebarNavItem>
      <SidebarNavItem>
        <NavLink onClick={() => setShowFundsTransferComponent(true)}>
          Funds Transfer
        </NavLink>
      </SidebarNavItem>
      <SidebarNavItem>
        <NavLink onClick={() => setShowCustomerDetailModal(true)}>
          Customer Details
        </NavLink>
      </SidebarNavItem>
      <SidebarNavItem>
        <NavLink href="/account">New Account</NavLink>
      </SidebarNavItem>
      <SidebarNavItem>
        <NavLink onClick={() => setShowPasswordChangeModal(true)}>
          Change Password
        </NavLink>
      </SidebarNavItem>
    </StyledSidebar>
  );
};

export default Sidebar;
