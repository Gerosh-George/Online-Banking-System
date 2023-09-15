import React from "react";
import { Link } from "react-router-dom";
import NavBar from "./NavBar";
import { Grid, Box } from "@mui/material";
import banner from "../banner.jpg";

function HomePage() {
  return (
    <>
      <NavBar />
      <Grid
        container
        direction="row"
        justifyContent="center"
        alignItems="center"
      >
        <img src={banner} alt="Logo" height={"500px"} />
      </Grid>
    </>
  );
}

export default HomePage;
