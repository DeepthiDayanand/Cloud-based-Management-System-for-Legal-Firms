import React from 'react';
import { Grid, Typography, TextField, Button, Link } from '@mui/material';
import logo from './logo.png';

export function Register() {
  return (
    <div style={{ paddingTop: '64px' }}>
      <Grid container spacing={4} justifyContent="center" alignItems="center">
        <Grid item xs={12} sm={6} md={4}>
          <Typography variant="h2" style={{ marginLeft: '130px' }}>
            Sign Up To
          </Typography>
          <Typography variant="h4" style={{ marginLeft: '130px' }}>
            CASEWISE
          </Typography>
          <div style={{ marginLeft: '130px' }}>
            Already have an account? <Link href="/">Login Now</Link>
          </div>
        </Grid>

         {/* Right Side - Registration Form */}
      <Grid item xs={12} sm={6} md={4}>
        {/* <Paper elevation={3} styles={{ padding: '20px', textAlign: 'center', borderRadius: '0', height: '100%' }}> */}
          <Typography variant="h5" gutterBottom>
            Sign Up
          </Typography>
          <form styles={{ height: '100%', display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
            <TextField
              label="Email"
              fullWidth
              margin="normal"
              variant="outlined"
              required
              InputProps={{
                style: { backgroundColor: '#f0f8ff', border: 'none', marginBottom: '16px' }
              }}
            />
            <TextField
              label="Create Username"
              fullWidth
              margin="normal"
              variant="outlined"
              required
              InputProps={{
                style: { backgroundColor: '#f0f8ff', border: 'none', marginBottom: '16px' }
              }}
            />
            <TextField
              label="Contact Number"
              fullWidth
              margin="normal"
              variant="outlined"
              required
              InputProps={{
                style: { backgroundColor: '#f0f8ff', border: 'none', marginBottom: '16px' }
              }}
            />
            <TextField
              label="Password"
              fullWidth
              margin="normal"
              variant="outlined"
              type="password"
              required
              InputProps={{
                style: { backgroundColor: '#f0f8ff', border: 'none', marginBottom: '16px' }
              }}
            />
            <TextField
              label="Confirm Password"
              fullWidth
              margin="normal"
              variant="outlined"
              type="password"
              required
              InputProps={{
                style: { backgroundColor: '#f0f8ff', border: 'none', marginBottom: '16px' }
              }}
            />
            <Button variant="contained" color="primary" fullWidth type="submit" styles={{ marginBottom: '16px' }}>
              Register
            </Button>
          </form>
        </Grid>
      </Grid>
    </div>
  );
}
