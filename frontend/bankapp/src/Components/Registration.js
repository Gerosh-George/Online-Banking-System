import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Registration = () => {
  const baseURL = "http://localhost:8080/saveCustomer";
  const navigate = useNavigate();
  const [userId, setCustomerId] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [mobile, setmobile] = useState("");
  const [aadhar, setAadhar] = useState("");
  const [dob, setDob] = useState("");

  const customerIdChangeHandler = (event) => {
    //alert(event.target.value);
    //console.log(regno);
    setCustomerId(event.target.value);
  };

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
    setDob(event.target.value);
  };

  const submitActionHandler = (event) => {
    event.preventDefault();
    console.log(event);
    axios
      .post(baseURL, {
        userId,
        password: password,
        name: name,
        mobile,
        aadhar,
        dob,
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

  const cancelHandler = () => {
    //reset the values of input fields
    setCustomerId("");
    setPassword("");
    setAadhar("");
    // navigate("/read");
  };
  return (
    <form onSubmit={submitActionHandler}>
      Customer ID:
      <input
        type="text"
        value={userId}
        onChange={customerIdChangeHandler}
        placeholder="Enter Customer ID number"
        required
      />
      <br></br>
      Password :
      <input
        type="password"
        value={password}
        onChange={passwordHandler}
        placeholder="Enter password"
        required
      />
      <br></br>
      Name:
      <input
        type="text"
        value={name}
        onChange={nameHandler}
        placeholder="Enter name"
        required
      />
      <br></br>
      Mobile :
      <input
        type="number"
        value={mobile}
        onChange={mobileHandler}
        placeholder="Enter Mobile"
        required
      />
      <br></br>
      DoB :
      <input
        type="text"
        value={dob}
        onChange={dobHandler}
        placeholder="Enter DoB"
        required
      />
      <br></br>
      Aadhar :
      <input
        type="text"
        value={aadhar}
        onChange={aadharHandler}
        placeholder="Enter Aadhar"
        required
      />
      <br></br>
      <br></br>
      <button type="submit">Login</button>
      &nbsp;&nbsp;&nbsp;
      <button type="reset" onClick={() => cancelHandler()}>
        Cancel
      </button>
    </form>
  );
};
export default Registration;
