import axios from "axios";
import notifySuccess from "./toastify-services/notifySuccess";
import notifyError from "./toastify-services/notifyError";


export const updateBalance = async (updateObject) => {
  try {
    const response = await axios.post(
      `http://localhost:8080/admin/updateBalance`,
      updateObject
    );
    if (response && response.data) {
      if (response.data === "Balance updated successfully") {
        notifySuccess(response.data);

      } else {
        console.log("Error");
        notifyError(response.data);
      }
    } else {
      notifyError("Server is down");
    }
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};

export const getCustomerDetails = async (customerId, setCustomerData) => {
  try {
    const response = await fetch(
      `http://localhost:8080/customer/${customerId}`
    );
    const data = await response.json();
    if(response.status === 200){
        console.log(data);
        setCustomerData(data);
        notifySuccess("Customer details found ");
    }
    else{
        notifyError("Customers details not found");
    }
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};

export const getAccounts = async(customerId, setAccount) => {
    try{
         if (customerId !== ""){
            axios
                .get(`http://localhost:8080/customer/${customerId}`)
                .then((res) => {
                    setAccount(res.data.account);
                    notifySuccess("Account details fetched successfully");
                })
                .catch((err) => {
                    console.log(err.response.data);
                    notifyError(err.response.data.message);
                    }
                );
         }
         else{
            notifyError("Please enter a valid customer ID");
         }
    } catch(error){
        console.log(error.response.data);
        notifyError(error.response.data.message);
    }
}


export const getTransactions = async(customerId, setTransactions) => {
    try{
         if (customerId !== ""){
            axios
                .get(`http://localhost:8080/allTransactions/${customerId}`)
                .then((res) => {
                    setTransactions(res.data);
                    notifySuccess("Transactions fetched successfully");
                })
                .catch((err) => {
                    console.log(err.response.data);
                    notifyError(err.response.data.message);
                    }
                );

         }

         else{
            notifyError("Please enter a valid customer ID");
         }
    } catch(error){
        console.log(error.response.data);
        notifyError(error.response.data.message);
    }
}


