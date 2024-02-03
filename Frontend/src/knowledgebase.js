import React from 'react';
import { Link } from 'react-router-dom';
import {
  Card,
  CardContent,
  Typography,
  Button,
  Container,
  Grid,
  styled,
} from '@mui/material';
import kb from './kb.jpg'
const KnowledgeableContainer = styled(Container)({
  display: 'flex',
  justifyContent: 'center',
  height: '100vh',
  
});

const KnowledgeableCard = styled(Card)({
  width: '100%', // Set width to 100% to cover the entire container
  margin: '10px',
  display: 'flex',
  flexDirection: 'column',
  textAlign: 'center',
  alignItems:'center',
  justifyContent: 'center',
  height: '100%', // Set height to 100% to cover the entire container
  borderRadius: 0,
   // Remove card border radius for a cleaner look
});

const Knowledgeable = () => {
  return (
    <>
    <div style={{backgroundColor:'#f5f5f5'}}>
    <h3 style={{fontFamily:'cursive',marginTop:'70px',fontSize:'50px'}}>Knowledge Base</h3>
    <KnowledgeableContainer>
        
      <Grid container spacing={2}>
        {/* Card 1 */}
        <Grid item xs={12} sm={6}>
          <KnowledgeableCard>
            <CardContent>
            <img src={kb} alt="knowledgebase" style={{ width: '100px', height: '70px' }} />

              <Typography variant="h6" gutterBottom>
                How to Add and View Cases
              </Typography>
              <Typography variant="body2">
                Learn how to add and view legal cases in the system.
                  </Typography>
                  
              <Button component={Link} to="/kb1" color="primary">
                Learn More
                    </Button>
                    
            </CardContent>
          </KnowledgeableCard>
        </Grid>

        {/* Card 2 */}
        <Grid item xs={12} sm={6}>
          <KnowledgeableCard>
            <CardContent>
            <img src={kb} alt="knowledgebase" style={{ width: '100px', height: '70px' }} />


              <Typography variant="h6" gutterBottom>
                Schedule Court Details
              </Typography>
              <Typography variant="body2">
                Understand how to schedule court details for cases.
              </Typography>
              <Button component={Link} to="/kb2" color="primary">
                Learn More
              </Button>
            </CardContent>
          </KnowledgeableCard>
        </Grid>

        {/* Card 3 */}
        <Grid item xs={12} sm={6}>
          <KnowledgeableCard>
            <CardContent>
            <img src={kb} alt="knowledgebase" style={{ width: '100px', height: '70px' }} />


              <Typography variant="h6" gutterBottom>
                Client Intake
              </Typography>
              <Typography variant="body2">
                Why Choose Us?
              </Typography>
              <Button component={Link} to="/kb3" color="primary">
                Learn More
              </Button>
            </CardContent>
          </KnowledgeableCard>
        </Grid>

        {/* Card 4 */}
        <Grid item xs={12} sm={6}>
          <KnowledgeableCard>
            <CardContent>
            <img src={kb} alt="knowledgebase" style={{ width: '100px', height: '70px'}} />

              <Typography variant="h6" gutterBottom>
                Help
              </Typography>
              <Typography variant="body2">
                Need assistance? Visit our Help Center.
              </Typography>
              <Button component={Link} to="/help" color="primary">
                Get Help
              </Button>
            </CardContent>
          </KnowledgeableCard>
        </Grid>
      </Grid>
    </KnowledgeableContainer>
    </div>
    </>
  );
};

export default Knowledgeable;
