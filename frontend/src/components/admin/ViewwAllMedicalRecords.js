import React, { useState, useEffect } from 'react';
import axios from 'axios';
import UrlConstants from '../../UrlConstants';
import '../../App.css'; 

const ViewAllMedicalRecords = () => {
  const [medicalRecords, setMedicalRecords] = useState([]);

  useEffect(() => {
    const fetchMedicalRecords = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get(`${UrlConstants.MEDICAL_RECORDS_URL}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        setMedicalRecords(response.data);
      } catch (error) {
        console.error('Error fetching medical records:', error);
      }
    };

    fetchMedicalRecords();
  }, []);

  return (
    <div className="container">
      <h2>All Medical Records</h2>
      {medicalRecords.length > 0 ? (
        <table className="table">
          <thead>
            <tr>
              <th>Doctor Name</th>
              <th>Patient Name</th>
              <th>Date of Visit</th>
              <th>Diagnosis</th>
              <th>Treatment Plan</th>
              <th>Prescribed Medications</th>
            </tr>
          </thead>
          <tbody>
            {medicalRecords.map(record => (
              <tr key={record.id}>
                <td>{record.doctor.lastName} {record.doctor.firstName}</td>
                <td>{record.patient.lastName} {record.patient.firstName}</td>
                <td>{new Date(record.dateOfVisit).toLocaleString()}</td>
                <td>{record.diagnosis}</td>
                <td>{record.treatmentPlan}</td>
                <td>
                  {record.prescribedMedication.map((med, index) => (
                    <div key={index}>
                      <strong>Name:</strong> {med.name}, <strong>Description:</strong> {med.description}, <strong>Dosage:</strong> {med.dosage}, <strong>Frequency:</strong> {med.frequency}
                    </div>
                  ))}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No medical records found.</p>
      )}
    </div>
  );
};

export default ViewAllMedicalRecords;
