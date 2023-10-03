import React from "react";
import { TextField } from "@mui/material";
export default function InputComponent({
  _value,
  _id,
  _changeHandler,
  _placeholder,
  _type
}) {
  return (
    <div>
      <TextField
        label={_id}
        value={_value}
        placeholder={_placeholder}
        fullWidth
        required
        onChange={_changeHandler}
        style={{ margin: "5px" }}
        type={_type ? _type : "text"}
      />
    </div>
  );
}
