import React from "react";
import { AiOutlineUser } from "react-icons/ai";
import { useLocation } from "react-router-dom";
import { isLoggedInCheck } from "../auth/AuthUtils";

import {
  Nav,
  Navbar,
  NavbarBrand,
  NavbarText,
  NavItem,
  NavLink,
} from "reactstrap";

const Header = () => {
  const headerText = ['/login', '/'];
 
  const isLoggedIn = isLoggedInCheck();
  const username = localStorage.getItem("username");
  const location = useLocation();
  const isLoginPage = headerText.includes(location.pathname);
  const isRegisterPage = location.pathname == '/register';

  return (
    <div>
      <Navbar color="primary" light expand="md">
        <NavbarBrand href={isLoggedIn ? "/welcome-user" : "/login"} style={{ color: "#f0f0f0" }}>
          Healthcare Management System
        </NavbarBrand>
        <Nav className="mr-auto" navbar>
          <NavItem>
            <NavLink style={{ color: "#f0f0f0" }}>
              { isRegisterPage ? "Register" : 
              isLoggedIn && !isLoginPage ? `Hello, ${username}!` : "Login to see more details"
              }
            </NavLink>
          </NavItem>
        </Nav>
        <NavbarText>
          <div>
            <AiOutlineUser />
          </div>
        </NavbarText>
      </Navbar>
    </div>
  );
};

export default Header;
