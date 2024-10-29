import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../App.css'; 
import UrlConstants from '../../UrlConstants';

function ProfilePatient() {
    const [patient, setPatient] = useState({
        firstName: '',
        lastName: '',
        user: {username: '', email: '', role: { name: '' } },
        gender: '',
        address : {id:0, streetAddress: '', city: '',county: '',country: '',postalCode: '',},
        phoneNumber: ''
    });
    useEffect(() => {
        const fetchUserDetails = async () => {
            try {
                const token = localStorage.getItem('token');
                const username = localStorage.getItem('username');
                const response = await axios.get(`${UrlConstants.PATIENT_URL}/${username}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                console.log(response.data);
                setPatient(response.data);
            } catch (error) {
                console.error('Error fetching user details:', error);
            }
        };

        fetchUserDetails();
    }, []);
    const handleEditProfile = () => {
        localStorage.setItem('patientUsername', patient.user.username);
        window.location.href = `/edit-patient/${patient.user.username}`;
      };

    if (!patient.user) {
        return <div>Loading...</div>;
    }

    return (
        <div className="container">
            <h1>Profile Information</h1>
            <div>
                <p><strong>First Name:</strong> {patient.firstName}</p>
                <p><strong>Last Name:</strong> {patient.lastName}</p>
                <p><strong>Username:</strong> {patient.user.username}</p>
                <p><strong>Email:</strong> {patient.user.email}</p>
                <p><strong>Role:</strong> {patient.user.role.name}</p>
                <p><strong>Gender:</strong> {patient.gender.name}</p>
                <p><strong>Street Address:</strong> {patient.address.streetAddress}</p>
                <p><strong>City:</strong> {patient.address.city}</p>
                <p><strong>County:</strong> {patient.address.county}</p>
                <p><strong>Country:</strong> {patient.address.country}</p>
                <p><strong>Postal Code:</strong> {patient.address.postalCode}</p>
                <p><strong>Phone Number:</strong> {patient.phoneNumber}</p>
            </div>
            <button type="button" className="button" onClick={handleEditProfile}>Edit Profile</button>
        </div>
    );
};

export default ProfilePatient;
