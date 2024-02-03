import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import { Link } from 'react-router-dom';
import { IoMdContact } from "react-icons/io";
const navbarStyle = {
  backgroundColor: '#ADD8E6',
  display: 'flex',
  justifyContent: 'space-between',
    alignItems: 'center',
  color:'white' // Align items vertically in the center
};

const Navbar = () => {
  return (
    <AppBar position='fixed' >
      
        <div position="static" style={navbarStyle}>
         <div>
                 <h3> CASEWISE</h3>
                  </div>
        <div>
          <Link to="/organizations">
                      <Button >My Legal Firm</Button>
                  </Link>
                  <Link to="/services">
                      <Button >Services</Button>
                  </Link>
                  <Link to="/about">
                  
                      <Button >About</Button>
          </Link>
          <Link to="/contact"> 
            <Button >Contact</Button>
          </Link>
          <Link to="/knowledgebase"> 
            <Button >InfoHub</Button>
          </Link>

                  <Link to="/">
                      
            <Button >
            <div style={{height:'30px', width:'30px'}}>
                <IoMdContact size="30px" />
                </div>
            </Button>
          </Link>
          
                  
          </div>
              </div>
          
     
    </AppBar>
  );
};

export default Navbar;
