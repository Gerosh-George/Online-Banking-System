import './App.css';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import HomePage from './Components/HomePage';
import AccountPage from './Components/AccountPage';
import LoginPage from './Components/LoginPage';
import Registration from './Components/Registration';

function App() {
  return (

  <div>
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<HomePage/>} />
          <Route exact path="/home" element={<HomePage/>} />
          
          <Route exact path="/account" element={<AccountPage/>} />
          <Route exact path="/login" element={<LoginPage/>} />
          <Route exact path="/registration" element={<Registration/>} />
       
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
