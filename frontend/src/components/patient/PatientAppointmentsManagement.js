import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../../App.css';

const PatientAppointmentsManagement = () => {
    const navigate = useNavigate();

    const handleMakeAppointment = () => {
        navigate('/view-specializations');
    };

    const handleUpcomingAppointments = () => {
        navigate('/upcoming-appointments-patient');
    };

    const handlePastAppointments = () => {
        navigate('/past-appointments-patient');
    };

    return (
        <div className="container">
            <h1>Patient Appointments Management</h1>
            <div className="button-container">
                <button className="button" onClick={handleMakeAppointment}>Make an Appointment</button>
                <button className="button" onClick={handleUpcomingAppointments}>Upcoming Appointments</button>
                <button className="button" onClick={handlePastAppointments}>Past Appointments</button>
            </div>
        </div>
    );
};

export default PatientAppointmentsManagement;
