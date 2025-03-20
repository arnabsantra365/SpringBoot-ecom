import axios from "axios";
import { useEffect, useState } from "react"
import {
    Container,
    Grid,
    Card,
    CardContent,
    Typography,
    TextField,
    MenuItem,
    Button
  } from "@mui/material";
  import { useCart } from "../context/CartContext";
const ProductList = () =>{
    const [products,setProducts] = useState([]);
    const [filteredProducts, setFilteredProducts] = useState([]);
    const [categories,setCategories] = useState<string[]>([]);
    const [selectedCategory, setSelectedCategory] = useState("");
    const [priceRange, setPriceRange] = useState<number>(0);
    const { addToCart } = useCart();
    useEffect(()=>{
        axios.get("http://localhost:8050/v1/api/products/all").then((response)=>{
            setProducts(response.data);
            setFilteredProducts(response.data);
            const uniqueCategories: string[] =Array.from(
                new Set(response.data.map((product: any) => product.categoryName)),
            );
              setCategories(uniqueCategories);
        });
    },[]);
    // console.log(products);

    useEffect(() => {
        let filtered = products;
    
        if (selectedCategory) {
          filtered = filtered.filter(
            (product: any) => product.categoryName === selectedCategory
          );
        }
    
        if (priceRange > 0) {
          filtered = filtered.filter((product: any) => product.price <= priceRange);
        }
    
        setFilteredProducts(filtered);
      }, [selectedCategory, priceRange, products]);
      return (
        <Container>
          <Typography variant="h4" sx={{ my: 3 }}>
            Product List
          </Typography>
    
          {/* Filter Controls */}
          <Grid container spacing={2} sx={{ mb: 3 }}>
            <Grid item xs={6}>
              <TextField
                select
                fullWidth
                label="Select Category"
                value={selectedCategory}
                onChange={(e) => setSelectedCategory(e.target.value)}
              >
                <MenuItem value="">All Categories</MenuItem>
                {categories.map((category) => (
                  <MenuItem key={category} value={category}>
                    {category}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item xs={6}>
              <TextField
                type="number"
                fullWidth
                label="Max Price"
                value={priceRange}
                onChange={(e) => setPriceRange(Number(e.target.value))}
              />
            </Grid>
          </Grid>
    
          {/* Product Grid */}
          <Grid container spacing={3}>
            {filteredProducts.map((product: any) => (
              <Grid item xs={12} sm={6} md={4} key={product.id}>
                <Card>
                  <CardContent>
                    <Typography variant="h6">{product.name}</Typography>
                    <Typography variant="body2" color="textSecondary">
                      {product.description}
                    </Typography>
                    <Typography variant="body1">Price: ${product.price}</Typography>
                    <Typography variant="body2" color="primary">
                      Category: {product.categoryName}
                    </Typography>
                    <Button variant="contained" color="primary" onClick={() => addToCart(product)}>
                Add to Cart
              </Button>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Container>
      );
};
export default ProductList;
