import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';

export const SidebarDataDoctor = [
  {
    title: 'Home',
    path: '/home',
    icon: <AiIcons.AiFillHome color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'My Profile',
    path: '/my-profile-doctor',
    icon: <FaIcons.FaUser color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'My Appointments',
    path: '/my-appointments',
    icon: <FaIcons.FaCalendar color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Medical Records',
    path: '/manage-medical-records',
    icon: <FaIcons.FaPills color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Logout',
    path: '/logout',
    icon: <IoIcons.IoMdLogOut color='#f5f5f5'/>,
    cName: 'nav-text'
  }
];