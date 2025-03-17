import React, { useState } from "react";
import axios from "axios";
import {
  Container,
  Tabs,
  Tab,
  TextField,
  Button,
  Box,
  Typography,
  Paper,
  Grid,
} from "@mui/material";

export default function AuthPage() {
  const [tab, setTab] = useState(0);
  const [form, setForm] = useState({
    firstname: "",
    lastname: "",
    address: {
      house: "",
      zip: "",
    },
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [loading, setLoading] = useState(false);

  // Handle tab change
  const handleTabChange = (event: React.SyntheticEvent, newValue: number) => {
    setTab(newValue);
    setForm({
      firstname: "",
      lastname: "",
      address: { house: "",zip: "" },
      email: "",
      password: "",
      confirmPassword: "",
    }); // Reset form when switching tabs
  };

  // Handle input change for standard fields
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [event.target.name]: event.target.value });
  };

  // Handle input change for nested address object
  const handleAddressChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setForm({
      ...form,
      address: { ...form.address, [event.target.name]: event.target.value },
    });
  };

  // Handle form submission
  const handleSubmit = async () => {
    if (tab === 1) {
      if (!form.firstname || !form.lastname || !form.address.house) {
        alert("Please fill all required fields.");
        return;
      }
      if (form.password !== form.confirmPassword) {
        alert("Passwords do not match!");
        return;
      }
    }

    setLoading(true);
    const endpoint =
      tab === 0
        ? "http://localhost:8090/v1/api/customer/login"
        : "http://localhost:8090/v1/api/customer/register";

    try {
      const response = await axios.post(endpoint, form, {
        headers: { "Content-Type": "application/json" },
      });
      alert(`Successfully ${tab === 0 ? "Signed In" : "Signed Up"}!`);
      console.log("Response:", response.data);
    } catch (error: any) {
      console.error("Error:", error.response?.data || error.message);
      alert(error.response?.data?.message || "An error occurred!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ padding: 4, marginTop: 8 }}>
        {/* Tabs for Sign In / Sign Up */}
        <Tabs value={tab} onChange={handleTabChange} centered>
          <Tab label="Sign In" />
          <Tab label="Sign Up" />
        </Tabs>

        <Box sx={{ mt: 3 }}>
          {tab === 1 && (
            <Grid container spacing={2}>
              <Grid item xs={6}>
                <TextField
                  fullWidth
                  label="First Name"
                  name="firstname"
                  value={form.firstname}
                  onChange={handleChange}
                  margin="normal"
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  fullWidth
                  label="Last Name"
                  name="lastname"
                  value={form.lastname}
                  onChange={handleChange}
                  margin="normal"
                />
              </Grid>
              <Grid item xs={6}>
                <TextField
                  fullWidth
                  label="House"
                  name="house"
                  value={form.address.house}
                  onChange={handleAddressChange}
                  margin="normal"
                />
              </Grid>
              
              <Grid item xs={3}>
                <TextField
                  fullWidth
                  label="Zip"
                  name="zip"
                  value={form.address.zip}
                  onChange={handleAddressChange}
                  margin="normal"
                />
              </Grid>
            </Grid>
          )}

          <TextField
            fullWidth
            label="Email"
            name="email"
            type="email"
            value={form.email}
            onChange={handleChange}
            margin="normal"
          />
          <TextField
            fullWidth
            label="Password"
            name="password"
            type="password"
            value={form.password}
            onChange={handleChange}
            margin="normal"
          />
          {tab === 1 && (
            <TextField
              fullWidth
              label="Confirm Password"
              name="confirmPassword"
              type="password"
              value={form.confirmPassword}
              onChange={handleChange}
              margin="normal"
            />
          )}

          <Button
            fullWidth
            variant="contained"
            color="primary"
            sx={{ mt: 2 }}
            onClick={handleSubmit}
            disabled={loading}
          >
            {loading ? "Processing..." : tab === 0 ? "Sign In" : "Sign Up"}
          </Button>
        </Box>

        <Typography variant="body2" align="center" sx={{ mt: 2 }}>
          {tab === 0 ? "Don't have an account?" : "Already have an account?"}{" "}
          <Button color="secondary" onClick={() => setTab(tab === 0 ? 1 : 0)}>
            {tab === 0 ? "Sign Up" : "Sign In"}
          </Button>
        </Typography>
      </Paper>
    </Container>
  );
}
