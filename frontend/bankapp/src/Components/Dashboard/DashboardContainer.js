import React from "react";
import { Grid, Container, Box } from "@mui/material";
import MediaCard from "../MediaCard";
import LoginPage from "../LoginPage";
import FormComponent from "../FormComponent";
import { Form } from "react-router-dom";
import ListComponent from "./ListComponent";
export default function DashboardContainer() {
  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={4}>
          <Container>
            <Box sx={{ bgcolor: "#cfe8fc", height: "80vh" }}>
            <ListComponent />
            </Box>
          </Container>
        </Grid>
        <Grid item xs={8}>
          

         
          
        </Grid>
      </Grid>
    </>
  );
}
