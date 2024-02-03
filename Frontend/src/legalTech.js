import React from 'react';
import legalTechImage from './legalTech.png'; // Import the image
import { Typography } from '@mui/material';

const LegalTech = () => {
  return (
    <div style={{ backgroundImage: `url(${legalTechImage})`, backgroundSize: 'cover', minHeight: '100vh', display: 'flex' }}>
      <div style={{ flex: '1', display: 'flex', maxWidth: '50vw'  }}>
        <div style={{ padding: '200px', textAlign: 'left'}}>
          <Typography variant="h4" style={{ color: 'white' }}>
            Flourishing Era of Legal Tech in India
          </Typography>
          <div style={{ color: 'white' }}>
            Legal technology, commonly known as legal tech, is experiencing a significant boom in India. 
            This transformative era is marked by innovative technologies and solutions that streamline 
            legal processes, enhance access to justice, and revolutionize the legal landscape.
            {/* Add more details as needed */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default LegalTech;
