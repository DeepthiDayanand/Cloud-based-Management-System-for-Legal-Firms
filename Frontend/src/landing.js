import React, { useState } from 'react';
import { Grid, Button, Typography, Popover, List, ListItem, ListItemText } from '@mui/material';
import cloud from './cloud.PNG';
import { Link } from 'react-router-dom';

export function LandingPage() {
 

 

  const pageStyle = {
    backgroundColor: '#27445C', // Professional blue background color
    minHeight: '100vh', // Full-page height
  };


  const imageStyle = {
    width: '100%',
    height: 'auto',
    borderRadius: '30%', // Rounded image
    margin: '0 auto', // Center the image horizontally
    display: 'block',
    paddingTop: '64px',
  };

  return (
    <div style={pageStyle}>
      

      {/* Main Content */}
      <Grid
        container
        spacing={4}
        justifyContent="center"
        alignItems="center"
        style={{ minHeight: '100vh', padding: '20px', color: 'white' }} // Padding around the content and full-page height
      >
        {/* Left Grid */}
        <Grid item xs={12} sm={6} md={6}>
          <Typography variant="h4" style={{ fontWeight: 'bold', lineHeight: '1.2',fontFamily:'Dancing Script' }}>
            Secure Storage for Legal Management
          </Typography>
          <div>
            <Typography variant="body1">
Legal data fortress: where secrets speak securely, tech flows seamlessly, and compliance is second nature. It's not just storage; it's a symphony of security in the legal rhythm. Elevate your data game effortlessly.            </Typography>
          </div>
          <Link to="/legalTech">
          <Button variant="contained" color="primary" style={{ borderRadius: '25px', marginTop: '20px' }}>
            GET STARTED
            </Button>
            </Link>
        </Grid>

        {/* Right Grid */}
        <Grid item xs={12} sm={6} md={6}>
          <img src={cloud} alt="Cloud" style={imageStyle} />
        </Grid>
      </Grid>
    </div>
  );
}
