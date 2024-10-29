import React, { useState, useEffect } from 'react';
import axios from 'axios';
import moment from 'moment';
import UrlConstants from '../../UrlConstants';
import '../../styles/PastAppointments.css' // Import the CSS file

const PastAppointmentsPatients = () => {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    const fetchPastAppointments = async () => {
      try {
        const token = localStorage.getItem('token');
        const username = localStorage.getItem('username');
        const response = await axios.get(`${UrlConstants.APPOINTMENTS_URL}/find-past-appointments-for-patient/${username}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        setAppointments(response.data);
      } catch (error) {
        console.error('Error fetching past appointments:', error);
      }
    };

    fetchPastAppointments();
  }, []);

  return (
    <div className="appointments-container">
      <h2>Past Appointments</h2>
      {appointments.length > 0 ? (
        <table className="appointments-table">
          <thead>
            <tr>
              <th>Patient</th>
              <th>Doctor</th>
              <th>Date and Time</th>
              <th>Reason</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {appointments.map(appointment => (
              <tr key={appointment.id}>
                <td>{appointment.patient.lastName} {appointment.patient.firstName}</td>
                <td>{appointment.doctor.lastName} {appointment.doctor.firstName}</td>
                <td>{moment(appointment.dateTime).format('MMMM Do YYYY, h:mm a')}</td>
                <td>{appointment.reason}</td>
                <td>{appointment.status.name}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No past appointments found.</p>
      )}
    </div>
  );
};

export default PastAppointmentsPatients;
