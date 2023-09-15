import React, { useState } from 'react';
import {useNavigate} from "react-router-dom";
import axios from "axios";

const LoginPage = () => {
  const baseURL = "http://localhost:8080/hello";
  const navigate = useNavigate();
  const [customerId, setCustomerId] = useState('');
  const [password, setPassword] = useState('');

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
    console.log(event);
    axios
      .post(baseURL,{
        customerId: customerId,
        password: password,
      })
      .then((response) => {
        console.log(response);
        alert(response.data.message);
       
        //navigate("/account");
      }).catch(error => {
        alert("error==="+error);
      });

  };

  const cancelHandler = () =>{
    //reset the values of input fields
    setCustomerId('');
    setPassword('');
   // navigate("/read");

  }
    return(
      
      
      <form onSubmit={submitActionHandler}>
        
            Customer ID:
            <input type="text" value={customerId} onChange={customerIdChangeHandler} placeholder="Enter Customer ID number" required/><br></br>
        
            Password :
        <input type="password" value={password} onChange={passwordHandler} placeholder="Enter password" required/><br></br>
        <br></br>
        <button type='submit'>Login</button>
        &nbsp;&nbsp;&nbsp;
        <button type='reset' onClick={()=>cancelHandler()}>Cancel</button>
      </form>

    
    
    );
}
export default LoginPage;