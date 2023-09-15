import "./App.css";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import HomePage from "./Components/HomePage";
import AccountPage from "./Components/AccountPage";
import LoginPage from "./Components/LoginPage";
import Registration from "./Components/Registration";
import FormComponent from "./Components/FormComponent";
import UserDashboard from "./Components/Dashboard/UserDashboard";
import NavBar from "./Components/NavBar";
import Layout from "./Components/Dashboard/Layout";
import WithdrawalComponent from "./Components/Dashboard/WithdrawalComponent";

function App() {
  return (
    <div>
      
        <BrowserRouter>
          <Routes>
            <Route exact path="/" element={<HomePage />} />
            <Route exact path="/userdashboard" element={<Layout />} />

            <Route exact path="/account" element={<AccountPage />} />
            <Route exact path="/login" element={<FormComponent />} />
            <Route exact path="/registration" element={<Registration />} />
            <Route exact path="/withdrawal" element={<WithdrawalComponent />} />
          </Routes>
        </BrowserRouter>
      
    </div>
  );
}

export default App;
