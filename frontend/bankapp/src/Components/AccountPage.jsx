import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import {
  Grid,
  Paper,
  Avatar,
  TextField,
  Button,
  Typography,
  Link,
} from "@mui/material";
import InputComponent from "./InputComponent";
import InputComponentAccounts from "./InputComponentAccounts";
import notifySuccess from "../utils/toastify-services/notifySuccess";
import notifyError from "../utils/toastify-services/notifyError";

const AccountPage = () => {
  const paperStyle = {
    padding: 20,
    height: "100%",
    width: 350,
    margin: "40px auto",
  };
  const avatarStyle = { backgroundColor: "#1bbd7e" };
  const btnstyle = { margin: "8px 0" };
  const customerId = sessionStorage.getItem("customerId");

  const baseURL = `http://localhost:8080/account/${customerId}`;
  const navigate = useNavigate();

  const [accountType, setAccountType] = useState("");
  const [branch, setBranch] = useState("");

  const accountTypeHandler = (event) => {
    setAccountType(event.target.value);
  };

  const branchHandler = (event) => {
    setBranch(event.target.value);
  };

  const submitActionHandler = (event) => {
    event.preventDefault();
    axios
      .post(baseURL, {
        accountType,
        branch,
      })
      .then((response) => {
        console.log(response);
        //alert(response.data.message);

        notifySuccess(response.data);

        navigate("/userdashboard");
      })
      .catch((error) => {
        //alert("error===" + error);
        notifyError("Account creation failed");
      });
  };

  return (
    <Grid>
      <Paper elevation={10} style={paperStyle}>
        <Grid item align="center">
          <Avatar style={avatarStyle}></Avatar>
          <h2>Create Account</h2>
        </Grid>
        <Grid item xs={12} mt={2}>
          <InputComponentAccounts
            _id={"Account Type"}
            _value={accountType}
            _changeHandler={accountTypeHandler}
          />
        </Grid>

        <Grid item xs={12}>
          <InputComponent
            _id={"Branch"}
            _value={branch}
            _placeholder={"Branch"}
            _changeHandler={branchHandler}
          />
        </Grid>

        <Button
          type="submit"
          color="primary"
          variant="contained"
          style={btnstyle}
          fullWidth
          onClick={submitActionHandler}
        >
          Open Account
        </Button>
      </Paper>
    </Grid>
  );
};
export default AccountPage;
