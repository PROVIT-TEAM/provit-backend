import styled from "styled-components"
import {centerDiv, modalHeader, modalCloseBtn } from "./common"

export const ModalHeader = styled.div`
  ${modalHeader}
`
export const CloseButton = styled.button`
  ${modalCloseBtn};
  width: 52px;
  height: 32px;
`
export const StyledInputBox = styled.input`
  width: 190px;
  height: 30px;
  margin-top: 15px;
  ::placeholder{
  font-weight: bold;}
`
export const CenterDiv = styled.div`
  ${centerDiv}
`

export const StyledSignUp = styled.button`
    background: none;
    border: none;
    font-size: 15px;
    font-weight: bold;
    color: blue;
`
export const FindPwdButton = styled.button`
  margin-left: 8em;
  border: none;
  background: none;
`
export const LoginButton = styled.button`
  border: none;
  color: white;
  background-color: blue;
  width: 196px;
  height: 36px;
  margin-top: 15px;
  font-weight: bold;
`

