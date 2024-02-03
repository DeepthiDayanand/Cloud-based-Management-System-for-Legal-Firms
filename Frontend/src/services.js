import React from 'react';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';
import cases from './cases.png';

function ServicesPage() {
  const cardStyle = {
    backgroundColor: 'linear-gradient(to bottom, #27445C, #ffffff)',
    padding: '1.5rem',
    borderRadius: '10px',
    textAlign: 'center',
    position: 'relative',
    height: '100%',
  };

  const imageStyle = {
    maxWidth: '100%', // Ensure the image doesn't exceed the container width
    maxHeight: '100%', // Ensure the image doesn't exceed the container height
    borderRadius: '30%', // Rounded image
    margin: '0 auto', // Center the image horizontally
    display: 'block',
  };

  const circleStyle = {
    width: '40px',
    height: '40px',
    borderRadius: '50%',
    backgroundColor: 'white',
    border: '2px solid #27445C',
    position: 'absolute',
    top: '-20px',
    left: '50%',
    transform: 'translateX(-50%)',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    fontSize: '20px',
    fontWeight: 'bold',
  };

  const rootStyle = {
    backgroundColor: '#27445C',
    padding: '64px',
    minHeight: '100vh',
    display: 'flex',
    flexDirection: 'column', // Display children components in a column
    alignItems: 'center', // Center items horizontally
    justifyContent: 'center', // Center items vertically
  };

  const linkStyle = {
    textDecoration: 'none', // Remove underlines from the Link components
  };

  return (
    <div style={rootStyle}>
    <img src={cases} alt="Cases" style={{ position: 'absolute', width: '100%', height: '100%', opacity: 0.6 }} />
      <Typography variant="h4" gutterBottom style={{ color: 'white', padding: '50px',fontFamily:"Dancing Script", fontSize: '80px' }}>
        Manage Cases
      </Typography>
      <Grid container spacing={3} alignItems="stretch" justify="center">
        
       
        <Grid item xs={12} sm={4}>
          <Link to='/caseForm' style={linkStyle}>
            <Paper elevation={3} style={cardStyle}>
              <div style={circleStyle}>1</div>
              <Typography variant="h6" gutterBottom style={{ padding: '10px' ,fontFamily:'cursive'}}>
                Submit  Case Details
              </Typography>
              <Typography style={{ padding: '20px'}}>
                Case-wise document details submission and uploading.
              </Typography>
            </Paper>
          </Link>
        </Grid>

         <Grid item xs={12} sm={4}>
          <Link to='/allCases' style={linkStyle}>
            <Paper elevation={3} style={cardStyle}>
              <div style={circleStyle}>2</div>
              <Typography variant="h6" gutterBottom style={{ padding: '10px' ,fontFamily:'cursive'}}>
                View Cases
              </Typography>
              <Typography style={{ padding: '20px'}}>
                View the case details for your organization using Casewise.
              </Typography>
            </Paper>
          </Link>
        </Grid>
         <Grid item xs={12} sm={4}>
          <Link to='/calendar' style={linkStyle}>
            <Paper elevation={3} style={cardStyle}>
              <div style={circleStyle}>3</div>
              <Typography variant="h6" gutterBottom style={{ padding: '10px' ,fontFamily:'cursive'}}>
                Schedule Organizer
              </Typography>
              <Typography style={{ padding: '20px'}}>
                Mark the calender for the important dates of onGoing cases.
              </Typography>
            </Paper>
          </Link>
        </Grid>
       
      </Grid>
    </div>
  );
}

export default ServicesPage;
