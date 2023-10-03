import React from "react";
import styled from "styled-components";
import { Card, Nav, NavItem, NavLink } from "react-bootstrap";
import WithdrawalComponent from "./WithdrawalComponent";
import FundsTransferComponent from "./FundsTransferComponent";
import UserProfile from "./UserProfile";
import PasswordChange from "./PasswordChange";
import TransactionHistory from "./TransactionHistory";
import CheckBalanceCard from "./CheckBalanceCard";

const MenuContainer = styled(Card)`
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
    font-weight: bold;

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

const ProfileName = styled.div`
  font-size: 24px;
  color: #007bff;
  margin-bottom: 10px;
`;

const ProfileEmail = styled.div`
  font-size: 16px;
  color: #555;
`;

const MenuNavItem = styled(NavItem)`
  margin-bottom: 10px;

  &:last-child {
    margin-bottom: 0;
  }
`;

const MenuComponent = ({ customerDetails }) => {
  return (
    <MenuContainer>
      <MenuNavItem>
        <Card.Title className="font-weight-bold">Dashboard</Card.Title>
      </MenuNavItem>
      <CheckBalanceCard accounts={customerDetails.account} />
      <WithdrawalComponent
        accounts={customerDetails.account}
      />
      <FundsTransferComponent
        targetAccounts={customerDetails.account}
      />
      <TransactionHistory
        accounts={customerDetails.account}
      />
      <UserProfile
        customerDetails={customerDetails}
      />
      <PasswordChange />
      <ProfileCardContainer>
        <ProfileName>{customerDetails.name}</ProfileName>
        <ProfileEmail>{customerDetails.email}</ProfileEmail>
      </ProfileCardContainer>
      <MenuNavItem>
        <NavLink>Transfer Money (Within Bank)</NavLink>
      </MenuNavItem>
      <MenuNavItem>
        <NavLink>Funds Transfer</NavLink>
      </MenuNavItem>
      <MenuNavItem>
        <NavLink>Customer Details</NavLink>
      </MenuNavItem>
      <MenuNavItem>
        <NavLink href="/account">New Account</NavLink>
      </MenuNavItem>
      <MenuNavItem>
        <NavLink href="#">Account Statement</NavLink>
      </MenuNavItem>
      <MenuNavItem>
        <NavLink>Change Password</NavLink>
      </MenuNavItem>
    </MenuContainer>
  );
};

export default MenuComponent;
