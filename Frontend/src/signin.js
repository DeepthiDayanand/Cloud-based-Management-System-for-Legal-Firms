import React from 'react';
import { Grid, Paper, Typography, TextField, Button, Link } from '@mui/material';
import logo from './logo.png';

export function Signin() {
 

  return (
    <Grid container spacing={4} justifyContent="center" alignItems="center" style={{ height: '100vh' ,paddingTop: '64px'}}>
          {/* Left Side - Logo and Website Name */}
         
      <Grid item xs={12} sm={6} md={4} >
        
        <Typography variant="h2" style={{ marginLeft: '130px' }}>
          Sign In To
        </Typography>
        <Typography variant="h4" style={{ marginLeft: '130px' }}>
          CASEWISE
        </Typography>
        <div style={{ marginLeft: '130px' }}>
          Dont have an Account? <Link href="/register">Register Now</Link>
        </div>
      </Grid>

      {/* Right Side - Sign-in Form */}
      <Grid item xs={12} sm={6} md={4}>
        <Paper elevation={3} style={{ padding: '20px', textAlign: 'center', borderRadius: '0', height: '100%' }}>
          <Typography variant="h5" gutterBottom>
            Sign In
          </Typography>
          {/* <form style={{ height: '100%', display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}> */}
            <TextField
              label="Username"
              fullWidth
              margin="normal"
              variant="outlined"
              required
              InputProps={{
                style: { backgroundColor: '#f0f8ff', border: 'none', marginBottom: '16px' } // Light blue background and no border
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
                style: { backgroundColor: '#f0f8ff', border: 'none', marginBottom: '16px' } // Light blue background and no border
              }}
            />

            <Link href="landing">
            <Button variant="contained" color="primary" fullWidth type="submit" style={{ marginBottom: '16px' }}>
              Sign In
            </Button>
</Link>
            <Link  variant="body2">
              Sign in with Google
            </Link>
          {/* </form> */}
        </Paper>
      </Grid>
    </Grid>
  );
};

export default Signin;
