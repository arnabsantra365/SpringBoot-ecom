import React,{useEffect} from "react";
import { useCart } from "../context/CartContext";
import { Button, List, ListItem, Typography } from "@mui/material";
import axios from "axios";
import { useAuth } from "../context/AuthContext";

export default function CartPage() {
  const {userId} = useAuth();
  const { cart, removeFromCart, clearCart } = useCart();
  useEffect(() => {
    console.log("Cart updated:", cart);
  }, [cart]);

  const handleCheckout = () => {
    const orderRequest = {
      customerId: userId, // Replace with logged-in customer ID
      products: cart.map(({ id, quantity }) => ({ productId: id, quantity })),
    };

    axios
      .post("http://localhost:8060/v1/api/orders", orderRequest)
      .then((response) => {
        alert("Order Placed Successfully!");
        clearCart();
      })
      .catch((error) => alert("Failed to place order: " + error.message));
  };
  if (!userId) {
    return <Typography variant="h6">Please sign in to view your orders.</Typography>;
  }
  else{
  return (
    <div>
      <Typography variant="h4">Cart</Typography>
      <List>
        {cart.map((item) => (
          <ListItem key={item.id}>
            {item.name} - {item.quantity} x ${item.price}
            <Button onClick={() => removeFromCart(item.id)}>Remove</Button>
          </ListItem>
        ))}
      </List>
      <Button variant="contained" color="primary" onClick={handleCheckout} disabled={cart.length === 0}>
        Checkout
      </Button>
    </div>
  );
}
}
