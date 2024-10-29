import React, { useState,useEffect  } from 'react';
import axios from 'axios';
import '../../styles/LoginPage.css';
import UrlConstants from '../../UrlConstants';

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  useEffect(() => {
    localStorage.clear();
  }, []);
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        UrlConstants.SIGN_IN_URL,
        {
          username,
          password,
        },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );

      const { token, username: loggedInUsername, role: roleOfUser } = response.data;

      // Set the session expiration time to 30 minutes from now
      const now = new Date();
      const expiryTime = now.getTime() + 30 * 60 * 1000; // 30 minutes in milliseconds

      localStorage.setItem('token', token);
      localStorage.setItem('username', loggedInUsername);
      localStorage.setItem('role', roleOfUser);
      localStorage.setItem('expiryTime', expiryTime);
      console.log(loggedInUsername);
      console.log(roleOfUser);

      window.location.href = '/home';
    } catch (error) {
      setError('Invalid credentials. Please try again.');
    }
  };

  const handleRegisterClick = () => {
    window.location.href = '/register';
  };

  return (
    <div className="login-container">
      <h1>Login</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <div className="alert alert-danger">{error}</div>}
        <button type="submit" className="login-button">Login</button>
        <br />
        <button type="button" className="login-button" onClick={handleRegisterClick}>Register as patient</button>
      </form>
    </div>
  );
}

export default LoginPage;
