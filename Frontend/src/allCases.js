import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { styled } from '@mui/system';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
} from '@mui/material';
import { Link } from 'react-router-dom';
import CryptoJS from 'crypto-js';

const StyledTableContainer = styled(TableContainer)({
  marginTop: '50px',
});

const DeleteButton = styled(Button)({
  backgroundColor: '#FF3D00',
  color: '#FFFFFF',
});

const DownloadButton = styled(Button)({
  backgroundColor: '#1976D2',
  color: '#FFFFFF',
});

const EncryptButton = styled(Button)({
  backgroundColor: '#4CAF50',
  color: '#FFFFFF',
});

const ArchivedCasesButton = styled(Button)({
  marginLeft: '1200px',
  marginRight: '50px',
  backgroundColor: '#4CAF50',
  color: '#FFFFFF',
});

const AllCases = () => {
  const [allCases, setAllCases] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAllCases = async () => {
      try {
        const response = await axios.get('http://localhost:8080/v1/legalcases');
        setAllCases(response.data.content || []);
      } catch (error) {
        console.error('Error fetching all cases:', error);
        setError('An error occurred while fetching cases.');
      } finally {
        setLoading(false);
      }
    };

    fetchAllCases();
  }, []);

  const handleDelete = async (caseId) => {
    try {
      await axios.delete(`http://localhost:8080/v1/legalcases/${caseId}`);
      setAllCases((prevCases) =>
        prevCases.filter((caseItem) => caseItem.legalCaseId !== caseId)
      );
    } catch (error) {
      console.error('Error deleting legal case:', error);
    }
  };

  const handleDownload = (caseItem) => {
    try {
      // Convert Base64 to binary
      const binaryData = atob(caseItem.legalCaseDocument);

      // Create a Uint8Array from the binary data
      const arrayBuffer = new Uint8Array(binaryData.length);
      for (let i = 0; i < binaryData.length; i++) {
        arrayBuffer[i] = binaryData.charCodeAt(i);
      }

      // Create a Blob from the Uint8Array
      const blob = new Blob([arrayBuffer], { type: 'application/pdf' });

      // Create a download link and trigger a click event
      const downloadLink = document.createElement('a');
      downloadLink.href = URL.createObjectURL(blob);
      downloadLink.download = `legal_case_${caseItem.legalCaseId}.pdf`;

      // Append the link to the body and trigger a click event
      document.body.appendChild(downloadLink);
      downloadLink.click();

      // Remove the link from the body after the download
      document.body.removeChild(downloadLink);
    } catch (error) {
      console.error('Error handling download:', error);
    }
  };

  const handleEncrypt = (caseItem) => {
    try {
      // Encrypt the document content using CryptoJS
      const encryptedDocument = CryptoJS.AES.encrypt(
        caseItem.legalCaseDocument,
        'yourEncryptionKey'
      ).toString();

      // Convert the encrypted content to a Blob
      const encryptedBlob = new Blob([encryptedDocument], { type: 'application/octet-stream' });

      // Create a download link for the encrypted content
      const encryptedDownloadLink = document.createElement('a');
      encryptedDownloadLink.href = URL.createObjectURL(encryptedBlob);
      encryptedDownloadLink.download = `encrypted_legal_case_${caseItem.legalCaseId}.txt`;

      // Append the link to the body and trigger a click event
      document.body.appendChild(encryptedDownloadLink);
      encryptedDownloadLink.click();

      // Remove the link from the body after the download
      document.body.removeChild(encryptedDownloadLink);
    } catch (error) {
      console.error('Error handling encryption:', error);
    }
  };

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <StyledTableContainer component={Paper}>
      <div style={{ fontFamily: 'Dancing Script', paddingTop: '50px', fontSize: '50px' }}>
        All Cases
      </div>
      <ArchivedCasesButton component={Link} to="/myCases">
        Archived Cases
      </ArchivedCasesButton>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Case ID</TableCell>
            <TableCell>Title</TableCell>
            <TableCell>Description</TableCell>
            <TableCell>Status</TableCell>
            <TableCell>Delete</TableCell>
            <TableCell>Download Document</TableCell>
            <TableCell>Encrypt Document</TableCell> {/* New column */}
          </TableRow>
        </TableHead>
        <TableBody>
          {allCases.map((caseItem) => (
            <TableRow key={caseItem.legalCaseId}>
              <TableCell>{caseItem.legalCaseId}</TableCell>
              <TableCell>{caseItem.legalCaseTitle}</TableCell>
              <TableCell>{caseItem.legalCaseDescription}</TableCell>
              <TableCell>{caseItem.legalCaseStatus}</TableCell>
              <TableCell>
                <DeleteButton variant="contained" onClick={() => handleDelete(caseItem.legalCaseId)}>
                  Delete
                </DeleteButton>
              </TableCell>
              <TableCell>
                <DownloadButton
                  variant="contained"
                  onClick={() => handleDownload(caseItem)}
                >
                  Download
                </DownloadButton>
              </TableCell>
              <TableCell>
                <EncryptButton
                  variant="contained"
                  onClick={() => handleEncrypt(caseItem)}
                >
                  Encrypt
                </EncryptButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </StyledTableContainer>
  );
};

export default AllCases;
