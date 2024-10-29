import React, { useEffect } from "react";
import "../styles/Home.css";
import { authenticate } from "../auth/AuthUtils.js";
import { useNavigate } from "react-router-dom";
function Home() {
  const navigate = useNavigate();
  authenticate(navigate);
  return (
    <div className="home-container">
      <h1 className="welcome-text">
        Welcome {localStorage.getItem('loggedInUsername')}! <br/>
      </h1>
    </div>
  );
}

export default Home;
