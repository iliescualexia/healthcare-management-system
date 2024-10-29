import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../App.css'; 
import UrlConstants from '../../UrlConstants';

function ProfileAdmin() {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUserDetails = async () => {
            try {
                const token = localStorage.getItem('token');
                const username = localStorage.getItem('username');
                const response = await axios.get(`http://localhost:8080/api/user/${username}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                console.log(response.data);
                setUser(response.data);
            } catch (error) {
                console.error('Error fetching user details:', error);
            }
        };

        fetchUserDetails();
    }, []);
    const handleEditProfile = () => {
        window.location.href = '/edit-admin-profile';
      };

    if (!user) {
        return <div>Loading...</div>;
    }

    const { username, email, role } = user;

    return (
        <div className="container">
            <h1>Profile Information</h1>
            <div>
                <p><strong>Username:</strong> {username}</p>
                <p><strong>Email:</strong> {email}</p>
                <p><strong>Role:</strong> {role.name}</p>
            </div>
            <button type="button" className="button" onClick={handleEditProfile}>Edit Profile</button>
        </div>
    );
};

export default ProfileAdmin;
