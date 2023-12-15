
import React from 'react'
import { signUpModalState } from '../recoil/modalState';
import { useRecoilState } from 'recoil';
import { LoginInfo, loginInput, loginTest, tokenTest } from '../api/RegistUser';
import { StyledModalDimmer, StyledModalBody, StyledModalTitle, StyledModalButton } from '../styles/Modal';
import { ModalHeader, CloseButton, CenterDiv, StyledInputBox, LoginButton, FindPwdButton, StyledSignUp } from '../styles/SignIn';

interface SignInProps {
    closeModal: () => void;
}

const SignIn:React.FC<SignInProps> = ({closeModal}) => {
    const [loginInfo, setLoginInfo] = useRecoilState<LoginInfo>(loginInput)
    const [signUpModal, setSignUpModal] = useRecoilState(signUpModalState);
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>, field: keyof LoginInfo) => {
      setLoginInfo({
        ...loginInfo,
        [field]: e.target.value,
      })
    };
    const SignUpClick = () => {
        setSignUpModal({isOpen: true});
        closeModal();
    }
    const loginClick = () => {
      console.log(loginInfo);
      loginTest(loginInfo);
    }
    const testClick = () => {
      tokenTest();
    }

    return (
        <>
        <StyledModalDimmer> 
            <StyledModalBody>

              <ModalHeader>
                <CloseButton onClick={closeModal}>X</CloseButton>
                <StyledModalTitle>PROVIT</StyledModalTitle>
              </ModalHeader>

              <CenterDiv>
                회원이 아니신가요?
                <StyledSignUp onClick={SignUpClick}>회원가입 하기</StyledSignUp>
                </CenterDiv>
              <CenterDiv>
                <div>
                  <StyledInputBox type="text" placeholder="이메일" 
                  onChange={(e)=>handleInputChange(e, 'username')}/>
                </div>
                <div>
                  <StyledInputBox type="text" placeholder="비밀번호"
                  onChange={(e)=>handleInputChange(e, 'password')}/>
                </div>
                <div>
                  <LoginButton onClick={loginClick}>로그인 하기</LoginButton>
                  <div>
                    <button onClick={testClick}>tokenTest</button>
                    <FindPwdButton>비밀번호 찾기</FindPwdButton>
                  </div>
                </div>
              </CenterDiv>
              <StyledModalButton>간편 로그인</StyledModalButton>

            </StyledModalBody>
          </StyledModalDimmer>
        </>
    )
}

export default SignIn