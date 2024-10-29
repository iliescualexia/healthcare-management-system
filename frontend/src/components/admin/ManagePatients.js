import React from 'react';
import '../../App.css'; // Import the CSS file

function ManagePatients() {
    const handleCreatePatientClick = () => {
        window.location.href = '/create-patient';
      };
      const handleViewPatients = () => {
        window.location.href = '/view-patients';
      };
  return (
    <div className="container">
      <h2>Manage Patients</h2>
      <div>
        <button type="button" className="button" onClick={handleCreatePatientClick}>Create Patient Profile</button> {/* Apply the button class */}
        <br></br>
        <button type="button" className="button" onClick={handleViewPatients}>View Patients</button> {/* Apply the button class */}
      </div>
    </div>
  );
}

export default ManagePatients;
