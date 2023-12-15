import styled from "styled-components";

export const StyledDetailBtn = styled.button`
  border: none;
  background: none;
`
export const StyledSubmitBtn = styled.button`
    border: none;
  color: white;
  background-color: blue;
  width: 196px;
  height: 36px;
  margin-top: 15px;
  font-weight: bold;
`
export const StyledCommonInputBox = styled.input`
  width: 190px;
  height: 30px;
  margin-top: 15px;
  ::placeholder{
    font-weight: bold;
  }
`
export const StyledNameAndBirth = styled.div`
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
export const StyledLabel = styled.div`
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