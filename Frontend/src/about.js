import React from 'react';
import logo from './logo.png';
import { Grid, Typography, Paper, Box } from '@mui/material';

function AboutUs() {
  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="center"
      minHeight="100vh"
      bgcolor="#27445C"
      color="white"
      style={{ paddingTop: '64px' }}
    >
      <Grid container spacing={4} item xs={12} sm={10}>
        {/* Left Grid */}
        <Grid item xs={12} sm={6}>
          <Paper elevation={3}>
            <Box p={3} display="flex" justifyContent="center" alignItems="center">
              <img src={logo} alt="CASEWISE Logo" style={{ maxWidth: '100%', height: 'auto' }} />
            </Box>
          </Paper>
        </Grid>

        {/* Right Grid */}
        <Grid item xs={12} sm={6}>
          <Paper elevation={3} style={{ height: '100%' }}>
            <Box p={3}>
              <Typography variant="h4" gutterBottom>
                About Us
              </Typography>
             
              <Typography variant="body1" paragraph>
                At CASEWISE, we are dedicated to revolutionizing the legal industry through digitization. Our mission is to provide a cutting-edge cloud-based management system for legal firms, ushering in a new era of efficiency and innovation.
              </Typography>
              <Typography variant="body1" paragraph>
                Our logo, featuring Justicia confidently holding a laptop in one hand and scales in the other, encapsulates our commitment to this groundbreaking concept. We firmly believe that by leveraging technology, we can enhance transparency, accessibility, and effectiveness within the legal sphere.
              </Typography>
              <Typography variant="body1" paragraph>
                Recognizing the unique challenges faced by legal professionals today, our passionate team of experts is tirelessly working to develop groundbreaking solutions that empower legal firms to work smarter and provide exceptional service to their clients.
              </Typography>
              <Typography variant="body1" paragraph>
                Join us on this exciting journey as we reshape the legal industry for the digital age. Together, we can make a significant impact and shape the future of law with CASEWISE.
              </Typography>
            </Box>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
}

export default AboutUs;
