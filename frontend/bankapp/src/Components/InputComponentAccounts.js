import React from "react";
import { FormControl, InputLabel, Select, MenuItem } from "@mui/material";

export default function InputComponentAccounts({
  _value,
  _id,
  _changeHandler,
}) {
  return (
    <div>
      <FormControl fullWidth>
        <InputLabel>{_id}</InputLabel>
        <Select
          value={_value}
          onChange={_changeHandler}
        >
          <MenuItem value="SAVINGS">SAVINGS</MenuItem>
          <MenuItem value="SALARY">SALARY</MenuItem>
          <MenuItem value="CURRENT">CURRENT</MenuItem>
        </Select>
      </FormControl>
    </div>
  );
}
