import './App.css';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import HomePage from './Components/HomePage';
import AccountPage from './Components/AccountPage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<HomePage/>} />
          <Route exact path="/home" element={<HomePage/>} />
          
          <Route exact path="/account" element={<AccountPage/>} />
       
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
