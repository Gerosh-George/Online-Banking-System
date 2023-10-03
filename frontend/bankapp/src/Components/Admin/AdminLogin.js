import React, { useState } from "react";
import axios from "axios";
import {useNavigate} from 'react-router-dom';
import {
  Grid,
  Paper,
  Avatar,
  TextField,
  Button,
  Typography,
  Link,
} from "@mui/material";
import InputComponent from "../InputComponent";
import notifySuccess from "../../utils/toastify-services/notifySuccess";
import notifyError from "../../utils/toastify-services/notifyError";
import NavBar from "../NavBar";


const AdminLogin = () => {
  const [userId, setUserId] = useState("");
  const [password, setPassword] = useState("");

  const paperStyle = {
    padding: 20,
    height: "60vh",
    width: 350,
    margin: "40px auto",
  };
  const avatarStyle = { backgroundColor: "#1bbd7e" };
  const btnstyle = { margin: "8px 0" };
  const navigate = useNavigate();

  const userIdChangeHandler = (event) => {
    setUserId(event.target.value);
  };

  const passwordHandler = (event) => {
    setPassword(event.target.value);
  };

  const submitActionHandler = (event) => {
    event.preventDefault();
    const baseURL = "http://localhost:8080/admin/login";
    console.log(event);

    axios
      .post(baseURL, {
        userid: userId,
        password: password,
      })
      .then((response) => {
        if(response.data === "Login success"){
          notifySuccess(response.data);
          sessionStorage.setItem("adminId", userId);
          navigate("/admindashboard");
        }else{
          notifyError(response.data)
        }
        
      })
      .catch((error) => {
        notifyError(error.response.data.message)
      });
  };

  return (
    <>
      <NavBar />
      <Grid>
        <Paper elevation={10} style={paperStyle}>
          <Grid item align="center">
            <Avatar style={avatarStyle}></Avatar>
            <h2>ADMIN LOG IN</h2>
          </Grid>
          <Grid item xs={12}>
            <InputComponent
              _id={"UserId"}
              _value={userId}
              _placeholder={"Enter UserID"}
              _changeHandler={userIdChangeHandler}
            />
          </Grid>
          <Grid item xs={12}>
            <InputComponent
              _id={"Password"}
              _value={password}
              _placeholder={"Enter Password"}
              _changeHandler={passwordHandler}
              _type={"password"}
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
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
          </div>
        </Paper>
      </Grid>
    </>
  );
};

export default AdminLogin;
