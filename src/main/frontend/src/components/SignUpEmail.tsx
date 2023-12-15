import React from 'react'
import ConfirmEmail from './ConfirmEmail'
import { useRecoilState } from 'recoil'
import { UserInfo, inputState, postTest } from '../api/RegistUser'
import { confirmEmailModalState, signUpEmailModalState } from '../recoil/modalState'
import { StyledCenterDiv, StyledModalCloseButton, StyledModalBody, StyledModalDimmer, StyledModalHeader, StyledModalTitle } from '../styles/Modal'
import { StyledCommonInputBox, StyledNameAndBirth, StyledLabel, StyledSubmitBtn } from '../styles/SignUpEmail'

interface SignUpEmailProps {
    closeModal: () => void,
}

const SignUpEmail:React.FC<SignUpEmailProps> = ({closeModal}) => {
  const [inputVal, setInputVal] = useRecoilState<UserInfo>(inputState)
  const [confirmModal, setConfirmModal] = useRecoilState(confirmEmailModalState)
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>, field: keyof UserInfo) => {
    setInputVal({
      ...inputVal,
      [field]: e.target.value,
    })
  };
  const signUpClick = () => {
    console.log(inputVal);
    postTest(inputVal);
  }
  const emailConfirmClick = () => {
    setConfirmModal({isOpen: true});
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
                <div>
                    <StyledCommonInputBox
                      type="text" placeholder='아이디'
                      onChange={(e)=> handleInputChange(e, 'userId')}
                    />
                </div>
                <div>
                    <StyledCommonInputBox
                      type="text" placeholder='비밀번호'
                      onChange={(e)=> handleInputChange(e, 'password')}
                    />
                </div>
                <div>
                    <StyledCommonInputBox type="text" placeholder='비밀번호 확인'/>
                </div>
                <StyledNameAndBirth>
                    <input
                      type="text" id='name' placeholder='이름'
                      onChange={(e)=> handleInputChange(e, 'name')}
                    />
                    <input
                      type="text" id='birth' placeholder='생년월일 6자리'
                      onChange={(e)=> handleInputChange(e, 'birth')}
                    />
                </StyledNameAndBirth>
                <StyledLabel>
                    <input
                      type="text" placeholder='이메일'
                      onChange={(e)=> handleInputChange(e, 'email')}
                    />
                    <button onClick={emailConfirmClick}>인증</button>
                </StyledLabel>
                <div>
                    <input type="checkbox"  
                    onChange={(e)=> handleInputChange(e, 'marketing')} />
                    <span>전체동의</span>
                </div>
                <StyledSubmitBtn onClick={signUpClick}>가입하기</StyledSubmitBtn>
            </StyledCenterDiv>
        </StyledModalBody>
    </StyledModalDimmer>
    {confirmModal.isOpen && (<>{<ConfirmEmail />}</>)}
    </>
  )
}

export default SignUpEmail
