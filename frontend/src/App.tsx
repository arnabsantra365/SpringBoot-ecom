import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AuthPage from './components/AuthPage'
import Navbar from './components/Navbar'
import HomePage from './components/HomePage';
import ProductForm from './components/ProductForm';
import ProductList from './components/ProductList';
import CartPage from './components/CartPage';
import { CartProvider } from './context/CartContext';

function App() {


  return (
    <>
    <CartProvider>
    <Router>
    <Navbar/>
    <Routes>
    <Route path="/" element={<HomePage/>} />
    <Route path="/login" element={<AuthPage />} />
    <Route path="/getproducts" element={<ProductList/>}/>
    <Route path="/product" element={<ProductForm/>}/>
    <Route path="/cart" element={<CartPage/>}/>
    </Routes>
    </Router>
    </CartProvider>
    </>
  )
}

export default App
