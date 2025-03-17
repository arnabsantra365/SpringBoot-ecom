import React from "react";
import { AppBar, Toolbar, IconButton, Typography, Box, Badge, Menu, MenuItem, Button } from "@mui/material";
import { Link } from "react-router-dom";
import { Mail as MailIcon, Notifications as NotificationsIcon, AccountCircle, MoreVert as MoreIcon } from "@mui/icons-material";

export default function Navbar() {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [mobileMenuAnchorEl, setMobileMenuAnchorEl] = React.useState<null | HTMLElement>(null);

  const isMenuOpen = Boolean(anchorEl);
  const isMobileMenuOpen = Boolean(mobileMenuAnchorEl);

  // Profile menu open
  const handleProfileMenuOpen = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  // Mobile menu open
  const handleMobileMenuOpen = (event: React.MouseEvent<HTMLElement>) => {
    setMobileMenuAnchorEl(event.currentTarget);
  };

  // Close menus
  const handleMenuClose = () => {
    setAnchorEl(null);
    setMobileMenuAnchorEl(null);
  };

  return (
    <AppBar position="static">
      <Toolbar>
        {/* Left side - Logo or Branding */}
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
        <Link to="/" style={{ textDecoration: "none", color: "white" }}>My Store</Link>
        </Typography>

        {/* Navigation Links - Shown only on large screens */}
        <Box sx={{ display: { xs: "none", md: "flex" } }}>
          <Button color="inherit">Products</Button>
          <Button color="inherit">Orders</Button>
          <Button color="inherit" component={Link} to="/login">Login</Button>


          {/* Notification and Profile Icons */}
          <IconButton size="large" aria-label="show 4 new mails" color="inherit">
            <Badge badgeContent={4} color="error">
              <MailIcon />
            </Badge>
          </IconButton>
          <IconButton size="large" aria-label="show 17 new notifications" color="inherit">
            <Badge badgeContent={17} color="error">
              <NotificationsIcon />
            </Badge>
          </IconButton>
          <IconButton
            size="large"
            edge="end"
            aria-label="account of current user"
            aria-controls="profile-menu"
            aria-haspopup="true"
            onClick={handleProfileMenuOpen}
            color="inherit"
          >
            <AccountCircle />
          </IconButton>
        </Box>

        {/* Mobile Menu Button - Shown only on small screens */}
        <Box sx={{ display: { xs: "flex", md: "none" } }}>
          <IconButton
            size="large"
            aria-label="show more"
            aria-controls="mobile-menu"
            aria-haspopup="true"
            onClick={handleMobileMenuOpen}
            color="inherit"
          >
            <MoreIcon />
          </IconButton>
        </Box>
      </Toolbar>

      {/* Mobile Menu - Opens on small screens */}
      <Menu
        anchorEl={mobileMenuAnchorEl}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
        keepMounted
        transformOrigin={{ vertical: "top", horizontal: "right" }}
        open={isMobileMenuOpen}
        onClose={handleMenuClose}
      >
        <MenuItem onClick={handleMenuClose}>Products</MenuItem>
        <MenuItem onClick={handleMenuClose}>Orders</MenuItem>
        <MenuItem onClick={handleMenuClose}>Login</MenuItem>
        <MenuItem onClick={handleMenuClose}>
          <IconButton size="large" color="inherit">
            <Badge badgeContent={4} color="error">
              <MailIcon />
            </Badge>
          </IconButton>
          Messages
        </MenuItem>
        <MenuItem onClick={handleMenuClose}>
          <IconButton size="large" color="inherit">
            <Badge badgeContent={17} color="error">
              <NotificationsIcon />
            </Badge>
          </IconButton>
          Notifications
        </MenuItem>
        <MenuItem onClick={handleMenuClose}>
          <IconButton size="large" color="inherit">
            <AccountCircle />
          </IconButton>
          Profile
        </MenuItem>
      </Menu>

      {/* Profile Menu */}
      <Menu
        anchorEl={anchorEl}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
        keepMounted
        transformOrigin={{ vertical: "top", horizontal: "right" }}
        open={isMenuOpen}
        onClose={handleMenuClose}
      >
        <MenuItem onClick={handleMenuClose}>Profile</MenuItem>
        <MenuItem onClick={handleMenuClose}>Logout</MenuItem>
      </Menu>
    </AppBar>
  );
}
