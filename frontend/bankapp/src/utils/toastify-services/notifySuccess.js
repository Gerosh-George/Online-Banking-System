import { toast } from "react-toastify";
const notifySuccess = ( message) => {
  toast.success(message, {
    position: "top-center",
    autoClose: 3000, // Notification will automatically close after 3 seconds
    hideProgressBar: false,
    closeOnClick: true,
    pauseOnHover: false,
    draggable: false,
  });
};

export default notifySuccess;
