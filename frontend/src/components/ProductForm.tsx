import React, { useState } from "react";
import axios from "axios";
import { TextField, Button, Container, Paper, Typography, MenuItem } from "@mui/material";

export default function ProductForm() {
  const [form, setForm] = useState({
    name: "",
    description: "",
    availableQuantity: "",
    price: "",
    categoryName: "",
  });

  const [categories, setCategories] = useState<string[]>(["Electronics", "Fashion", "Home", "Toys"]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      await axios.post("http://localhost:8050/v1/api/products/createProduct", form);
      alert("Product registered successfully!");
      setForm({ name: "", description: "", availableQuantity: "", price: "", categoryName: "" });
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <Container maxWidth="sm">
      <Paper sx={{ padding: 3, marginTop: 4 }}>
        <Typography variant="h5">Register Product</Typography>
        <TextField fullWidth label="Name" name="name" value={form.name} onChange={handleChange} margin="normal" />
        <TextField fullWidth label="Description" name="description" value={form.description} onChange={handleChange} margin="normal" />
        <TextField fullWidth label="Available Quantity" name="availableQuantity" value={form.availableQuantity} onChange={handleChange} type="number" margin="normal" />
        <TextField fullWidth label="Price" name="price" value={form.price} onChange={handleChange} type="number" margin="normal" />
        <TextField fullWidth select label="categoryName" name="categoryName" value={form.categoryName} onChange={handleChange} margin="normal">
          {categories.map((cat) => (
            <MenuItem key={cat} value={cat}>
              {cat}
            </MenuItem>
          ))}
        </TextField>
        <Button fullWidth variant="contained" sx={{ mt: 2 }} onClick={handleSubmit}>
          Submit
        </Button>
      </Paper>
    </Container>
  );
}
