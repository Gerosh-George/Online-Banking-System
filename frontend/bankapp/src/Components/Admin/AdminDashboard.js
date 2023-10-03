import React, { useState } from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Card,
  CardContent,
  Grid,
  Menu,
  MenuItem,
  Tabs,
  Tab,
  Box,
} from "@mui/material";

import AdminCreateAccount from "./AdminCreateAccount";
import TransactionTable from "./TransactionTable";
import AccountTable from "./AccountTable";
import CustomerDetails from "./CustomerDetails";
import styled, { css } from "styled-components";

import { useNavigate } from "react-router";

import UpdateBalanceModal from "./UpdateBalanceModal";

const StyledAppBar = styled(AppBar)`
  background-color: #0f053d;
  /* Add margin to the bottom */
`;

const StyledButton = styled(Button)`
  && {
    color: white;
  }
`;

const CardContainer = styled.div`
  cursor: pointer;
  bottom-margin: 5px; /* Add margin to the container */
  &:hover {
    background-color: #f0f0f0;
  }
`;

const CardTitle = styled(Typography)`
  && {
    font-size: 20px;
  }
`;

const CardDescription = styled(Typography)`
  && {
    color: #555;
  }
`;

const AdminDashboard = () => {
  const [showUpdateBalanceModal, setUpdateBalanceModal] = useState(false);
  const [showAdminCreateAccountModal, setShowAdminCreateAccountModal] =
    useState(false);
  const [activeTab, setActiveTab] = useState(0); // State to manage active tab

  const [menuAnchorEl, setMenuAnchorEl] = useState(null); // State for the Menu dropdown
  const navigate = useNavigate();

  const handleLogout = () => {
    console.log("Logged out");
    sessionStorage.clear();

    navigate("/");
  };

  const handleCardClick = (cardNumber) => {
    console.log(`Clicked on Card ${cardNumber}`);
  };

  const handleTabChange = (event, newValue) => {
    setActiveTab(newValue);
  };

  const handleMenuOpen = (event) => {
    setMenuAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setMenuAnchorEl(null);
  };

  return (
    <div>
      <AdminCreateAccount
        show={showAdminCreateAccountModal}
        onHide={() => setShowAdminCreateAccountModal(false)}
      />
      <UpdateBalanceModal
        show={showUpdateBalanceModal}
        onHide={() => setUpdateBalanceModal(false)}
      />
      <StyledAppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Admin Dashboard
          </Typography>
          <Button
            color="inherit"
            aria-controls="menu-dropdown"
            aria-haspopup="true"
            onClick={handleMenuOpen}
          >
            Menu
          </Button>
          <Menu
            id="menu-dropdown"
            anchorEl={menuAnchorEl}
            keepMounted
            open={Boolean(menuAnchorEl)}
            onClose={handleMenuClose}
          >
            <MenuItem onClick={() => setShowAdminCreateAccountModal(true)}>
              Create Account
            </MenuItem>
            <MenuItem onClick={() => setUpdateBalanceModal(true)}>
              Update Balance
            </MenuItem>
            <MenuItem onClick={handleLogout}>Logout</MenuItem>
          </Menu>
        </Toolbar>
      </StyledAppBar>

      {/* Tabs Panel */}
      <Tabs value={activeTab} onChange={handleTabChange}>
        <Tab label="Customer" />
        <Tab label="Accounts" />
        <Tab label="Transactions" />
      </Tabs>
      <TabPanel value={activeTab} index={0}>
        <CustomerDetails />
      </TabPanel>
      <TabPanel value={activeTab} index={1}>
        <AccountTable />
      </TabPanel>
      <TabPanel value={activeTab} index={2}>
        <TransactionTable />
      </TabPanel>
    </div>
  );
};

export default AdminDashboard;


function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`tabpanel-${index}`}
      {...other}
    >
      {value === index && <Box p={3}>{children}</Box>}
    </div>
  );
}
