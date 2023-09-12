import React, { useState } from "react";
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

const FormComponent = () => {
  const [customerId, setCustomerId] = useState("");
  const [password, setPassword] = useState("");

  const paperStyle = {
    padding: 20,
    height: "60vh",
    width: 350,
    margin: "40px auto",
  };
  const avatarStyle = { backgroundColor: "#1bbd7e" };
  const btnstyle = { margin: "8px 0" };

  const customerIdChangeHandler = (event) => {
    //alert(event.target.value);
    //console.log(regno);
    setCustomerId(event.target.value);
  };

  const passwordHandler = (event) => {
    setPassword(event.target.value);
  };

  const submitActionHandler = (event) => {
    event.preventDefault();
    const baseURL = "http://localhost:8080/hello";
    console.log(event);
    axios
      .post(baseURL, {
        customerId: customerId,
        password: password,
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
          <h2>LOG IN</h2>
        </Grid>
        <Grid item xs={12}>
          <InputComponent
            _id={"CustomerID"}
            _value={customerId}
            _placeholder={"Enter CustomerID"}
            _changeHandler={customerIdChangeHandler}
          />
        </Grid>
        <Grid item xs={12}>
          <InputComponent
            _id={"Password"}
            _value={password}
            _placeholder={"Enter Password"}
            _changeHandler={passwordHandler}
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
          Login
        </Button>
        <Typography>
          <Link href="#">Forgot Password</Link>
        </Typography>
        <Typography>
          Become Customer : <Link href="#">Sign Up</Link>
        </Typography>
      </Paper>
    </Grid>
  );
};

export default FormComponent;