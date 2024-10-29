import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';

export const SidebarDataPatient = [
  {
    title: 'Home',
    path: '/home',
    icon: <AiIcons.AiFillHome color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'My Profile',
    path: '/my-profile-patient',
    icon: <FaIcons.FaUser color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Appointments',
    path: '/appointments',
    icon: <FaIcons.FaCalendar color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Medical Records',
    path: '/view-medical-records-patient',
    icon: <FaIcons.FaFileMedicalAlt color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Logout',
    path: '/logout',
    icon: <IoIcons.IoMdLogOut color='#f5f5f5'/>,
    cName: 'nav-text'
  }
];