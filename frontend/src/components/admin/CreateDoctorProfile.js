import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Select from 'react-select';
import UrlConstants from '../../UrlConstants';

function CreateDoctorProfile() {
  const [specializations, setSpecializations] = useState([]);
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('DOCTOR');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [gender, setGender] = useState('');
  const [yearsOfExperience, setYearsOfExperience] = useState('');
  const [selectedSpecializations, setSelectedSpecializations] = useState([]);
  const [biography, setBiography] = useState('');
  const [error, setError] = useState(null);

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
  

  const handleSubmit = async e => {
    e.preventDefault();

    try {

        const token = localStorage.getItem('token');
        const response = await axios.post(
        UrlConstants.SIGN_UP_DOCTOR_URL,
        {
            username : username,
            email : email,
            password : password,
            role : role,
            firstName : firstName,
            lastName : lastName,
            gender : gender,
            yearsOfExperience: parseInt(yearsOfExperience),
            specializations: Array.from(new Set(selectedSpecializations)),
            biography : biography
        },
        {
          headers: {
            Authorization: `Bearer ${token}`
          },
        }
      );

      const { message } = response.data;
      alert(message);
      window.location.href = '/manage-doctors';
      // Redirect or handle success scenario
    } catch (error) {
      setError('Registration failed. Please try again.');
    }
  };

  const handleSelectChange = selectedOptions => {
    const selectedValues = selectedOptions.map(option => option.value);
    setSelectedSpecializations(selectedValues);
  };

  return (
    <div className='container'>
      <h2>Create Doctor Profile</h2>
      <form onSubmit={handleSubmit}>
        {/* Input fields for doctor registration */}
        <div>
          <label>Username:</label>
          <input type="text" value={username} onChange={e => setUsername(e.target.value)} />
        </div>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={e => setEmail(e.target.value)} />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={e => setPassword(e.target.value)} />
        </div>
        <div>
          <label>First Name:</label>
          <input type="text" value={firstName} onChange={e => setFirstName(e.target.value)} />
        </div>
        <div>
          <label>Last Name:</label>
          <input type="text" value={lastName} onChange={e => setLastName(e.target.value)} />
        </div>
        <div className="form-group">
          <label htmlFor="gender">Gender</label>
          <select
            id="gender"
            value={gender}
            onChange={(e) => setGender(e.target.value)}
            required
          >
            <option value="">Select Gender</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
          </select>
        </div>
        <div>
          <label>Years of Experience:</label>
          <input type="number" value={yearsOfExperience} onChange={e => setYearsOfExperience(e.target.value)} />
        </div>
        <div>
          <label>Specializations:</label>
          <Select
            options={specializations}
            isMulti
            onChange={handleSelectChange}
          />
        </div>
        <div>
          <label>Biography:</label>
          <textarea value={biography} onChange={e => setBiography(e.target.value)}></textarea>
        </div>
        {error && <div className="alert alert-danger">{error}</div>}
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default CreateDoctorProfile;
