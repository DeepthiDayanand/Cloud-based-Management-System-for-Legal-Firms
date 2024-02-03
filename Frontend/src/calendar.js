import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import {
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
  Typography,
  styled,
} from '@mui/material';

const StyledCalendarContainer = styled(Paper)({
  padding: '20px',
  marginRight: '20px',
  minWidth: '300px',
});

const StyledTableContainer = styled(Paper)({
  padding: '20px',
  width: '100%',
});

const StyledButton = styled(Button)({
  marginTop: '10px',
});

const CalendarComponent = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [note, setNote] = useState('');
  const [data, setData] = useState({ content: [] });

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/v1/legalcases');
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const handleDateClick = (date) => {
    setSelectedDate(date);
    console.log("selected date",date)
  };

  const handleNoteChange = (event) => {
    setNote(event.target.value);
  };

  const handleSave = async () => {
    const legalCaseId = note;
    console.log("id", legalCaseId);
    console.log("note", note);

    try {
      const response = await axios.get(`http://localhost:8080/v1/legalcases/${legalCaseId}`);
      const matchingCase = response.data;

      console.log("matching", matchingCase);

      if (matchingCase) {
        const updatedLegalCase = {
          ...matchingCase,
          legalCaseDate: selectedDate
          ? new Date(selectedDate.getTime() + 24 * 60 * 60 * 1000).toISOString().split('T')[0]
          : null,

          
        };

        await axios.put(`http://localhost:8080/v1/legalcases/${legalCaseId}`, updatedLegalCase);
        console.log('Data updated successfully',updatedLegalCase);
        fetchData(); // Fetch updated data after save
      } else {
        console.error('No matching legal case found for the entered note.');
      }
    } catch (error) {
      console.error('Error updating data:', error);
    }
  };

  return (
    <>
      <div style={{ fontFamily: 'Cursive', paddingTop: '70px', fontSize: '50px' }}>
        Set Court Dates For Different Cases
      </div>

      <div style={{ display: 'flex', paddingTop: '70px', minWidth: '100%' }}>
        <StyledCalendarContainer>
          <Calendar onClickDay={handleDateClick} value={selectedDate} />
          <TextField
            label="Enter Case ID"
            multiline
            rows={4}
            value={note}
            onChange={handleNoteChange}
            style={{ marginTop: "20px" }}
          />
          <StyledButton variant="contained" onClick={handleSave}>
            Update Court Date
          </StyledButton>
        </StyledCalendarContainer>
        <StyledTableContainer>
          <Typography variant="h5" gutterBottom style={{fontFamily:"cursive"}}>
            Court Schedule
          </Typography>
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Case Id</TableCell>
                  <TableCell>Title</TableCell>
                  <TableCell>Court Date</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {data.content.map((legalCase) => (
                  <TableRow key={legalCase.legalCaseId}>
                    <TableCell>{legalCase.legalCaseId}</TableCell>
                    <TableCell>{legalCase.legalCaseTitle}</TableCell>
                    <TableCell>
                    {legalCase.legalCaseDate ? new Date(legalCase.legalCaseDate).toLocaleDateString() : 'Not set'}
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </StyledTableContainer>
      </div>
    </>
  );
};

export default CalendarComponent;
