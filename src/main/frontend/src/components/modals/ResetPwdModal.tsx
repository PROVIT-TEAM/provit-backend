import React from 'react'
import useModal from '../../hook/modal';
import { ModalBackground, ModalContainer, ModalHeader, ModalTitle, CloseButton, ModalContent, StyledLabel} from '../../styles/modal';
import { CommonBtn } from '../../styles/button/commonBtn';
import { CommonInput } from '../../styles/input/commonInput';
import styled from 'styled-components';

const ResetPwdModal = () => {
    const {closeModal} = useModal();
    const handleBackroundClick = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
        if (e.target === e.currentTarget){
            closeModal();
        }
    }

    return (
    <ModalBackground onClick={handleBackroundClick}>
        <ModalContainer>

            <ModalHeader>
                <CloseButton onClick={closeModal}>&times;</CloseButton>
                <ModalTitle>비밀번호 재설정</ModalTitle>
            </ModalHeader>

            <ModalContent>
            
            <StyledDiv>
            <StyledLabel>이메일</StyledLabel>
                <CommonInput
                    type="password"  placeholder='이메일을 입력해주세요.'/>
                <CommonBtn margin='10px 0px 0px 0px;'>인증코드 발송</CommonBtn>
            </StyledDiv>
            </ModalContent>

        </ModalContainer>
    </ModalBackground>
    
    );
}

const StyledDiv = styled.div`
    margin-top: 120px;
`
export default ResetPwdModal