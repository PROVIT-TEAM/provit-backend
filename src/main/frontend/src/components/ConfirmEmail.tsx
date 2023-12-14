import React from 'react'
import { CenterDiv, CloseButton, ModalBody, ModalDimmer, ModalHeader } from '../styles/Modal'
import { useRecoilState } from 'recoil'
import { confirmEmailModalState } from '../recoil/modalState'
import styled from 'styled-components'

const ConfirmEmail = () => {
    const [confirmModal, setConfirmModal] = useRecoilState(confirmEmailModalState)
    const closeModal = () => {
        setConfirmModal({isOpen: false})
    }
    const confirmClick = () => {
        // 서버에 인증번호 확인 요청()
        closeModal();
    }

  return (
    <>
    <ModalDimmer>
        <ModalBody>
            <ModalHeader>
            <CloseButton onClick={closeModal}>X</CloseButton>
                <div>인증코드를 이메일로 전송하였습니다.</div>
                <div>확인 후, 인증번호를 입력해주세요.</div>
            <CenterDiv>
                <CommonInputBox type="text" />
                <p></p>
                <ConfirmBtn onClick={confirmClick}>인증하기</ConfirmBtn>
            </CenterDiv>
            
            </ModalHeader>
        </ModalBody>
    </ModalDimmer>
    </>
    
  )
}

export default ConfirmEmail

const ConfirmBtn = styled.button`
  border: none;
  color: white;
  background-color: blue;
  width: 196px;
  height: 36px;
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