import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Signin } from './signin';
import { Register } from './register';
import { LandingPage } from './landing';
import Courts from './courts';
import AboutUs from './about';
import Navbar from './navbar';
import ServicesPage from './services';
import MyCases from './myCases';
import Organizations from './organizations';
import CaseForm from './caseForm';
import ContactUs from './contact';
import AllCases from './allCases';
import LegalTech from './legalTech';
import Calendar from './calendar';
import Help from './help';
import KnowledgeBase from './knowledgebase';
import KB1 from './kb1';
import KB2 from './kb2';
import KB3 from './kb3';

function App() {
  const isSignInOrRegister = window.location.pathname === '/' || window.location.pathname === '/register';

  return (
    <div className="App">
      <Router>
        {!isSignInOrRegister && <Navbar />}
        <Routes>
          <Route path="/" element={<Signin />} />
          <Route path="/register" element={<Register />} />
          <Route path="/landing" element={<LandingPage />} />
          <Route path="/courts" element={<Courts />} />
          <Route path="/about" element={<AboutUs />} />
          <Route path="/services" element={<ServicesPage />} />
          <Route path="/organizations" element={<Organizations />} />
          <Route path="/caseForm" element={<CaseForm />} />
          <Route path="/contact" element={<ContactUs />} />
          <Route path="/myCases" element={<MyCases />} />
          <Route path="/allCases" element={<AllCases />} />
          <Route path="/legalTech" element={<LegalTech />} />
          <Route path="/calendar" element={<Calendar />} />
          <Route path="/help" element={<Help />} />
          <Route path="/knowledgebase" element={<KnowledgeBase />} />
          <Route path="/KB1" element={<KB1 />} />
          <Route path="KB2" element={<KB2 />} />
          <Route path="KB3" element={<KB3 />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
