import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Container,
  Grid,
  Card,
  CardContent,
  Typography,
} from "@mui/material";
import "./App.css";

const pageStyle = {
  backgroundColor: "#27445C", // Navy blue background color for the entire page
  color: "white", // White text color
  minHeight: "100vh", // Make the container cover the full viewport height
  minWidth: "100vw",
  display: "flex", // Use flexbox to center content vertically and horizontally
  flexDirection: "column", // Arrange children vertically
  justifyContent: "center", // Center content vertically
  alignItems: "center", // Center content horizontally
};

const cardStyle = {
  backgroundColor: "#27445C", // Navy blue background color for cards
  color: "white", // White text color
};

const cardContentStyle = {
  backgroundColor: "#f0f8ff", // Very light blue background color for card content
  color: "navy", // Navy text color
  minHeight: "120px", // Adjust the minimum height as needed
};

function Courts() {
  const [courtTypes, setCourtTypes] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/v1/courtTypes")
      .then((response) => {
        setCourtTypes(response.data);
      })
      .catch((error) => {
        console.error("Error fetching court types:", error);
      });
  }, []);

  return (
    <Container maxWidth="lg" style={pageStyle}>
      <Typography
        variant="h4"
        component="h1"
        align="center"
        gutterBottom
        style={{ fontFamily: "DancingScripts", fontSize: "36px" }}
      >
        Types Of Courts
      </Typography>
      <Grid container spacing={2}>
        {courtTypes.map((courtType) => (
          <Grid item xs={12} sm={6} md={4} key={courtType.courtTypeId}>
            <Card style={cardStyle}>
              <CardContent style={cardContentStyle}>
                <Typography variant="h6" component="div">
                  {courtType.courtTypeName}
                </Typography>
                <Typography color="textSecondary">
                  {courtType.courtTypeDescription}
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}

export default Courts;
