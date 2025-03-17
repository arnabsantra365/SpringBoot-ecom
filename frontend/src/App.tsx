import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AuthPage from './components/AuthPage'
import Navbar from './components/Navbar'
import HomePage from './components/HomePage';

function App() {


  return (
    <>
    <Router>
    <Navbar/>
    <Routes>
    <Route path="/" element={<HomePage/>} />
    <Route path="/login" element={<AuthPage />} />
    </Routes>
    </Router>
      
    </>
  )
}

export default App
