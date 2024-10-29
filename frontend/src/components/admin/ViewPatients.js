import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../App.css';
import UrlConstants from '../../UrlConstants';

const ViewPatients = () => {
    const [patients, setPatients] = useState([]);

    useEffect(() => {
        const fetchPatients = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(UrlConstants.PATIENT_URL, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setPatients(response.data);
                console.log(response.data);
            } catch (error) {
                console.error('Error fetching patients:', error);
            }
        };

        fetchPatients();
    }, []);

    const handleEditPatient = (username) => {
        localStorage.setItem('patientUsername', username);
        window.location.href = `/edit-patient/${username}`;
    };

    return (
        <div className="container">
            <h1>All Patients</h1>
            <div className="ul">
                {patients.map(patient => (
                    <div key={patient.user.username} className="patient-card">
                        <h2>{patient.firstName} {patient.lastName}</h2>
                        <p><strong>Username:</strong> {patient.user.username}</p>
                        <p><strong>Email:</strong> {patient.user.email}</p>
                        <p><strong>Role:</strong> {patient.user.role.name}</p>
                        <p><strong>Gender:</strong> {patient.gender.name}</p>
                        <p><strong>Street Address:</strong> {patient.streetAddress}</p>
                        <p><strong>City:</strong> {patient.address.city}</p>
                        <p><strong>County:</strong> {patient.address.county}</p>
                        <p><strong>Country:</strong> {patient.address.country}</p>
                        <p><strong>Postal Code:</strong> {patient.address.postalCode}</p>
                        <p><strong>Phone Number:</strong> {patient.phoneNumber}</p>
                        <button className="button" onClick={() => handleEditPatient(patient.user.username)}>Edit</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ViewPatients;
