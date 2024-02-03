import React, { useState } from 'react';
import axios from 'axios';
import { styled } from '@mui/system';
import { Button, TextField, Container, Paper, Typography } from '@mui/material';

const StyledContainer = styled(Container)({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  height: '100vh',
  minWidth: '100vw',
  marginTop: '50px',
  backgroundColor: '#27445C',
});

const StyledPaper = styled(Paper)({
  padding: '40px',
  width: '60vw',
  backgroundColor: '#ffffff',
});

const StyledTextField = styled(TextField)({
  '& .MuiOutlinedInput-root': {
    '& fieldset': {
      borderColor: '#80D8FF',
    },
    '&:hover fieldset': {
      borderColor: '#80D8FF',
    },
  },
});

const initialState = {
  legalCaseTitle: '',
  legalCaseDescription: '',
  legalCaseStatus: '',
  legalCaseDocument: '', // new field for document
  legalCaseDate: '', // new field for date
};

const LegalCaseForm = () => {
  const [formData, setFormData] = useState(initialState);

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    if (name === 'legalCaseDate') {
      // Ensure the date is in the "yyyy-mm-dd" format
      const formattedDate = new Date(value).toISOString().split('T')[0];
      setFormData({ ...formData, [name]: formattedDate });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
  
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        // Extract the base64 content without the prefix
        const base64Content = reader.result.split(',')[1];
        setFormData({ ...formData, legalCaseDocument: base64Content });
      };
      reader.readAsDataURL(file);
    }
  };

  console.log(formData);
  
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/v1/legalcases', {
        legalCaseTitle: formData.legalCaseTitle,
        legalCaseDescription: formData.legalCaseDescription,
        legalCaseStatus: formData.legalCaseStatus,
        legalCaseDocument: formData.legalCaseDocument,
        // legalCaseDate: formData.legalCaseDate,
        legalCaseOrganizationId: '2e5ececb-ed13-4325-a9e3-5bd426ea449e',
      });

      console.log('Legal case created:', response.data);
      setFormData(initialState); // Reset the form values after successful submission
    } catch (error) {
      console.error('Error creating legal case:', error);
      // Handle errors here
    }
  };

  return (
    <StyledContainer>
      <StyledPaper elevation={3}>
        <Typography variant="h3" gutterBottom style={{ fontFamily: 'Dancing Script' }}>
          Add Case
        </Typography>
        <form onSubmit={handleSubmit}>
          <StyledTextField
            label="Case Title"
            variant="outlined"
            fullWidth
            margin="normal"
            name="legalCaseTitle"
            value={formData.legalCaseTitle}
            onChange={handleInputChange}
            required
          />
          <StyledTextField
            label="Case Status"
            variant="outlined"
            fullWidth
            margin="normal"
            name="legalCaseStatus"
            value={formData.legalCaseStatus}
            onChange={handleInputChange}
            required
          />
          <StyledTextField
            label="Case Description"
            variant="outlined"
            fullWidth
            margin="normal"
            name="legalCaseDescription"
            value={formData.legalCaseDescription}
            onChange={handleInputChange}
            multiline
            rows={4}
            required
          />
          {/* <StyledTextField
            type="date"
           
            variant="outlined"
            fullWidth
            margin="normal"
            name="legalCaseDate"
            value={formData.legalCaseDate}
            onChange={handleInputChange}
            
          /> */}
          <input
            type="file"
            accept=".pdf, .doc, .docx" // Add the file types you want to accept
            onChange={handleFileChange}
            style={{ margin: '10px' }}
          />
          <Button type="submit" variant="contained" color="primary" fullWidth>
            Add Case
          </Button>
        </form>
      </StyledPaper>
    </StyledContainer>
  );
};

export default LegalCaseForm;
