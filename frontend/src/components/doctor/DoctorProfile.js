import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../App.css'; 
import UrlConstants from '../../UrlConstants';

function ProfileDoctor() {
    const [doctor, setDoctor] = useState({
        firstName: '',
        lastName: '',
        user: {username: '', email: '', role: { name: '' } },
        gender: '',
        yearsOfExperience: 0,
        specializations: [],
        biography: ''
    });

    useEffect(() => {
        const fetchDoctorDetails = async () => {
            try {
                const token = localStorage.getItem('token');
                const username = localStorage.getItem('username');
                const response = await axios.get(`${UrlConstants.DOCTOR_URL}/${username}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                console.log(response.data);
                setDoctor(response.data);
            } catch (error) {
                console.error('Error fetching doctor details:', error);
            }
        };

        fetchDoctorDetails();
    }, []);

    const handleEditProfile = () => {
        localStorage.setItem('doctorUsername', doctor.user.username);
        window.location.href = `/edit-doctor/${doctor.user.username}`;
    };

    if (!doctor.user) {
        return <div>Loading...</div>;
    }

    return (
        <div className="container">
            <h1>Doctor Profile Information</h1>
            <div>
                <p><strong>First Name:</strong> {doctor.firstName}</p>
                <p><strong>Last Name:</strong> {doctor.lastName}</p>
                <p><strong>Username:</strong> {doctor.user.username}</p>
                <p><strong>Email:</strong> {doctor.user.email}</p>
                <p><strong>Role:</strong> {doctor.user.role.name}</p>
                <p><strong>Gender:</strong> {doctor.gender.name}</p>
                <p><strong>Years of Experience:</strong> {doctor.yearsOfExperience}</p>
                <p><strong>Specializations:</strong> {doctor.specializations.map(spec => spec.name).join(', ')}</p>
                <p><strong>Biography:</strong> {doctor.biography}</p>
            </div>
            <button type="button" className="button" onClick={handleEditProfile}>Edit Profile</button>
        </div>
    );
};

export default ProfileDoctor;
