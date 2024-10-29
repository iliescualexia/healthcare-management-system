import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import '../../App.css';
import UrlConstants from '../../UrlConstants';

const EditPatient = () => {
    const { username } = useParams();
    const navigate = useNavigate();
    const [patient, setPatient] = useState({
        firstName: '',
        lastName: '',
        user: { id: 0, username: '', email: '', role: { name: '' } },
        gender: '',
        address : {id:0, streetAddress: '', city: '',county: '',country: '',postalCode: '',},
        phoneNumber: ''
    });
    const [password, setPassword] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const token = localStorage.getItem('token');
                const [patientResponse, userResponse] = await Promise.all([
                    axios.get(`${UrlConstants.PATIENT_URL}/${username}`, {
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
                setPatient(prevState => ({
                    ...patientResponse.data,
                    user: userResponse.data,
                    address: patientResponse.data.address
                }));
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
        } else {
            setPatient(prevState => {
                if (name in prevState.user) {
                    return {
                        ...prevState,
                        user: {
                            ...prevState.user,
                            [name]: value
                        }
                    };
                }
                if(name in prevState.address){
                    return{
                        ...prevState,
                        address: {
                            ...prevState.address,
                            [name] : value
                        }
                    }
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
            console.log(patient.user.id);
            await axios.put(`${UrlConstants.USER_URL}`, {
                ...patient.user,
                password: password
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            await axios.put(`${UrlConstants.PATIENT_URL}`, patient, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            alert('Patient profile updated successfully');
            const role = localStorage.getItem('role');
            if(role === 'ADMIN'){
                navigate('/view-patients');
            }else if(role === 'PATIENT'){
                navigate('/my-profile-patient');
            }
 
        } catch (error) {
            console.error('Error updating patient profile:', error);
            alert('Failed to update patient profile');
        }
    };

    return (
        <div className="container">
            <h1>Edit Patient Profile</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>First Name:</label>
                    <input 
                        type="text" 
                        name="firstName" 
                        value={patient.firstName} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input 
                        type="text" 
                        name="lastName" 
                        value={patient.lastName} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Username:</label>
                    <input 
                        type="text" 
                        name="username" 
                        value={patient.user.username} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Email:</label>
                    <input 
                        type="email" 
                        name="email" 
                        value={patient.user.email} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Role:</label>
                    <p>{patient.user.role.name}</p>
                </div>
                <div>
                    <label>Gender:</label>
                    <select 
                        name="gender" 
                        value={patient.gender.name} 
                        onChange={handleChange} 
                        required
                    >
                        <option value="">Select Gender</option>
                        <option value="MALE">Male</option>
                        <option value="FEMALE">Female</option>
                    </select>
                </div>
                <div>
                    <label>Street Address:</label>
                    <input 
                        type="text" 
                        name="streetAddress" 
                        value={patient.address.streetAddress} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>City:</label>
                    <input 
                        type="text" 
                        name="city" 
                        value={patient.address.city} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>County:</label>
                    <input 
                        type="text" 
                        name="county" 
                        value={patient.address.county} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Country:</label>
                    <input 
                        type="text" 
                        name="country" 
                        value={patient.address.country} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Postal Code:</label>
                    <input 
                        type="text" 
                        name="postalCode" 
                        value={patient.address.postalCode} 
                        onChange={handleChange} 
                        required 
                    />
                </div>
                <div>
                    <label>Phone Number:</label>
                    <input 
                        type="text" 
                        name="phoneNumber" 
                        value={patient.phoneNumber} 
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

export default EditPatient;
