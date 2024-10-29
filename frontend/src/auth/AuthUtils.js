const isLoggedInCheck = () => {
    const token = localStorage.getItem('token');
    return !!token;
  };
  
  const authenticate = (navigate) => {
    if (!isLoggedInCheck()) {
  
      window.location.href ="http://localhost:3000/login";
      // navigate("/login");
    }
  };
  export { authenticate, isLoggedInCheck };
