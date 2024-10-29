import React from 'react';
import '../../App.css'; // Import the CSS file

function ManageDoctors() {
    const handleCreateDoctorClick = () => {
        window.location.href = '/create-doctor';
      };
      const handleViewDoctors = () => {
        window.location.href = '/view-doctors';
      };
  return (
    <div className="container">
      <h2>Manage Doctors</h2>
      <div>
        <button type="button" className="button" onClick={handleCreateDoctorClick}>Create Doctor Profile</button> {/* Apply the button class */}
        <br></br>
        <button type="button" className="button" onClick={handleViewDoctors}>View Doctors</button> {/* Apply the button class */}
      </div>
    </div>
  );
}

export default ManageDoctors;
