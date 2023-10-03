import React, { useState } from 'react';
import axios from 'axios';
import NavBar from "./NavBar"
import { useNavigate } from 'react-router-dom';
import { updatePassword } from "../utils/GetRequests.js"
import {
  Grid,
  Paper,
  Avatar,
  TextField,
  Button,
  Typography,
  Link,
} from '@mui/material';

const ForgotPasswordForm = () => {
  const [customerId, setCustomerId] = useState('');
  const [otp, setOtp] = useState('101010');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [message, setMessage] = useState('');
  const [isSubmitted, setIsSubmitted] = useState(false);

  const paperStyle = {
    padding: 20,
    height: '75vh',
    width: 350,
    margin: '40px auto',
  };
  const avatarStyle = { backgroundColor: '#1bbd7e' };
  const btnstyle = { margin: '8px 0' };
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'customerId') {
      setCustomerId(value);
    } else if (name === 'otp') {
      setOtp(value);
    } else if (name === 'newPassword') {
      setNewPassword(value);
    } else if (name === 'confirmPassword') {
      setConfirmPassword(value);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Add validation logic here
    if (!validateForm()) {
      return;
    }

    updatePassword(otp, customerId, newPassword)

    // Simulate sending a password reset request to your backend
    setIsSubmitted(true);
  };

  const validateForm = () => {
    if (!customerId || !otp || !newPassword || !confirmPassword) {
      setMessage('Please fill in all fields.');
      return false;
    }

    if (newPassword !== confirmPassword) {
      setMessage('New password and confirm password do not match.');
      return false;
    }

    return true;
  };

  return (
    <>
    <NavBar />

    <Grid container justifyContent="center">
      <Grid item>
        <Paper elevation={10} style={paperStyle}>
          <Grid container direction="column" alignItems="center">
            <Avatar style={avatarStyle}></Avatar>
            <Typography variant="h5">Forgot Password</Typography>
          </Grid>
          {isSubmitted ? (
            <Typography variant="body1" align="center">
              {message}
            </Typography>
          ) : (
            <form onSubmit={handleSubmit}>
              <TextField
                label="Customer ID"
                name="customerId"
                value={customerId}
                onChange={handleChange}
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="One-Time Password (OTP)"
                name="otp"
                type="text"
                value={otp}
                onChange={handleChange}
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="New Password"
                name="newPassword"
                type="password"
                value={newPassword}
                onChange={handleChange}
                fullWidth
                required
                margin="normal"
              />
              <TextField
                label="Confirm New Password"
                name="confirmPassword"
                type="password"
                value={confirmPassword}
                onChange={handleChange}
                fullWidth
                required
                margin="normal"
              />
              <Button
                type="submit"
                color="primary"
                variant="contained"
                style={btnstyle}
                fullWidth
              >
                Reset Password
              </Button>
            </form>
          )}
          <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>


          </div>
        </Paper>
      </Grid>
    </Grid>
  </>
  );
};

export default ForgotPasswordForm;
