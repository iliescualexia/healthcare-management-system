import React from "react";
import { BrowserRouter as Router, Route, Routes, useLocation, Navigate } from "react-router-dom";
import { Col, Row } from "reactstrap";
import LoginPage from './components/login/LoginPage';
import Header from './components/Header';
import { isLoggedInCheck } from './auth/AuthUtils';
import Home from './components/Home';
import Navbar from './components/navbar/Navbar';
import Logout from './components/login/Logout';
import SessionTimeoutHandler from './components/SessionTimeoutHandler';
import RegisterPage from './components/register/RegisterPage';
import ManageDoctors from "./components/admin/ManageDoctors";
import CreateDoctorProfile from "./components/admin/CreateDoctorProfile";
import ProfileAdmin from "./components/admin/ProfileAdmin";
import EditProfileAdmin from "./components/admin/EditProfileAdmin"
import ViewDoctors from "./components/admin/ViewDoctors";
import EditDoctor from "./components/admin/EditDoctor";
import ManagePatients from "./components/admin/ManagePatients";
import CreatePatientProfile from "./components/admin/CreatePatientProfile";
import ViewPatients from "./components/admin/ViewPatients";
import EditPatient from "./components/admin/EditPatient";
import ProfilePatient from "./components/patient/ProfilePatient";
import PatientAppointmentsManagement from "./components/patient/PatientAppointmentsManagement";
import ViewSpecializations from "./components/patient/ViewSpecializations";
import ViewDoctorsForSpecialization from "./components/patient/ViewDoctorsForSpecialization";
import DayScheduleButton from "./components/patient/Appointment";
import Appointment from "./components/patient/Appointment";
import PastAppointmentsPatients from "./components/patient/PastAppointmentPatient";
import UpcomingAppointmentsPatients from "./components/patient/UpcomingAppointmentsPatients";
import ProfileDoctor from "./components/doctor/DoctorProfile";
import DoctorAppointmentsManagement from "./components/doctor/DoctorAppointmentsManagement";
import DoctorRequestedAppointments from "./components/doctor/DoctorRequestedAppointments";
import UpcomingAppointmentsDoctors from "./components/doctor/UpcomingAppoinmentsDoctor";
import PastAppointmentsDoctors from "./components/doctor/PastAppointmentsDoctor";
import ManageMedicalRecords from "./components/doctor/ManageMedicalRecords";
import MedicalRecordForm from "./components/doctor/MedicalRecordForm";
import ViewMedicalRecordsDoctor from "./components/doctor/ViewMedicalRecordsDoctor";
import ViewPatientMedicalRecords from "./components/patient/ViewPatientMedicalRecords";
import ViewAllMedicalRecords from "./components/admin/ViewwAllMedicalRecords";
import ViewAllAppointments from "./components/admin/ViewAllAppointments";
function AppContent() {
  const NoSideNavRoutes = ['/login', '/','/register'];
  const location = useLocation();
  const isNoSideNav = NoSideNavRoutes.includes(location.pathname);

  return (
    <>
      <SessionTimeoutHandler />
      {!isNoSideNav && isLoggedInCheck() && <Navbar />}
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/home" element={<Home />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/manage-doctors" element={<ManageDoctors />} />
        <Route path="/create-doctor" element={<CreateDoctorProfile />} />
        <Route path="/my-profile-admin" element={<ProfileAdmin />} />
        <Route path="/edit-admin-profile" element={<EditProfileAdmin />} />
        <Route path="/view-doctors" element = {<ViewDoctors/>} />
        <Route path="/edit-doctor/:username" element={<EditDoctor />} />
        <Route path="/manage-patients" element={<ManagePatients />} />
        <Route path="/create-patient" element={<CreatePatientProfile />} />
        <Route path="/view-patients" element = {<ViewPatients/>} />
        <Route path="/edit-patient/:username" element={<EditPatient />} />
        <Route path="/my-profile-patient" element={<ProfilePatient />} />
        <Route path="/appointments" element={<PatientAppointmentsManagement/>}/>
        <Route path="/view-specializations" element = {<ViewSpecializations/>}/>
        <Route path="/view-doctors/:specialization" element = {<ViewDoctorsForSpecialization/>}/>
        <Route path="/book/:username" element = {<Appointment/>}/>
        <Route path="/past-appointments-patient" element = {<PastAppointmentsPatients/>}/>
        <Route path="/upcoming-appointments-patient" element = {<UpcomingAppointmentsPatients/>}/>
        <Route path="/my-profile-doctor" element={<ProfileDoctor />} />
        <Route path="/my-appointments" element={<DoctorAppointmentsManagement/>}/>
        <Route path="/requested-appointments" element={<DoctorRequestedAppointments/>}/>
        <Route path="/upcoming-appointments-doctor" element = {<UpcomingAppointmentsDoctors/>}/>
        <Route path="/past-appointments-doctor" element = {<PastAppointmentsDoctors/>}/>
        <Route path="/manage-medical-records" element = {<ManageMedicalRecords/>}/>
        <Route path="/create-medical-record" element = {<MedicalRecordForm/>}/>
        <Route path="/view-medical-records-doctor" element = {<ViewMedicalRecordsDoctor/>}/>
        <Route path="/view-medical-records-patient" element = {<ViewPatientMedicalRecords/>}/>
        <Route path="/view-all-medical-records" element = {<ViewAllMedicalRecords/>}/>
        <Route path="/view-all-appointments" element = {<ViewAllAppointments/>}/>
      </Routes>
    </>
  );
}

function App() {
  const styles = {
    contentDiv: {
      display: "flex",
    },
    contentMargin: {
      marginLeft: "10px",
      width: "100%",
    },
  };

  return (
    <div>
      <Router>
        <Row>
          <Col>
            <Header />
          </Col>
        </Row>
        <div style={styles.contentDiv}>
          <AppContent />
       </div>
       </Router>
    </div>
  );
}

export default App;
