import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../App.css';
import UrlConstants from '../../UrlConstants';

const ViewDoctors = () => {
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        const fetchDoctors = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(UrlConstants.DOCTOR_URL, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setDoctors(response.data);
            } catch (error) {
                console.error('Error fetching doctors:', error);
            }
        };

        fetchDoctors();
    }, []);

    const handleEditDoctor = (username) => {
        localStorage.setItem('doctorUsername', username);
        window.location.href = `/edit-doctor/${username}`;
    };

    return (
        <div className="container">
            <h1>All Doctors</h1>
            <div className="ul">
                {doctors.map(doctor => (
                    <div key={doctor.user.username} className="doctor-card">
                        <h2>Dr. {doctor.firstName} {doctor.lastName}</h2>
                        <p><strong>Username:</strong> {doctor.user.username}</p>
                        <p><strong>Email:</strong> {doctor.user.email}</p>
                        <p><strong>Role:</strong> {doctor.user.role.name}</p>
                        <p><strong>Gender:</strong> {doctor.gender.name}</p>
                        <p><strong>Years of Experience:</strong> {doctor.yearsOfExperience}</p>
                        <p><strong>Specializations:</strong> {doctor.specializations.map(s => s.name).join(', ')}</p>
                        <p><strong>Biography:</strong> {doctor.biography}</p>
                        <button className="button" onClick={() => handleEditDoctor(doctor.user.username)}>Edit</button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ViewDoctors;
