import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import '../../App.css';
import UrlConstants from '../../UrlConstants';

const EditDoctor = () => {
    const { username } = useParams();
    const navigate = useNavigate();
    const [doctor, setDoctor] = useState({
        firstName: '',
        lastName: '',
        user: { id: 0, username: '', email: '', role: { name: '' } },
        gender: { name: '' },
        yearsOfExperience: '',
        specializations: [],
        biography: ''
    });
    const [password, setPassword] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                const [doctorResponse, userResponse] = await Promise.all([
                    axios.get(`${UrlConstants.DOCTOR_URL}/${username}`, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    }),
                    axios.get(`${UrlConstants.USER_URL}/${username}`, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    })
                ]);
                setDoctor(doctorResponse.data);
                setPassword('');
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [username]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === 'password') {
                setPassword(value);
            } else{
            setDoctor(prevState => {
                if (name in prevState.user) {
                    return {
                        ...prevState,
                        user: {
                            ...prevState.user,
                            [name]: value
                        }
                    };
                }
                return {
                    ...prevState,
                    [name]: value
                };
            });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem('token');
            const userUpdateResponse = await axios.put(`${UrlConstants.USER_URL}`, {
                ...doctor.user,
                password: password
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            const doctorUpdateResponse = await axios.put(`${UrlConstants.DOCTOR_URL}`, doctor, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            alert('Doctor profile updated successfully');
            const role = localStorage.getItem('role');
            if(role === 'ADMIN'){
                navigate('/view-doctors');
            }else if(role === 'DOCTOR'){
                navigate('/my-profile-doctor');
            }
    
        } catch (error) {
            console.error('Error updating doctor profile:', error);
            alert('Failed to update doctor profile');
        }
    };

    return (
        <div className="container">
            <h1>Edit Doctor Profile</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>First Name:</label>
                    <input 
                        type="text" 
                        name="firstName" 
                        value={doctor.firstName} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input 
                        type="text" 
                        name="lastName" 
                        value={doctor.lastName} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Username:</label>
                    <input 
                        type="text" 
                        name="username" 
                        value={doctor.user.username} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input 
                        type="email" 
                        name="email" 
                        value={doctor.user.email} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Role:</label>
                    <p>{doctor.user.role.name}</p>
                </div>
                <div>
                    <label>Gender:</label>
                    <p>{doctor.gender.name}</p>
                </div>
                <div>
                    <label>Years of Experience:</label>
                    <input 
                        type="number" 
                        name="yearsOfExperience" 
                        value={doctor.yearsOfExperience} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Specializations:</label>
                    <p>{doctor.specializations.map(s => s.name).join(', ')}</p>
                </div>
                <div>
                    <label>Biography:</label>
                    <textarea 
                        name="biography" 
                        value={doctor.biography} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input 
                        type="password" 
                        name="password" 
                        value={password} 
                        onChange={handleChange} 
                    />
                </div>
                <button type="submit">Save Changes</button>
            </form>
        </div>
    );
};

export default EditDoctor;
