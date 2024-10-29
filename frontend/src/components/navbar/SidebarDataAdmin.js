import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';

export const SidebarDataAdmin = [
  {
    title: 'Home',
    path: '/home',
    icon: <AiIcons.AiFillHome color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'My Profile',
    path: '/my-profile-admin',
    icon: <FaIcons.FaUser color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Manage Doctors',
    path: '/manage-doctors',
    icon: <FaIcons.FaUserMd color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Manage Patients',
    path: '/manage-patients',
    icon: <FaIcons.FaHeart color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'View All Appointments',
    path: '/view-all-appointments',
    icon: <FaIcons.FaCalendar color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'View All Medical Records',
    path: '/view-all-medical-records',
    icon: <FaIcons.FaCalendar color='#f5f5f5'/>,
    cName: 'nav-text'
  },
  {
    title: 'Logout',
    path: '/logout',
    icon: <IoIcons.IoMdLogOut color='#f5f5f5'/>,
    cName: 'nav-text'
  }
];