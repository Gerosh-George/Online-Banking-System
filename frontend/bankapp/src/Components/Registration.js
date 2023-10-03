import React, { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
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
import NavBar from "./NavBar";
import notifyError from "../utils/toastify-services/notifyError";
import notifySuccess from "../utils/toastify-services/notifySuccess";

function reverseString(inputDate) {
  const dateParts = inputDate.split('-');
  
  if (dateParts.length !== 3) {
    return "Invalid date format. Please use 'dd-mm-yyyy'.";
  }

  // Rearrange the date parts to the 'yyyy-mm-dd' format
  const year = dateParts[2];
  const month = dateParts[1];
  const day = dateParts[0];
  console.log(year, month, day);
  // Create the formatted date string
  const formattedDate = `${year}-${month}-${day}`;
  console.log(formattedDate);

  return formattedDate;
}







const Registration = () => {
  const paperStyle = {
    padding: 20,
    height: "80%",
    width: 350,
    margin: "40px auto",
  };
  const avatarStyle = { backgroundColor: "#1bbd7e" };
  const btnstyle = { margin: "8px 0" };

  const baseURL = "http://localhost:8080/register";
  const navigate = useNavigate();

  const [toggleButton, setToggleButton] = useState(false);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [mobile, setmobile] = useState("");
  const [aadhar, setAadhar] = useState("");
  const [dob, setDob] = useState("");

  const passwordHandler = (event) => {
    setPassword(event.target.value);
  };

  const nameHandler = (event) => {
    setName(event.target.value);
  };

  const mobileHandler = (event) => {
    setmobile(event.target.value);
  };

  const aadharHandler = (event) => {
    setAadhar(event.target.value);
  };

  const dobHandler = (event) => {
    setDob((event.target.value));
  };

  const emailHandler = (event) => {
    setEmail(event.target.value);
  };

  const handleToggleButton = () =>{
    if (!name || !email || !password ) {
      notifyError('Please fill in all fields.');
      return;
    }

    // Check if the password length is between 8 and 15 characters
    if (password.length < 8 || password.length > 15) {
      notifyError("Password must be between 8 and 15 characters.");
      return;
    }

    // Validate email format
    const isValidEmail = (email) => {
      const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      return emailRegex.test(email);
    };
  
    if (!isValidEmail(email)) {
      notifyError('Please enter a valid email address.');
      return;
    }

    setToggleButton(!toggleButton)

  }

  const submitActionHandler = (event) => {
    event.preventDefault();
  
    // Check for empty fields
    if (!name || !email || !password || !mobile || !aadhar || !dob) {
      notifyError('Please fill in all fields.');
      return;
    }   
  
    // Validate Aadhar number format
    const isValidAadhar = (aadhar) => {
      const aadharRegex = /^\d{12}$/;
      return aadharRegex.test(aadhar);
    };
  
    if (!isValidAadhar(aadhar)) {
      notifyError('Please enter a valid Aadhar number.');
      return;
    }
  
    // Validate mobile number format
    const isValidMobileNumber = (mobileNumber) => {
      // Define a regular expression for a 10-digit mobile number
      const mobileNumberRegex = /^\d{10}$/;
      return mobileNumberRegex.test(mobileNumber);
    };
  
    if (!isValidMobileNumber(mobile)) {
      notifyError('Please enter a valid mobile number.');
      return;
    }
  
    // Additional validation for date of birth (DOB)
  
    if (dob === "Invalid date format. Please use 'dd-mm-yyyy'.") {
      notifyError('Invalid date format for DOB. Please use "dd-mm-yyyy".');
      return;
    }

    const isAgeGreaterThan18 = (dateOfBirth) => {
      const today = new Date();
      const birthDate = new Date(dateOfBirth);
      const age = today.getFullYear() - birthDate.getFullYear();
      const monthDifference = today.getMonth() - birthDate.getMonth();
      const dayDifference = today.getDate() - birthDate.getDate();
    
      if (monthDifference < 0 || (monthDifference === 0 && dayDifference < 0)) {
        return age - 1 >= 18;
      }
    
      return age >= 18;
    }

    if(!isAgeGreaterThan18(dob)){
        notifyError("Customer's age should be greater than 18")
        return
    }
  
    // Continue with the axios POST request
    axios
      .post(baseURL, {
        password: password,
        name: name,
        mobile,
        aadhar,
        email,
        dob: dob // Use the formatted DOB
      })
      .then((response) => {

        if(response.data.message === 'Customer created successfully!'){
          notifySuccess(response.data.message + "\n Customer Id: " + response.data.id)
          navigate("/login");
        }else{    
          notifyError(response.data.message)
          setToggleButton(!toggleButton)
          setAadhar("")
          setName("")
          setPassword("")
          setmobile("")
          setEmail("")
          setDob("")
          
        }
        
      })
      .catch((error) => {
        console.log(error);
        //alert(error.response.data.message);
        //alert("error===" + error);
      });
  };

  return (
    <>
      <NavBar />
      <Grid xs={12}>
        <Paper elevation={10} style={paperStyle}>
          <Grid item align="center">
            <Avatar style={avatarStyle}></Avatar>
            <h2>Customer Sign Up</h2>
          </Grid>
          {!toggleButton ? (
            <>
              <Grid item xs={12}>
                <InputComponent
                  _id={"Customer Name"}
                  _value={name}
                  _placeholder={"Enter Customer Name"}
                  _changeHandler={nameHandler}
                  requi
                />
                <InputComponent
                  _id={"Email"}
                  _value={email}
                  _placeholder={"Enter Email"}
                  _changeHandler={emailHandler}
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
            </>
          ) : (
            <>
              <Grid item xs={12}>
                <InputComponent
                  _id={"Aadhar Number"}
                  _value={aadhar}
                  _placeholder={"Enter Aadhar Number"}
                  _changeHandler={aadharHandler}
                />
              </Grid>
              <Grid item xs={12}>
                <InputComponent
                  _id={"Mobile"}
                  _value={mobile}
                  _placeholder={"Enter Moblie"}
                  _changeHandler={mobileHandler}
                />
              </Grid>
              <Grid item xs={12}>
                <InputComponent
                  _id={""}
                  _value={dob}
                  _placeholder={"Enter DOB"}
                  _changeHandler={dobHandler}
                  _type={"date"}
                />
              </Grid>
            </>
          )}

          <Grid item xs={12} style={{display: "flex",justifyContent: "center", alignItems:"center" }}>
            {toggleButton ? (
              <Button color="warning" onClick={handleToggleButton}>
                Back
              </Button>
            ) : (
              <Button color="success"  onClick={handleToggleButton}>
                Next
              </Button>
            )}
          </Grid>

          <Button
            type="submit"
            color="primary"
            variant="contained"
            style={btnstyle}
            fullWidth
            onClick={submitActionHandler}
            
          >
            Sign Up
          </Button>

          <Typography>
            Already Customer : <Link href="login">Sign In</Link>
          </Typography>
        </Paper>
      </Grid>
    </>
  );
};
export default Registration;
