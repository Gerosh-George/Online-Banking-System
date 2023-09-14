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

const AccountPage = () => {
  const paperStyle = {
    padding: 20,
    height: "100%",
    width: 350,
    margin: "40px auto",
  };
  const avatarStyle = { backgroundColor: "#1bbd7e" };
  const btnstyle = { margin: "8px 0" };

  const baseURL = "http://localhost:8080/createAccount/2";
  const navigate = useNavigate();

  const [accountType, setAccountType] = useState("");
  const [openingDate, setOpeningDate] = useState("");
  const [ifsc, setIfsc] = useState("");
  const [branch, setBranch] = useState("");

  const accountTypeHandler = (event) => {
    setAccountType(event.target.value);
  };

  const openingDateHandler = (event) => {
    setOpeningDate(event.target.value);
  };

  const ifscHandler = (event) => {
    setIfsc(event.target.value);
  };

  const branchHandler = (event) => {
    setBranch(event.target.value);
  };

  const submitActionHandler = (event) => {
    event.preventDefault();
    axios
      .get("http://localhost:8080/accountHello")
      .then((res) => console.log(res.data));
    console.log(event);
    axios
      .post(baseURL, {
        accountType,
        ifsc,
        openingDate,
        branch,
      })
      .then((response) => {
        console.log(response);
        alert(response.data.message);

        //navigate("/account");
      })
      .catch((error) => {
        alert("error===" + error);
      });
  };

  return (
    <Grid>
      <Paper elevation={10} style={paperStyle}>
        <Grid item align="center">
          <Avatar style={avatarStyle}></Avatar>
          <h2>Create Account</h2>
        </Grid>
        <Grid item xs={12}>
          <InputComponent
            _id={"Account Type"}
            _value={accountType}
            _placeholder={"Account Type"}
            _changeHandler={accountTypeHandler}
          />
        </Grid>
        <Grid item xs={12}>
          <InputComponent
            _id={"IFSC"}
            _value={ifsc}
            _placeholder={"IFSC"}
            _changeHandler={ifscHandler}
          />
        </Grid>
        <Grid item xs={12}>
          <InputComponent
            _id={"Branch"}
            _value={branch}
            _placeholder={"Brancg"}
            _changeHandler={branchHandler}
          />
        </Grid>

        <Grid item xs={12}>
          <InputComponent
            _id={"Account Opening Date"}
            _value={openingDate}
            _placeholder={"Opening Date"}
            _changeHandler={openingDateHandler}
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
