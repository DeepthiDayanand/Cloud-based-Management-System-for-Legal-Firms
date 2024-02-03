// kb1.js

import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import kb1 from './kb1.png'

const KB1 = () => {
  return (
    <div style={{marginTop:"70px"}}>
      <h1>How to Add and View Cases</h1>
      <Card>
        <CardContent>
          <Typography variant="h6" gutterBottom>
            Optimize your legal operations with valuable insights
          </Typography>
          <Typography variant="body2" color="textSecondary">
            Access a comprehensive case dashboard, enjoy the convenience of instant backups,
            ensuring your data is always protected and retrievable, and effortlessly manage your documents.
          </Typography>
        </CardContent>
          </Card>
          <img src={kb1} style={{ maxWidth: '100%', height: 'auto', margin:'20px' }} /> 
    </div>
  );
}

export default KB1;
