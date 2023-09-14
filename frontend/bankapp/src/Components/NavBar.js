import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import { Link } from "react-router-dom";

export default function NavBar() {
  return (
    <>
      <Box sx={{ m: 2, flexGrow: 1 }}>
        <AppBar position="static" >
          <Toolbar>
            

            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              Wells Fargo
            </Typography>
            <Button color="inherit"><Link to="/Login">Login</Link></Button>
            <Button color="inherit"><Link to="/Registration">Register</Link></Button>
          </Toolbar>
        </AppBar>
      </Box>
    </>
  );
}
