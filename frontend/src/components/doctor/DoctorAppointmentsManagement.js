import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../../App.css';

const DoctorAppointmentsManagement = () => {
    const navigate = useNavigate();

    const handleReuqestedAppointment = () => {
        navigate('/requested-appointments');
    };

    const handleUpcomingAppointments = () => {
        navigate('/upcoming-appointments-doctor');
    };

    const handlePastAppointments = () => {
        navigate('/past-appointments-doctor');
    };

    return (
        <div className="container">
            <h1>Doctor Appointments Management</h1>
            <div className="button-container">
            <button className="button" onClick={handleReuqestedAppointment}>Requested Appointments</button>
                <button className="button" onClick={handleUpcomingAppointments}>Upcoming Appointments</button>
                <button className="button" onClick={handlePastAppointments}>Past Appointments</button>
            </div>
        </div>
    );
};

export default DoctorAppointmentsManagement;
