import { toast } from "react-toastify";
const notifyError = ( message ) => {
  toast.error(message, {
    position: "top-center",
    autoClose: 3000,
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: false,
    draggable: false,
  });
};

export default notifyError;
