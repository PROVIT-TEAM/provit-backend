import { useNavigate } from "react-router-dom";

const useModal = () => {
    const navigate = useNavigate();
    const closeModal = () => {
        navigate('/');
    };

    return {closeModal}
}

export default useModal;