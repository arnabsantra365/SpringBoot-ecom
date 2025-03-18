import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AuthPage from './components/AuthPage'
import Navbar from './components/Navbar'
import HomePage from './components/HomePage';
import ProductForm from './components/ProductForm';
import ProductList from './components/ProductList';

function App() {


  return (
    <>
    <Router>
    <Navbar/>
    <Routes>
    <Route path="/" element={<HomePage/>} />
    <Route path="/login" element={<AuthPage />} />
    <Route path="/getproducts" element={<ProductList/>}/>
    <Route path="/product" element={<ProductForm/>}/>
    </Routes>
    </Router>
      
    </>
  )
}

export default App
