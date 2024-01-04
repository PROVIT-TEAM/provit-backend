import React, { useState } from 'react'
import useModal from '../../hook/modal';
import { ModalBackground, ModalContainer, ModalHeader, ModalTitle, CloseButton, ModalContent, StyledLabel} from '../../styles/modal';
import { CommonInput } from '../../styles/input/commonInput';
import { CommonBtn } from '../../styles/button/commonBtn';
import styled from 'styled-components';
import DatePicker from '../DatePicker';
import { useRecoilState } from 'recoil';
import { loginInput, signUpInfoState } from '../../recoil/atom';
import { emailAuthentication, emailSignUp } from '../../apis/api/UserApi';


const SignUpInfoModal = () => {
  //모달 닫기
  const {closeModal} = useModal();
  const handleBackroundClick = (e: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
      if (e.target === e.currentTarget){
          closeModal();
      }
    }
  // input value
  const [signUpInfo, setSignUpInfo] = useRecoilState(signUpInfoState);
  const InputHandler = (field: keyof typeof signUpInfo, value: string) => {
    setSignUpInfo((prev) => ({...prev, [field]: value}));
    checkPasswordConditions(value);
  }
  // 비밀번호 검증
  const [passwordLabel, setPasswordLabel] = useState({
    text: '비밀번호',
    color: '',
  });
  const checkPasswordConditions = (password: string) => {
    const minLength = 8;
    const hasNumber = /\d/.test(password);
    const hasLetter = /[a-zA-Z]/.test(password);

    if (password.length < minLength || !(hasNumber && hasLetter)) {
      setPasswordLabel({text: '비밀번호-영문, 숫자 조합 8자 이상 입력해주세요.', color: 'red'});
    } else {
      setPasswordLabel({text: passwordLabel.text, color: ''});
    }
  };
  // 입력값 확인용
  const logValues = () => {
    console.log(
      'SignUpInfo:',
      'Email:', signUpInfo.email,
      'Password:', signUpInfo.password,
      'Name:', signUpInfo.name,
      'Year:', signUpInfo.year,
      'Month:', signUpInfo.month,
      'Day:', signUpInfo.day,
      'Marketing', signUpInfo.marketing
    );
    //가입요청 api
    emailSignUp(signUpInfo)
  }
  //이메일 인증
  const sendEmail = () => {
    console.log(signUpInfo.email);
    emailAuthentication(signUpInfo.email);
  }

    return (
    <ModalBackground onClick={handleBackroundClick}>
        <ModalContainer height='550px'>

            <ModalHeader>
                <CloseButton onClick={closeModal}>&times;</CloseButton>
                <ModalTitle>회원가입</ModalTitle>
            </ModalHeader>

            <ModalContent>
              <StyledForm>

              <StyledLabel>이메일</StyledLabel>
              <CommonInput
                  width={'210px'} type="text" placeholder='이메일을 입력해주세요.'
                  value={signUpInfo.email} onChange={(e) => InputHandler('email', e.target.value)}
                />
              <CommonBtn
                width='80px' height='38px' margin='0px 0px 0px 10px'
                onClick={sendEmail}
              >인증받기</CommonBtn>

              <StyledLabel color={passwordLabel.color}>{passwordLabel.text}</StyledLabel>
              <CommonInput
                type="password"  placeholder='비밀번호를 입력해주세요.'
                value={signUpInfo.password} onChange={(e) => InputHandler('password', e.target.value)}
              />

              <StyledLabel>이름</StyledLabel>
              <CommonInput
                type="text" placeholder='홍길동'
                value={signUpInfo.name} onChange={(e) => InputHandler('name', e.target.value)}
              />

              <StyledLabel>생년월일</StyledLabel>
              <DatePicker
                values={{year: signUpInfo.year, month: signUpInfo.month, day: signUpInfo.day}}
                onChange={(field, value) => InputHandler(field, value)}/>
              
              <StyledDiv>
              <StyledCheckboxContainer>
                  <StyledCheckBox type="checkbox" value={signUpInfo.marketing} onChange={(e) => InputHandler('marketing', e.target.checked.toString())}/>
                  <StyledText>마케팅 정보 수신 및 선택적 개인정보 제공에 동의합니다.</StyledText>
              </StyledCheckboxContainer>

              </StyledDiv>
              
              <CommonBtn margin='40px 0px 0px 0px' onClick={logValues}>가입하기</CommonBtn>

              </StyledForm>
            </ModalContent>

        </ModalContainer>
    </ModalBackground>
    
    );
}

const StyledCheckBox = styled.input`
    width: 1.2rem;
    height: 1.0rem;
    border-radius: 50%;
    border: 2px solid #999;
    appearance: none;
    cursor: pointer;
    margin: 0px 7px 0px 15px;

    &:checked::before {
        content: '✔';
        color: white;
        position: relative;
        top: -4px;
        left: 1px;
    }
`;
const StyledText = styled.span`
    position: relative;
    top: -1px;
    font-size: 12px;
`;
const StyledCheckboxContainer = styled.div`
    display: flex;
    text-align: left;
    /* align-items: center; */
    margin-left: 7px;
    width: 300px;
`;
const StyledDiv = styled.div`
  margin-top: 30px;
`
const StyledForm = styled.div`
  margin-top: 20px;
`
export default SignUpInfoModal