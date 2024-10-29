import React, { useState, useEffect } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import Modal from 'react-modal';
import axios from 'axios';
import UrlConstants from '../../UrlConstants';
import { useParams } from 'react-router-dom';

const localizer = momentLocalizer(moment);

const AppointmentCalendar = () => {
  const [events, setEvents] = useState([]);
  const [selectedDate, setSelectedDate] = useState(null);
  const [selectedHour, setSelectedHour] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [idPatient, setIdPatient] = useState(0);
  const [idDoctor, setIdDoctor] = useState(0);
  const [reason, setReason] = useState('');
  const { username } = useParams();
  const [fetchedAppointments, setFetchedAppointments] = useState([]);

  const fetchDoctorAppointments = async (doctorId) => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.get(`${UrlConstants.APPOINTMENTS_URL}/find-appointments-by-doctorId/${doctorId}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      const appointments = response.data.map(appointment => ({
        id: appointment.id,
        title: appointment.reason,
        start: new Date(appointment.dateTime),
        end: moment(new Date(appointment.dateTime)).add(1, 'hour').toDate()
      }));

      setFetchedAppointments(appointments);
    } catch (error) {
      console.error('Error fetching doctor appointments:', error);
    }
  };

  useEffect(() => {
    const fetchPatientAndDoctor = async () => {
      try {
        const token = localStorage.getItem('token');
        const usernamePatient = localStorage.getItem('username');
        
        const patientResponse = await axios.get(`${UrlConstants.PATIENT_URL}/${usernamePatient}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        
        const doctorResponse = await axios.get(`${UrlConstants.DOCTOR_URL}/${username}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });

        setIdPatient(patientResponse.data.id);
        setIdDoctor(doctorResponse.data.id);
        fetchDoctorAppointments(doctorResponse.data.id);
      } catch (error) {
        console.error('Error fetching patient or doctor data:', error);
      }
    };

    fetchPatientAndDoctor();
  }, [username]);

  // Handle slot selection
  const handleSlotSelect = ({ start }) => {
    const selectedDate = moment(start).format('YYYY-MM-DD');
    setSelectedDate(selectedDate);
  };

  // Generate available time slots for the selected date
  const generateTimeSlots = () => {
    const timeSlots = [];
    if (!selectedDate) return timeSlots;

    const selectedDateAppointments = fetchedAppointments.filter(event =>
      moment(event.start).format('YYYY-MM-DD') === selectedDate
    );

    for (let hour = 9; hour <= 17; hour++) {
      const startTime = moment(`${selectedDate} ${hour}:00`).toDate();
      const endTime = moment(`${selectedDate} ${hour + 1}:00`).toDate();

      const isSlotAvailable = !selectedDateAppointments.some(appointment =>
        moment(appointment.start).isSame(startTime)
      );

      if (isSlotAvailable) {
        timeSlots.push({
          id: `${selectedDate}-${hour}`,
          title: moment(startTime).format('HH:mm'),
          start: startTime,
          end: endTime,
        });
      }
    }
    return timeSlots;
  };

  // Handle event selection
  const handleEventSelect = (event) => {
    const selectedHour = moment(event.start).format('HH:mm');
    setSelectedHour(selectedHour);
    setIsModalOpen(true);
  };

  // Close modal
  const closeModal = () => {
    setIsModalOpen(false);
    setReason('');
  };

  // Handle confirm button click
  const handleConfirm = async () => {
    try {
      const appointmentData = {
        patient: {
          id: idPatient
        },
        doctor: {
          id: idDoctor
        },
        dateTime: `${selectedDate}T${selectedHour}:00`,
        reason: reason,
        status: {
          name: "PENDING"
        }
      };

      const response = await axios.post(`${UrlConstants.APPOINTMENTS_URL}`, appointmentData, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      });
      console.log('Appointment created:', response.data);
      closeModal();
      window.location.href = `/appointments`;
    } catch (error) {
      console.error('Error creating appointment:', error);
    }
  };

  return (
    <div>
      <h2>Appointment Calendar</h2>
      {!isModalOpen && (
        <div style={{ height: 500 }}>
          <Calendar
            localizer={localizer}
            events={events.concat(selectedDate ? generateTimeSlots() : [])}
            startAccessor="start"
            endAccessor="end"
            selectable
            onSelectSlot={handleSlotSelect}
            onSelectEvent={handleEventSelect}
            style={{ height: '100%' }}
          />
        </div>
      )}
      {selectedDate && (
        <Modal isOpen={isModalOpen} onRequestClose={closeModal}>
          <h3>Confirm Appointment</h3>
          <p>Selected Date: {moment(selectedDate).format('MMMM DD, YYYY')}</p>
          <p>Selected Hour: {selectedHour}</p>
          <label htmlFor="reason">Reason:</label>
          <input
            type="text"
            id="reason"
            value={reason}
            onChange={(e) => setReason(e.target.value)}
          />
          <button onClick={closeModal}>Cancel</button>
          <button onClick={handleConfirm}>Confirm</button>
        </Modal>
      )}
    </div>
  );
};

export default AppointmentCalendar;
