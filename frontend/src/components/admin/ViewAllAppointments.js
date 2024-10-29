import React, { useState, useEffect } from 'react';
import axios from 'axios';
import moment from 'moment';
import UrlConstants from '../../UrlConstants';
import '../../App.css';

const ViewAllAppointments = () => {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get(`${UrlConstants.APPOINTMENTS_URL}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        setAppointments(response.data);
      } catch (error) {
        console.error('Error fetching appointments:', error);
      }
    };

    fetchAppointments();
  }, []);

  return (
    <div className="container">
      <h2>All Appointments</h2>
      {appointments.length > 0 ? (
        <table className="table">
          <thead>
            <tr>
              <th>Doctor Name</th>
              <th>Patient Name</th>
              <th>Date of Appointment</th>
              <th>Reason</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {appointments.map(appointment => (
              <tr key={appointment.id}>
                <td>{appointment.doctor.lastName} {appointment.doctor.firstName}</td>
                <td>{appointment.patient.lastName} {appointment.patient.firstName}</td>
                <td>{moment(appointment.dateTime).format('MMMM Do YYYY, h:mm a')}</td>
                <td>{appointment.reason}</td>
                <td>{appointment.status.name}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No appointments found.</p>
      )}
    </div>
  );
};

export default ViewAllAppointments;
