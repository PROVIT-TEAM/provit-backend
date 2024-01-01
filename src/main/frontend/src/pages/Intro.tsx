import { StyledHeader, StyledButton, StyledDiv } from "../styles/Intro";
import SignUp from "../components/SignUp";
import { useRecoilState } from "recoil";
import { loginModalState, modalState, signUpEmailModalState, signUpModalState } from "../recoil/modalState";
import SignIn from "../components/SignIn";
import SignUpEmail from "../components/SignUpEmail";
import { naver, reload } from "../apis/api/UserApi";
import { instance } from "../apis/utils/instance";

function Intro2() {
  const [modal, setModal] = useRecoilState(modalState)
  const [loginModal, setLoginModal] = useRecoilState(loginModalState);
  const [signUpModal, setSignUpModal] = useRecoilState(signUpModalState);
  const [signUpEmailModal, setSignUpEmailModal] = useRecoilState(signUpEmailModalState)

  const openSignUpModal = () => {
    setModal({ isOpen: true, content: <SignUp closeModal={closeModal} /> });
  };
  
  const openSignInModal = () => {
    setModal({ isOpen: true, content: <SignIn closeModal={closeModal} /> });
  };

  const openSignUpEmailModal = () => {
    setModal({ isOpen: true, content: <SignUpEmail closeModal={closeModal} /> });
  };

  const closeModal = () => {
    setModal({ isOpen: false, content: null });
  };

  return (  
    <>
    <StyledHeader>
      <h2>PROVIT</h2>
      <StyledDiv>
        <StyledButton onClick={openSignInModal}>로그인</StyledButton>
        <StyledButton onClick={openSignUpModal}>회원가입</StyledButton>
      </StyledDiv>
      <StyledButton><a href="http://localhost:9090/oauth2/google">구글</a></StyledButton>
      
    </StyledHeader>
    
    {modal.isOpen && (<>{modal.content}</>)}
    {loginModal.isOpen && (<>{<SignIn closeModal={() => setLoginModal({isOpen: false})} />}</>)}
    {signUpModal.isOpen && (<>{<SignUp closeModal={() => setSignUpModal({isOpen: false})} />}</>)}
    {signUpEmailModal.isOpen && (<>{<SignUpEmail closeModal={() => setSignUpEmailModal({isOpen: false})} />}</>)}
    </>
  );
}

export default Intro2;