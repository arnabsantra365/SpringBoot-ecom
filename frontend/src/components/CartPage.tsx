import React from "react";
import { useCart } from "../context/CartContext";
import { Button, List, ListItem, Typography } from "@mui/material";
import axios from "axios";

export default function CartPage() {
  const { cart, removeFromCart, clearCart } = useCart();

  const handleCheckout = () => {
    const orderRequest = {
      customerId: 1, // Replace with logged-in customer ID
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
