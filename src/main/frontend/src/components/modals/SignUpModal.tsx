import React from 'react'
import styled from 'styled-components';
import useModal from '../../hook/modal';
import kakao from '../../assets/images/kakao_login.png';
import naver from '../../assets/images/naver_login.png';
import google from '../../assets/images/google_login.png';
import { CommonBtn } from '../../styles/button/commonBtn';
import { ModalBackground, ModalContainer, ModalHeader, ModalTitle, CloseButton, ModalContent} from '../../styles/modal';
import { Link } from 'react-router-dom';


const SignUpModal = () => {

    const {closeModal} = useModal();
    const handleBackroundClick = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
        if (e.target === e.currentTarget){
            closeModal();
        }
    }

    return (
    <ModalBackground onClick={handleBackroundClick}>
        <ModalContainer height='550px'>

            <ModalHeader>
                <CloseButton onClick={closeModal}>&times;</CloseButton>
            </ModalHeader>

            <ModalContent>
                <StyledLogo>PROVIT</StyledLogo>
                <a href=''><img src={kakao}/></a>
                <CommonBtn margin='10px 0px 0px 0px'><StyledLink to="/signUpInfo">이메일로 가입하기</StyledLink></CommonBtn>
                <StyledSeperator>또는</StyledSeperator>
                <div>
                    <StyledSocialBtn href=""><img src={naver} style={{ width: '48px', height: '48px' }}/></StyledSocialBtn>
                    <StyledSocialBtn href=""><img src={google} style={{ width: '48px', height: '48px' }}/></StyledSocialBtn>
                </div>
                <StyledP>이미 회원이신가요? <Link to="/login" style={{color: '#446FF6', textDecoration: 'none'}}>로그인하기</Link></StyledP>
            </ModalContent>

        </ModalContainer>
    </ModalBackground>
    
    );
};

const StyledSocialBtn = styled.a`
    margin: 0px 5px;
`
const StyledP = styled.p`
    margin-top: 50px 
`
const StyledLink = styled(Link)`
    color: white;
    font-weight: bold;
`
const StyledSeperator = styled.p`
    display: flex;
    flex-basis: 100%;
    align-items: center;
    color: #7c7c7c;
    font-size: 12px;
    margin: 35px 0px 25px 0px;

    &::before,
    &::after {
        content: "";
        flex-grow: 1;
        background: #7c7c7c;
        height: 1px;
        font-size: 0;
        line-height: 0;
        margin: 0 16px;
    }
    `
const StyledLogo = styled.div`
    text-align: center;
    justify-content: center;
    height: 160px;
    line-height: 160px;
    font-size: 2.0em;
    font-weight: bold;
    margin-bottom: 10px;
`

export default SignUpModal;