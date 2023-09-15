import React from 'react';
import styled from 'styled-components';
import { Nav, NavItem, NavLink } from 'react-bootstrap';

const StyledSidebar = styled(Nav)`
  height: 80vh;
  background: #f7f7f7;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
  flex-direction: column;
  padding: 20px;

  .font-weight-bold {
    font-weight: bold;
  }

  a {
    text-decoration: none;
    color: #333;
    transition: color 0.3s;

    &:hover {
      color: #007bff;
    }
  }
`;

const Sidebar = () => {
  return (
    <StyledSidebar className="flex-column">
      <NavItem>
        <NavLink href="#" className="font-weight-bold">
          Dashboard
        </NavLink>
      </NavItem>
      <NavItem>
        <NavLink href="/withdraw">Withdraw</NavLink>
      </NavItem>
      <NavItem>
        <NavLink href="#">Transfer Money</NavLink>
      </NavItem>
      <NavItem>
        <NavLink href="#">Transaction History</NavLink>
      </NavItem>
      <NavItem>
        <NavLink href="/account">New Account</NavLink>
      </NavItem>
      <NavItem>
        <NavLink href="#">Account Details</NavLink>
      </NavItem>
    </StyledSidebar>
  );
};

export default Sidebar;
