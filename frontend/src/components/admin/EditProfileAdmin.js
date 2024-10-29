import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../App.css';
import UrlConstants from '../../UrlConstants';

function EditAdminProfile() {
    const [user, setUser] = useState({
        id : 0,
        username: '',
        email: '',
        role: '',
        password: ''
    });

    useEffect(() => {
        const fetchUserDetails = async () => {
            try {
                const token = localStorage.getItem('token');
                const username1 = localStorage.getItem('username');
                const response = await axios.get(`${UrlConstants.USER_URL}/${username1}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                const {id, username, email, role } = response.data;
                setUser({ id,username, email, role, password: '' });
            } catch (error) {
                console.error('Error fetching user details:', error);
            }
        };

        fetchUserDetails();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem('token');
            await axios.put(`${UrlConstants.USER_URL}`
            , user, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            alert('Profile updated successfully');
            window.location.href = '/my-profile-admin';
        } catch (error) {
            console.error('Error updating profile:', error);
            alert('Failed to update profile');
        }
    };

    return (
        <div className="container">
            <h1>Edit Profile</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input 
                        type="text" 
                        name="username" 
                        value={user.username} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input 
                        type="email" 
                        name="email" 
                        value={user.email} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Role:</label>
                    <p>{user.role.name}</p>
                </div>
                <div>
                    <label>Password:</label>
                    <input 
                        type="password" 
                        name="password" 
                        value={user.password} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <button className = "button" type="submit">Save Changes</button>
            </form>
        </div>
    );
}

export default EditAdminProfile;
