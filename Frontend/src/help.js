import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, Typography, Card, CardContent, Snackbar, styled } from '@mui/material';
import ChatIcon from '@mui/icons-material/Chat';

const HelpContainer = styled('div')({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  height: '100vh',
  minWidth: '100vw',
});

const HelpCard = styled(Card)({
  
  textAlign: 'left',
  backgroundColor: '#f5f5f5',
  margin: 'auto', // Center the card horizontally
  alignItems: 'center',
  justifyContent: 'center',
});

const HelpCardContent = styled(CardContent)({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent: 'center',
});

const HelpList = styled('ul')({
  listStyle: 'none',
  padding: 0,
});

const HelpListItem = styled('li')({
  marginBottom: '10px',
});

const HelpButton = styled(Button)({
  backgroundColor: '#007bff',
  color: '#fff',
  marginTop: '10px',
  '&:hover': {
    backgroundColor: '#0056b3',
  },
});

const ChatbotIcon = styled('div')({
  position: 'fixed',
  bottom: '20px',
  right: '20px',
  fontSize: '2rem',
  color: '#007bff',
  cursor: 'pointer',
});

const Help = () => {
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [openChatbot, setOpenChatbot] = useState(false);

  const handleCustomerCareNotification = () => {
    setOpenSnackbar(true);
  };

  const handleSnackbarClose = () => {
    setOpenSnackbar(false);
  };

  const handleChatIconClick = () => {
    setOpenChatbot(true);
  };

  const handleCloseChatbot = () => {
    setOpenChatbot(false);
  };

  // Chatbot-related logic
  const handleChatbotSubmit = (message) => {
    // Implement your chatbot logic here
    console.log(`User message: ${message}`);
  };

  return (
    <HelpContainer>
      <HelpCard>
        <HelpCardContent>
          <Typography variant="h5" gutterBottom>
            Help Center
          </Typography>
          <HelpList>
            <HelpListItem>
              <Link to="/courts">Know about the types of courts</Link>
            </HelpListItem>
            <HelpListItem>
              <Link to="/about">Information regarding case-wise details</Link>
            </HelpListItem>
            <HelpListItem>
              <Link to="/contact">Need support? Mail us</Link>
            </HelpListItem>
            <HelpListItem>
              <HelpButton onClick={handleCustomerCareNotification}>
                Customer care help (Notify on 8102986784)
              </HelpButton>
            </HelpListItem>
          </HelpList>
        </HelpCardContent>
      </HelpCard>
      
      {/* Snackbar for notifications */}
      <Snackbar
        open={openSnackbar}
        autoHideDuration={6000}
        onClose={handleSnackbarClose}
        message="Customer care notification sent to 8102986784"
        anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
      />
    </HelpContainer>
  );
};

export default Help;
