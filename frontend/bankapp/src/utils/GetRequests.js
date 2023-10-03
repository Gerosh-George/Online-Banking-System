import axios from "axios";
import notifySuccess from "./toastify-services/notifySuccess";
import notifyError from "./toastify-services/notifyError";

export const getAccountDetails = async (accountNumber, setAccountDetails) => {
  try {
    const response = await fetch(
      `http://localhost:8080/getAccountDetails/${accountNumber}`
    );
    const data = await response.json();
    console.log(data);
    setAccountDetails(data);
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};

export const getCustomerDetails = async (customerId, setCustomerDetails) => {
  try {
    const response = await fetch(
      `http://localhost:8080/customer/${customerId}`
    );
    const data = await response.json();
    console.log(data);
    setCustomerDetails(data);
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};

export const updatePassword = (otp, customerId, password) => {
  const baseURL = `http://localhost:8080/changePassword/${otp}`;
  axios
    .put(baseURL, {
      customerId,
      password,
    })
    .then((response) => {
        console.log(response)
        notifySuccess(response.data)
        }
    )
    .catch((error) => {
      console.log(error);
      if (error && error.response && error.response.data) {
        notifyError(error.response.data.message);
      } else notifyError("Server is down");
    });
};
export const getAllUserTransactions = async (customerId, setTransactions) => {
  try {
    const response = await fetch(
      `http://localhost:8080/allTransactions/${customerId}`
    );
    const data = await response.json();
    console.log(data);
    setTransactions(data);
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};

export const getTransactions = async (accountNumber, setTransactions) => {
  axios
    .get(`http://localhost:8080/transactions/${accountNumber}`)
    .then((response) => {
      console.log(response.data);
      setTransactions(response.data);
    })
    .catch((error) => {
      console.log(error);
      if (error && error.response && error.response.data) {
        notifyError(error.response.data.message);
      } else notifyError("Server is down");
    });
};

export const getAllAccountsBalance = async (customerId, setTotalBalance) => {
  try {
    const response = await fetch(
      `http://localhost:8080/allAccountBalance/${customerId}`
    );

    const data = await response.json();
    console.log(data);
    setTotalBalance(data);
    notifySuccess("Balance refreshed successfully");
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};

export const transferFunds = async (
  transferFundsObject,
  globalRefresh,
  setGlobalRefresh
) => {
  axios
    .post(`http://localhost:8080/transact`, transferFundsObject)
    .then((response) => {
      console.log(response);
      //alert(response.data, response.status);
      notifySuccess(response.data);
      setGlobalRefresh(!globalRefresh);
    })
    .catch((error) => {
      console.log(error);
      //alert(error.response.data.message);
      if (error && error.response && error.response.data) {
        notifyError(error.response.data.message);
      } else notifyError("Server is down");
    });
};

export const loginCustomer = async (loginObject, navigate) => {
  try {
    const response = await axios.post(
      `http://localhost:8080/login`,
      loginObject
    );
    if (response && response.data) {
      if (response.data === "Login success") {
        notifySuccess(response.data);
        sessionStorage.setItem("customerId", loginObject.customerId);
        navigate("/userdashboard");
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

export const withdrawRequest = async (
  withdrawObject,
  globalRefresh,
  setGlobalRefresh
) => {
  try {
    const response = await axios.post(
      `http://localhost:8080/transact`,
      withdrawObject
    );
    console.log(response);
    console.log(setGlobalRefresh)
    notifySuccess(response.data);
    setGlobalRefresh(!globalRefresh);
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};
const formatDate = (dateString) => {
  const [year, month, day] = dateString.split("-");
  return `${year}-${month}-${day}`;
};

export const postAccountStatements = async (
  selectedAccount,
  startDate,
  endDate,
  setTransactions
) => {
  try {
    const formatDateStart = formatDate(startDate);
    const formatDateEnd = formatDate(endDate);
    console.log(`http://localhost:8080/accountStatement/${selectedAccount}`, {
      startDate,
      endDate,
    });

    const response = await axios.post(
      `http://localhost:8080/accountStatement/${selectedAccount}`,
      { startDate: formatDateStart, endDate: formatDateEnd }
    );
    console.log(response);
    setTransactions(response.data);
  } catch (error) {
    console.log(error);
    if (error && error.response && error.response.data) {
      notifyError(error.response.data.message);
    } else notifyError("Server is down");
  }
};

export const updateCustomerDetails = async (customerObject) => {
  try {
    const response = await axios.put(
      `http://localhost:8080/changeDetails`,
      customerObject
    );
    console.log(response);
    notifySuccess(response.data);
  } catch (error) {
    console.log(error);
    notifyError(error.response.data.message);
  }
}