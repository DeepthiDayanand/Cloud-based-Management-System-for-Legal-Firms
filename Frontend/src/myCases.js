import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';

const MyCases = () => {
  const [closedCases, setClosedCases] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
  const fetchClosedCases = async () => {
    try {
      console.log('Fetching closed cases...');
      const response = await axios.get('https://www.courtlistener.com/api/rest/v3/originating-court-information/');
      console.log('Response:', response.data); // Check the response data
      setClosedCases(response.data.results);
    } catch (error) {
      console.error('Error fetching closed cases:', error);
      setError('An error occurred while fetching closed cases.');
    } finally {
      setLoading(false);
    }
  };

  fetchClosedCases();
}, []);


  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <>
      <TableContainer component={Paper} style={{ marginTop: '100px', color:'black' }}>
        <h2 style={{fontFamily:"Dancing Script", fontSize:"40px"}}>Closed Cases</h2>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Lawyer</TableCell>
              <TableCell>Court Reporter</TableCell>
              <TableCell>Date Filed</TableCell>
              <TableCell>Docket Number</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {closedCases.map((caseItem) => (
              <TableRow key={caseItem.id}>
                <TableCell>{caseItem.assigned_to_str}</TableCell>
                <TableCell>{caseItem.court_reporter}</TableCell>
                <TableCell>{caseItem.date_filed}</TableCell>
                <TableCell>{caseItem.docket_number}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
};

export default MyCases;
