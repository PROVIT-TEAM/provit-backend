import React from 'react'
import styled from 'styled-components';
import useModal from '../../hook/modal';
import naver from '../../assets/images/naver_login.png';
import google from '../../assets/images/google_login.png';
import kakao from '../../assets/images/kakao_round.png';
import { CommonBtn } from '../../styles/button/commonBtn';
import { ModalBackground, ModalContainer, ModalHeader, CloseButton, ModalContent, StyledLabel} from '../../styles/modal';
import { CommonInput } from '../../styles/input/commonInput';
import { Link } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { loginInput } from '../../recoil/atom'
import { emailLogin } from '../../apis/api/UserApi';

const LoginModal = () => {
    const {closeModal} = useModal();
    const handleBackroundClick = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
        if (e.target === e.currentTarget){
            closeModal();
        }
    }
    const [loginInfo, setLoginInfo] = useRecoilState(loginInput);
    const InputHandler = (field: keyof typeof loginInfo, value: string) => {
        setLoginInfo((prev) => ({...prev, [field]: value}));
    }
    const LoginBtnClicked = () => {
        emailLogin(loginInfo);
    }

    return (
    <ModalBackground onClick={handleBackroundClick}>
        <ModalContainer>

            <ModalHeader>
                <CloseButton onClick={closeModal}>&times;</CloseButton>
            </ModalHeader>

            <ModalContent>
                <StyledLogo>PROVIT</StyledLogo>
                <StyledLabel>이메일</StyledLabel>
                <CommonInput
                    type="text" placeholder='이메일을 입력해주세요.'
                    value={loginInfo.email} onChange={(e) => InputHandler('email', e.target.value)}
                />

                <StyledLabel>비밀번호</StyledLabel>
                <CommonInput
                    type="password"  placeholder='비밀번호를 입력해주세요.'
                    value={loginInfo.password} onChange={(e) => InputHandler('password', e.target.value)}
                />
                <CommonBtn margin='10px 0px 0px 0px;' onClick={LoginBtnClicked}>로그인</CommonBtn>

                <StyledNav>
                    <Link to="/signUp">회원가입</Link>
                    <Link to="/resetPwd">비밀번호 찾기</Link>
                </StyledNav>
                
                <StyledSeparator>간편 로그인</StyledSeparator>
                <div>
                    <StyledSocialBtn href=""><img src={kakao} style={{ width: '48px', height: '48px' }}/></StyledSocialBtn>
                    <StyledSocialBtn href=""><img src={naver} style={{ width: '48px', height: '48px' }}/></StyledSocialBtn>
                    <StyledSocialBtn href=""><img src={google} style={{ width: '48px', height: '48px' }}/></StyledSocialBtn>
                </div>
            </ModalContent>

        </ModalContainer>
    </ModalBackground>
    
    );
}

const StyledNav = styled.nav`
    margin-top: 25px;
    a {
        text-decoration: none;
        color: #8E8E93;
        font-size: 0.9em;
        margin: 0px 10px;
    }
`
const StyledSocialBtn = styled.a`
    margin: 0px 5px;
`
const StyledSeparator = styled.p`
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
    height: 120px;
    line-height: 120px;
    font-size: 2.0em;
    font-weight: bold;
    margin-bottom: 10px;
`
export default LoginModal