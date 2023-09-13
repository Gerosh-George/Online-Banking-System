import React from "react";
import { Grid, Container, Box } from "@mui/material";
import MediaCard from "./MediaCard";
export default function DashboardContainer() {
  return (
    <>
      <Grid container spacing={2}  >
        <Grid item xs={4}>
          <Container >
            <Box sx={{ bgcolor: "#cfe8fc", height: "80vh" }} >
                <MediaCard />
            </Box>
          </Container>
        </Grid>
        <Grid item xs={8}>
          <Container >
            <Box sx={{ bgcolor: "#cfe8fc", height: "80vh" }} />
          </Container>
        </Grid>
      </Grid>
    </>
  );
}
