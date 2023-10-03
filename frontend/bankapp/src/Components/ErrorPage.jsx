import React from 'react';
import ErrorImg from '../error.jpg';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';

export default function ErrorPage() {
  return (
    <div>
      <div className="HomePage_container">
        <div className="left_side">
          <Link to="/">
            <Button variant="contained">Take me to somewhere safe.</Button>
          </Link>
        </div>
        <div className="right_side">
          <img src={ErrorImg} />
        </div>
      </div>
    </div>
  );
}