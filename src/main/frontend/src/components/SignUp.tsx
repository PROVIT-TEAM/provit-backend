import React from 'react'
import { ModalDimmer, ModalBody, ModalTitle, ModalHeader, CloseButton, CenterDiv } from '../styles/Modal';
import styled from 'styled-components';
import { useRecoilState } from 'recoil';
import { loginModalState, signUpEmailModalState } from '../recoil/modalState';

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
        <ModalDimmer> 
            <ModalBody>

              <ModalHeader>
                <CloseButton onClick={closeModal}>X</CloseButton>
                <ModalTitle>PROVIT</ModalTitle>
              </ModalHeader>

              <CenterDiv>
                이미 회원이신가요?
                <LoginBtn onClick={LoginClick}>로그인하기</LoginBtn>
                </CenterDiv>
              <CenterDiv>
                <div>
                  <KaKao>카카오로 가입하기</KaKao>
                </div>
                <div>
                  <EmailLoginBtn onClick={EmailClick}>이메일로 가입하기</EmailLoginBtn>
                </div>
              </CenterDiv>
              <CenterDiv>
                <p>다른 방식으로 가입하기</p>
              </CenterDiv>

            </ModalBody>
          </ModalDimmer>
        </>
    )
}

export default SignUp

const LoginBtn = styled.button`
    background: none;
    border: none;
    font-size: 15px;
    font-weight: bold;
    color: blue;
`
const KaKao = styled.button`
  border: none;
  color: black;
  background-color: none;
  width: 196px;
  height: 36px;
  margin-top: 15px;
  font-weight: bold;
`
const EmailLoginBtn = styled.button`
  border: none;
  color: white;
  background-color: blue;
  width: 196px;
  height: 36px;
  margin-top: 15px;
  font-weight: bold;
`
