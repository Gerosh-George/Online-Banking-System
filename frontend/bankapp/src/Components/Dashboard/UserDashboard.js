import React,{useEffect} from "react";
import styled from "styled-components";
import { Container, Row, Col, Card, Button, Table } from "react-bootstrap";
import AccountDetails from "./AccountDetails";
import { getAccountDetails } from "../../utils/GetRequests";

const DashboardContainer = styled(Container)`
  background-color: #f5f5f5;
  padding: 20px;
  height: 80vh;
`;

const UserCard = styled(Card)`
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-bottom: 20px;

  h5 {
    font-weight: bold;
  }

  p {
    margin-top: 10px;
  }
`;

const QuickLinksCard = styled(Card)`
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;

  h6 {
    font-weight: bold;
  }

  ul {
    list-style: none;
    padding: 0;

    li {
      margin-bottom: 10px;

      a {
        text-decoration: none;
        color: #333;
        transition: color 0.3s;

        &:hover {
          color: #007bff;
        }
      }
    }
  }
`;

const RecentTransactionsCard = styled(Card)`
  .bg-primary.text-white {
    border-bottom: 0;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }
`;

const UserDashboard = () => {
  useEffect(() => {
    getAccountDetails(2);
  },[])
  return (
    <DashboardContainer fluid>
      <Row>
        <Col md={3}>
          <UserCard>
            <Card.Body>
              <h5 className="mb-3">Welcome, User!</h5>
              <p>
                <strong>Account Balance:</strong> $1,000.00
              </p>
              <Button variant="primary">View Account Details</Button>
            </Card.Body>
          </UserCard>
          {/* <QuickLinksCard>
            <Card.Body>
              <h6>Quick Links</h6>
              <ul>
                <li><a href="#">Transfer Money</a></li>
                <li><a href="#">Pay Bills</a></li>
                <li><a href="#">Transaction History</a></li>
              </ul>
            </Card.Body>
          </QuickLinksCard> */}
        </Col>
        <Col md={9}>
          <RecentTransactionsCard>
            <Card.Header className="bg-primary text-white">
              Recent Transactions
            </Card.Header>
            <Card.Body>
              <Table striped bordered hover responsive>
                <thead>
                  <tr>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Amount</th>
                  </tr>
                </thead>
                <tbody>
                  {/* Map through recent transactions data and display here */}
                </tbody>
              </Table>
            </Card.Body>
          </RecentTransactionsCard>
          
        </Col>
      </Row>
    </DashboardContainer>
  );
};

export default UserDashboard;
