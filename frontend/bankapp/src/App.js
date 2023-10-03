import React from "react";
import "./App.css";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import HomePage from "./Components/HomePage";
import AccountPage from "./Components/AccountPage";
import LoginPage from "./Components/LoginPage";
import Registration from "./Components/Registration";

import Layout from "./Components/Dashboard/Layout";

//import UserProfile from "./Components/Dashboard/UserProfile.js"
import ForgotPassword from "./Components/ForgotPassword";

import AdminLogin from "./Components/Admin/AdminLogin";
import AdminDashboard from "./Components/Admin/AdminDashboard";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import ErrorPage from "./Components/ErrorPage";

function App() {
  const [globalRefresh, setGlobalRefresh] = React.useState(false);
  return (
    <div>
      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />

      <BrowserRouter>
        <Routes>
          <Route exact path="*" element={<ErrorPage />} />
          <Route exact path="/" element={<HomePage />} />
          <Route
            exact
            path="/userdashboard"
            element={
              <Layout
                globalRefresh={globalRefresh}
                setGlobalRefresh={setGlobalRefresh}
              />
            }
          />

          <Route exact path="/account" element={<AccountPage />} />
          <Route exact path="/login" element={<LoginPage />} />
          <Route exact path="/admin" element={<AdminLogin />} />
          <Route exact path="/admindashboard" element={<AdminDashboard />} />

          <Route exact path="/registration" element={<Registration />} />
          <Route exact path="/forgotpassword" element={<ForgotPassword />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
