import React, { useState } from 'react';
import axios from 'axios';
import UrlConstants from '../../UrlConstants';
import '../../App.css'; 

const MedicalRecordForm = () => {
  const [patientUsername, setPatientUsername] = useState('');
  const [dateOfVisit, setDateOfVisit] = useState('');
  const [diagnosis, setDiagnosis] = useState('');
  const [treatmentPlan, setTreatmentPlan] = useState('');
  const [medications, setMedications] = useState([{ name: '', description: '', dosage: '', frequency: '' }]);

  const handleAddMedication = () => {
    setMedications([...medications, { name: '', description: '', dosage: '', frequency: '' }]);
  };

  const handleMedicationChange = (index, field, value) => {
    const updatedMedications = [...medications];
    updatedMedications[index][field] = value;
    setMedications(updatedMedications);
  };

  const formatDateOfVisit = (date) => {
    const selectedDate = new Date(date);
    const year = selectedDate.getFullYear();
    const month = (`0${selectedDate.getMonth() + 1}`).slice(-2);
    const day = (`0${selectedDate.getDate()}`).slice(-2);
    const hours = (`0${selectedDate.getHours()}`).slice(-2);
    const minutes = (`0${selectedDate.getMinutes()}`).slice(-2);
    const seconds = (`0${selectedDate.getSeconds()}`).slice(-2);

    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');
    const doctorUsername = localStorage.getItem('username');

    try {
     console.log(dateOfVisit)
      await axios.post(`${UrlConstants.MEDICAL_RECORDS_URL}`, {
        patientUsername,
        doctorUsername,
        dateOfVisit,
        diagnosis,
        treatmentPlan,
        prescribedMedication: medications
      }, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      alert('Medical record created successfully');
      setPatientUsername('');
      setDateOfVisit('');
      setDiagnosis('');
      setTreatmentPlan('');
      setMedications([{ name: '', description: '', dosage: '', frequency: '' }]);
      window.location.href = "/manage-medical-records";
    } catch (error) {
      console.error('Error creating medical record:', error);
      alert('Error creating medical record. Please try again.');
    }
  };

  return (
    <div className="container">
      <h2>Create Medical Record</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Patient Username:</label>
          <input type="text" value={patientUsername} onChange={(e) => setPatientUsername(e.target.value)} />
        </div>
        <div className="form-group">
          <label>Date of Visit:</label>
          <input type="datetime-local" value={dateOfVisit} onChange={(e) => setDateOfVisit(e.target.value)} />
        </div>
        <div className="form-group">
          <label>Diagnosis:</label>
          <input type="text" value={diagnosis} onChange={(e) => setDiagnosis(e.target.value)} />
        </div>
        <div className="form-group">
          <label>Treatment Plan:</label>
          <textarea value={treatmentPlan} onChange={(e) => setTreatmentPlan(e.target.value)} />
        </div>
        <div className="medications">
          <h3>Medications:</h3>
          {medications.map((medication, index) => (
            <div key={index}>
              <label>Name:</label>
              <input type="text" value={medication.name} onChange={(e) => handleMedicationChange(index, 'name', e.target.value)} />
              <label>Description:</label>
              <input type="text" value={medication.description} onChange={(e) => handleMedicationChange(index, 'description', e.target.value)} />
              <label>Dosage:</label>
              <input type="text" value={medication.dosage} onChange={(e) => handleMedicationChange(index, 'dosage', e.target.value)} />
              <label>Frequency:</label>
              <input type="text" value={medication.frequency} onChange={(e) => handleMedicationChange(index, 'frequency', e.target.value)} />
            </div>
          ))}
          <button className="button" type="button" onClick={handleAddMedication}>Add Medication</button>
        </div>
        <button className="button" type="submit">Submit</button>
      </form>
    </div>
  );
};

export default MedicalRecordForm;
