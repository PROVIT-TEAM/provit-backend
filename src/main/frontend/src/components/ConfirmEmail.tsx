import { StyledCenterDiv, StyledModalCloseButton, StyledModalBody, StyledModalDimmer, StyledModalHeader } from '../styles/Modal'
import { useRecoilState } from 'recoil'
import { confirmEmailModalState } from '../recoil/modalState'
import { StyledCommonInputBox, StyledConfirmBtn } from '../styles/ConfirmEmail'

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
    <StyledModalDimmer>
        <StyledModalBody>
            <StyledModalHeader>
            <StyledModalCloseButton onClick={closeModal}>X</StyledModalCloseButton>
                <div>인증코드를 이메일로 전송하였습니다.</div>
                <div>확인 후, 인증번호를 입력해주세요.</div>
            <StyledCenterDiv>
                <StyledCommonInputBox type="text" />
                <p></p>
                <StyledConfirmBtn onClick={confirmClick}>인증하기</StyledConfirmBtn>
            </StyledCenterDiv>
            
            </StyledModalHeader>
        </StyledModalBody>
    </StyledModalDimmer>
    </>   
)
}

export default ConfirmEmail