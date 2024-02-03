// Organizations.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import organizationsImage from './organizations.png';

function Organizations() {
  const [organizations, setOrganizations] = useState([]);

  const rootStyle = {
    backgroundColor: '#27445C',
    padding: '64px',
    minHeight: '100vh',
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
  };

  const cardStyle = {
    backgroundColor: '#ADD8E6',
    borderRadius: '10px',
    padding: '20px',
    // margin: '20px',
    width: '40vw',
  };

  const imageStyle = {
    width: '100%',
    height: '100vh',
    borderRadius: '30%',
    margin: '0 auto',
    display: 'block',
  };

  useEffect(() => {
    // Fetch organization data when the component mounts
    axios.get('http://localhost:8080/v1/organizations') // Replace 'YOUR_API_ENDPOINT' with the actual API endpoint
      .then(response => {
        setOrganizations(response.data.content);
      })
      .catch(error => {
        console.error('Error fetching organization data:', error);
      });
  }, []);

  return (
    <div style={rootStyle}>
      <div style={{ width: '50%', paddingRight: '0px' }}>
        <Typography variant="h4" gutterBottom style={{ color: '#ADD8E6', padding: '30px', fontFamily:'Dancing Script' }}>
          My Legal Firm
        </Typography>

        {/* Display all organizations */}
        {organizations.map((org) => (
          <Card key={org.organizationId} style={cardStyle}>
            <CardContent>
              <Typography variant="h6">{org.organizationName}</Typography>
              <p>{org.organizationDescription}</p>
              <p>Email: {org.organizationEmail}</p>
              <p>Phone: {org.organizationPhone}</p>
              <p>Address: {org.organizationStreet1}, {org.organizationStreet2}, {org.organizationStreetZipcode}</p>
              <p>City: {org.organizationCityName}</p>
              <p>District: {org.organizationDistrictName}</p>
              <p>State: {org.organizationStateName}</p>
              <p>Country: {org.organizationCountryName}</p>
              {/* Display other organization details as needed */}
            </CardContent>
          </Card>
        ))}
      </div>
      <div style={{ width: '50%' }}>
        <img src={organizationsImage} alt="Organizations" style={imageStyle} />
      </div>
    </div>
  );
}

export default Organizations;
