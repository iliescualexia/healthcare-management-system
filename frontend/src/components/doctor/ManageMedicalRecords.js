import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../../App.css';

const ManageMedicalRecords = () => {
    const navigate = useNavigate();

    const handleCreateMedicalRecord = () => {
        navigate('/create-medical-record');
    };

    const handleViewAllMedicalRecords = () => {
        
        navigate('/view-medical-records-doctor');
    };

    return (
        <div className="container">
            <h1>Doctor Appointments Management</h1>
            <div className="button-container">
            <button className="button" onClick={handleCreateMedicalRecord}>Create New Medical Record</button>
            <button className="button" onClick={handleViewAllMedicalRecords}>View All Medical Records</button>
            </div>
        </div>
    );
};

export default ManageMedicalRecords;
