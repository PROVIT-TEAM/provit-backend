import React from 'react'
import { useRecoilState } from 'recoil'
import { CenterDiv, CloseButton, ModalBody, ModalButton, ModalDimmer, ModalHeader, ModalTitle } from '../styles/Modal'
import styled from 'styled-components'
import { UserInfo, inputState, postTest } from '../api/RegistUser'
import { confirmEmailModalState, signUpEmailModalState } from '../recoil/modalState'
import ConfirmEmail from './ConfirmEmail'

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
    // registUser(inputVal);
    postTest(inputVal);
  }
  const emailConfirmClick = () => {
    setConfirmModal({isOpen: true});
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
                <div>
                    <CommonInputBox
                      type="text" placeholder='아이디'
                      onChange={(e)=> handleInputChange(e, 'userId')}
                    />
                </div>
                <div>
                    <CommonInputBox
                      type="text" placeholder='비밀번호'
                      onChange={(e)=> handleInputChange(e, 'password')}
                    />
                </div>
                <div>
                    <CommonInputBox type="text" placeholder='비밀번호 확인'/>
                </div>
                <NameAndBirth>
                    <input
                      type="text" id='name' placeholder='이름'
                      onChange={(e)=> handleInputChange(e, 'name')}
                    />
                    <input
                      type="text" id='birth' placeholder='생년월일 6자리'
                      onChange={(e)=> handleInputChange(e, 'birth')}
                    />
                </NameAndBirth>
                <Label>
                    <input
                      type="text" placeholder='이메일'
                      onChange={(e)=> handleInputChange(e, 'email')}
                    />
                    <button onClick={emailConfirmClick}>인증</button>
                </Label>
                <div>
                    <input type="checkbox"  
                    onChange={(e)=> handleInputChange(e, 'marketing')} />
                    <span>전체동의</span>
                </div>
                <SubmitBtn onClick={signUpClick}>가입하기</SubmitBtn>
            </CenterDiv>
        </ModalBody>
    </ModalDimmer>
    {confirmModal.isOpen && (<>{<ConfirmEmail />}</>)}
    </>
  )
}

export default SignUpEmail
const DetailBtn = styled.button`
  border: none;
  background: none;
`
const SubmitBtn = styled.button`
    border: none;
  color: white;
  background-color: blue;
  width: 196px;
  height: 36px;
  margin-top: 15px;
  font-weight: bold;
`
const CommonInputBox = styled.input`
  width: 190px;
  height: 30px;
  margin-top: 15px;
  ::placeholder{
    font-weight: bold;
  }
`
const NameAndBirth = styled.div`
    margin-top: 15px;

    #name{
        width: 80px;
        height: 30px;
        margin-right: 10px;
    }

    #birth{
        width: 95px;
        height: 30px;

    }
`
const Label = styled.div`
  position: relative;

  input {
    width: 170px;
    height: 30px;
    margin-left: 10px;
    margin-top: 15px;
    ::placeholder{
    font-weight: bold;
  }
  }
  button {
    border: none;
    background-color: #4c8fca;
    color: white;
    font-weight: bold;
    height: 36px;
  }
`;