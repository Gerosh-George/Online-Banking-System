import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import MenuComponent from './MenuComponent';
import styled from "styled-components";

const MenuIcon = styled.div`
  /* Menu icon styles for mobile screens (max-width: 767px) */
  @media (max-width: 767px) {
   
    /* Add your menu icon styling here */
  }
`;

export default function Appbar({customerDetails}) {
  const navigate = useNavigate();
  const customerId = sessionStorage.getItem("customerId");
  const handleLogout = () => {
    console.log("logout");
    sessionStorage.clear();
    navigate("/");
  }
  return (
    <Box sx={{m:2, flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Wells Fargo
          </Typography>
          <Button color="inherit">{customerId}</Button>
          <Button color="inherit" onClick={()=>handleLogout()}>Logout</Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
