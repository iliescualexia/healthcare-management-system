import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const SessionTimeoutHandler = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const checkSessionTimeout = () => {
      const expiryTime = localStorage.getItem('expiryTime');
      if (expiryTime && new Date().getTime() > expiryTime) {
        localStorage.clear();
        navigate('/login');
      }
    };

    checkSessionTimeout();
    const interval = setInterval(checkSessionTimeout, 1000 * 60);

    return () => clearInterval(interval);
  }, [navigate]);

  return null;
};

export default SessionTimeoutHandler;
