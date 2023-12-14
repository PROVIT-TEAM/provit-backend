
import React from 'react'
import styled from 'styled-components';
import { ModalDimmer, ModalBody, ModalTitle, ModalButton } from '../styles/Modal';
import { signUpModalState } from '../recoil/modalState';
import { useRecoilState } from 'recoil';

interface SignInProps {
    closeModal: () => void;
}

const SignIn:React.FC<SignInProps> = ({closeModal}) => {
    const [signUpModal, setSignUpModal] = useRecoilState(signUpModalState);
    const SignUpClick = () => {
        setSignUpModal({isOpen: true});
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
                회원이 아니신가요?
                <SignUp onClick={SignUpClick}>회원가입 하기</SignUp>
                </CenterDiv>
              <CenterDiv>
                <div>
                  <InputBox type="text" placeholder="이메일" />
                </div>
                <div>
                  <InputBox type="text" placeholder="비밀번호"/>
                </div>
                <div>
                  <LoginButton>로그인 하기</LoginButton>
                  <div><FindPwdButton>비밀번호 찾기</FindPwdButton></div>
                </div>
              </CenterDiv>
              <ModalButton>간편 로그인</ModalButton>

            </ModalBody>
          </ModalDimmer>
        </>
    )
}

export default SignIn
const SignUp = styled.button`
    background: none;
    border: none;
    font-size: 15px;
    font-weight: bold;
    color: blue;
`
const InputBox = styled.input`
  width: 190px;
  height: 30px;
  margin-top: 15px;
  ::placeholder{
    font-weight: bold;
  }
`
const FindPwdButton = styled.button`
  margin-left: 8em;
  border: none;
  background: none;
`
const LoginButton = styled.button`
  border: none;
  color: white;
  background-color: blue;
  width: 196px;
  height: 36px;
  margin-top: 15px;
  font-weight: bold;
`
const CenterDiv = styled.div`
  text-align: center;
  border: none;
  padding: 5px 0px;
`

const ModalHeader = styled.div`
  display: contents;
  text-align: center;
  height: 60px;
  border: none;
`

const CloseButton = styled.button`
  text-align: center;
  background: none;
  border: none;
  width: 52px;
  height: 32px;
  margin-left: 85%;
  font-weight: bold;
`;