import React from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import logo  from './logo.png';

const contacts = [
  { name: 'Ayushi Soumya', email: 'soumya.ayushi@gmail.com' },
  { name: 'Bhumika Nayak', email: 'bhumikanayak02@gmail.com' },
  { name: 'Deepthi Dayanand', email: 'deepthibhd@gmail.com' },
  { name: 'Vaishnavi VB', email: 'belikerevaishnavi@gmail.com' },
];

const ContactUs = () => {
    return (
      <>
    <Container style={{ backgroundColor: '#27445C', padding: '80px',maxWidth:'100vw',maxHeight:'100vh'}}>
      <Typography variant="h4" align="center" style={{ color: 'white', marginBottom: '2rem' }}>
        Contact Us
      </Typography>
      <Grid container spacing={3} justifyContent="center">
        {contacts.map((contact, index) => (
          <Grid item xs={12} sm={6} md={3} key={index}>
            <div style={{ textAlign: 'center', padding: '1.5rem', border: '1px solid #ccc', borderRadius: '8px', backgroundColor: '#ADD8E6' }}>
              <Typography variant="h6" gutterBottom style={{ fontWeight: 'bold', color: 'black' }}>
                {contact.name}
              </Typography>
              <Typography color="textSecondary" style={{ color: 'black' }}>
                {contact.email}
              </Typography>
            </div>
          </Grid>
        ))}
          </Grid>
          
      </Container>
     
    <Grid
      container
      justifyContent="center"
      alignItems="center"
      style={{ height: '50vh', position: 'relative' }}
    >
      <img
        src={logo}
        alt="watermark"
        style={{
          position: 'absolute',
          width: '50%',
          height: '100%',
          opacity: 0.2, // Adjust the opacity as needed
        }}
      />
      <Typography variant="h1" style={{ fontFamily: 'Dancing Script', color: '#27445C', zIndex: 1 }}>
        CaseWise
      </Typography>
    </Grid>
      </>
  );
};

export default ContactUs;
