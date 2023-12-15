import React from 'react'
import { useRecoilState } from 'recoil';
import { loginModalState, signUpEmailModalState } from '../recoil/modalState';
import { StyledModalDimmer, StyledModalBody, StyledModalTitle, StyledModalHeader, StyledModalCloseButton, StyledCenterDiv } from '../styles/Modal';
import { StyledEmailLogin, StyledKaKao, StyledLoginBtn } from '../styles/SignUp';

interface SignUpProps {
    closeModal: () => void;
}

const SignUp: React.FC<SignUpProps> = ({closeModal}) => {
    const [loginModal, setLoginModal] = useRecoilState(loginModalState);
    const [signUpEmailModal, setSignUpEmailModal] = useRecoilState(signUpEmailModalState)
    const LoginClick = () => {
        setLoginModal({isOpen: true});
        closeModal();
    }
    const EmailClick = () => {
      setSignUpEmailModal({isOpen: true});
      closeModal();
    }

    return (
        <>
        <StyledModalDimmer> 
            <StyledModalBody>

              <StyledModalHeader>
                <StyledModalCloseButton onClick={closeModal}>X</StyledModalCloseButton>
                <StyledModalTitle>PROVIT</StyledModalTitle>
              </StyledModalHeader>

              <StyledCenterDiv>
                이미 회원이신가요?
                <StyledLoginBtn onClick={LoginClick}>로그인하기</StyledLoginBtn>
                </StyledCenterDiv>
              <StyledCenterDiv>
                <div>
                  <StyledKaKao>카카오로 가입하기</StyledKaKao>
                </div>
                <div>
                  <StyledEmailLogin onClick={EmailClick}>이메일로 가입하기</StyledEmailLogin>
                </div>
              </StyledCenterDiv>
              <StyledCenterDiv>
                <p>다른 방식으로 가입하기</p>
              </StyledCenterDiv>

            </StyledModalBody>
          </StyledModalDimmer>
        </>
    )
}

export default SignUp

