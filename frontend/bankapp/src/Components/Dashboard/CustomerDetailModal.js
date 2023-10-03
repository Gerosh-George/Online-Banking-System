import React, { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import styled from "styled-components";
import { updateCustomerDetails } from "../../utils/GetRequests";

const EditableField = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
`;

const EditIcon = styled(FontAwesomeIcon)`
  margin-right: 10px;
  cursor: pointer;
  color: #007bff;
`;

const FieldName = styled.span`
  font-weight: bold;
  margin-right: 5px;
`;

const FieldInput = styled(Form.Control)`
  font-weight: normal;
  flex-grow: 1;
`;

const CustomerDetailModal = ({
  show,
  onHide,
  customerDetails,
  globalRefresh,
  setGlobalRefresh,
}) => {
  const [editableName, setEditableName] = useState(customerDetails.name);
  const [editableEmail, setEditableEmail] = useState(customerDetails.email);
  const [editablePassword, setEditablePassword] = useState(
    customerDetails.password
  );
  const [editableMobile, setEditableMobile] = useState(customerDetails.mobile);
  const [editableAadhar, setEditableAadhar] = useState(customerDetails.aadhar);
  const [editableDob, setEditableDob] = useState(customerDetails.dob);
  const [editableAddress, setEditableAddress] = useState(
    customerDetails.address
  );
  const [editableFathername, setEditableFathername] = useState(
    customerDetails.fathername
  );
  const [editableMothername, setEditableMothername] = useState(
    customerDetails.mothername
  );

  useEffect(() => {
    setEditableName(customerDetails.name);
    setEditableEmail(customerDetails.email);
    setEditablePassword(customerDetails.password);
    setEditableMobile(customerDetails.mobile);
    setEditableAadhar(customerDetails.aadhar);
    setEditableDob(customerDetails.dob);
    setEditableAddress(customerDetails.address);
    setEditableFathername(customerDetails.fathername);
    setEditableMothername(customerDetails.mothername);
  }, [customerDetails]);

  const handleNameChange = (e) => {
    setEditableName(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEditableEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setEditablePassword(e.target.value);
  };

  const handleMobileChange = (e) => {
    setEditableMobile(e.target.value);
  };

  const handleAadharChange = (e) => {
    setEditableAadhar(e.target.value);
  };

  const handleDobChange = (e) => {
    setEditableDob(e.target.value);
  };

  const handleAddressChange = (e) => {
    setEditableAddress(e.target.value);
  };

  const handleFathernameChange = (e) => {
    setEditableFathername(e.target.value);
  };

  const handleMothernameChange = (e) => {
    setEditableMothername(e.target.value);
  };
  const handleSubmitButton = () => {
    const data = {
      customerId: customerDetails.customerId,
      name: editableName,
      email: editableEmail,
      password: editablePassword,
      mobile: editableMobile,
      aadhar: editableAadhar,
      dob: editableDob,
      address: editableAddress,
      fathername: editableFathername,
      mothername: editableMothername,
    };
    console.log(data);
    updateCustomerDetails(data);
    setGlobalRefresh(!globalRefresh);
    onHide();
  };
  return (
    <Modal show={show} onHide={onHide}>
      <Modal.Header closeButton>
        <Modal.Title>Customer Details</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Name:</FieldName>
          <FieldInput
            type="text"
            value={editableName}
            onChange={handleNameChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Email:</FieldName>
          <FieldInput
            type="text"
            value={editableEmail}
            onChange={handleEmailChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Password:</FieldName>
          <FieldInput
            type="text"
            value={editablePassword}
            onChange={handlePasswordChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Mobile:</FieldName>
          <FieldInput
            type="text"
            value={editableMobile}
            onChange={handleMobileChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Aadhar:</FieldName>
          <FieldInput
            type="text"
            value={editableAadhar}
            onChange={handleAadharChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>DOB:</FieldName>
          <FieldInput
            type="text"
            value={editableDob}
            onChange={handleDobChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Address:</FieldName>
          <FieldInput
            type="text"
            value={editableAddress}
            onChange={handleAddressChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Father Name:</FieldName>
          <FieldInput
            type="text"
            value={editableFathername}
            onChange={handleFathernameChange}
            readOnly={false}
          />
        </EditableField>
        <EditableField>
          <EditIcon icon={faEdit} onClick={() => {}} />
          <FieldName>Mother Name:</FieldName>
          <FieldInput
            type="text"
            value={editableMothername}
            onChange={handleMothernameChange}
            readOnly={false}
          />
        </EditableField>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onHide}>
          Close
        </Button>
        <Button variant="primary" onClick={handleSubmitButton}>
          Save Changes
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default CustomerDetailModal;
