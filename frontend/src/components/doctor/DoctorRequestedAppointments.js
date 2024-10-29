import React, { useState, useEffect } from 'react';
import axios from 'axios';
import moment from 'moment';
import UrlConstants from '../../UrlConstants';
import '../../styles/DoctorRequestedAppointments.css'; // Import the CSS file

const DoctorRequestedAppointments = () => {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    const fetchRequestedAppointments = async () => {
      try {
        const token = localStorage.getItem('token');
        const username = localStorage.getItem('username');
        const response = await axios.get(`${UrlConstants.APPOINTMENTS_URL}/find-requested-appointments-for-doctor/${username}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        setAppointments(response.data);
      } catch (error) {
        console.error('Error fetching requested appointments:', error);
      }
    };

    fetchRequestedAppointments();
  }, []);

  const handleAccept = async (appointmentId) => {
    try {
      const token = localStorage.getItem('token');
      await axios.put(`${UrlConstants.APPOINTMENTS_URL}/confirm-appointment/${appointmentId}`,{}, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setAppointments(appointments.filter(appointment => appointment.id !== appointmentId));
      alert("Appointment accepted");
      window.location.href="/my-appointments";
      console.log('Appointment accepted:', appointmentId);
    } catch (error) {
      console.error('Error accepting appointment:', error);
    }
  };

  const handleDecline = async (appointmentId) => {
    try {
      const token = localStorage.getItem('token');
      await axios.put(`${UrlConstants.APPOINTMENTS_URL}/refuse-appointment/${appointmentId}`,{}, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      setAppointments(appointments.filter(appointment => appointment.id !== appointmentId));
      alert("Appointment declined");
      window.location.href="/my-appointments";
      console.log('Appointment declined:', appointmentId);
    } catch (error) {
      console.error('Error declining appointment:', error);
    }
  };

  return (
    <div className="appointments-container">
      <h2>Requested Appointments</h2>
      {appointments.length > 0 ? (
        <table className="appointments-table">
          <thead>
            <tr>
              <th>Patient</th>
              <th>Date and Time</th>
              <th>Reason</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {appointments.map(appointment => (
              <tr key={appointment.id}>
                <td>{appointment.patient.lastName} {appointment.patient.firstName}</td>
                <td>{moment(appointment.dateTime).format('MMMM Do YYYY, h:mm a')}</td>
                <td>{appointment.reason}</td>
                <td>
                  <button className="accept-btn" onClick={() => handleAccept(appointment.id)}>Accept</button>
                  <button className="decline-btn" onClick={() => handleDecline(appointment.id)}>Decline</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No requested appointments found.</p>
      )}
    </div>
  );
};

export default DoctorRequestedAppointments;
