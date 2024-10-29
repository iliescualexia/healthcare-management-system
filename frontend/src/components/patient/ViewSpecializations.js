import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../App.css';
import UrlConstants from '../../UrlConstants';

const ViewSpecializations = () => {
    const [specializations, setSpecializations] = useState([]);

    useEffect(() => {
        const fetchSpecializations = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(UrlConstants.SPECIALIZATION_URL, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                console.log(response.data);

                const data = response.data;

                const options = data.map(specialization => ({
                    label: specialization.name,
                    value: specialization.name
                }));
                setSpecializations(options);
            } catch (error) {
                console.error('Error fetching specializations:', error);
            }
        };

        fetchSpecializations();
    }, []);

    const handleSelect = (specialization) => {
        window.location.href = `/view-doctors/${specialization.label}`;
    };

    return (
        <div className="container">
            <h1>Select a Specialization</h1>
            <ul className="specializations-list">
                {specializations.map(specialization => (
                    <li key={specialization.value} className="specialization-item">
                        <span>{specialization.label}</span>
                        <button className="select-button" onClick={() => handleSelect(specialization)}>Select</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ViewSpecializations;
