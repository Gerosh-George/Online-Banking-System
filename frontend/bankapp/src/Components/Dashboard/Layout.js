import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import Sidebar from "./SideBar"; // Import your Sidebar component
import UserDashboard from "./UserDashboard";
import NavBar from "../NavBar";
import Appbar from "./Appbar";
import AccountDetails from "./AccountDetails";
import AccountDetailsModal from "./AccountDetailsModal";

const Layout = () => {
  
  return (
    <>
      <Appbar />
    
      <Container fluid>
        <Row>
          <Col md={3}>
            <Sidebar />
          </Col>
          <Col md={9}>
            <UserDashboard />
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Layout;
