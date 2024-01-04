import styled from "styled-components";

const CommonInput = styled.input`
  width: ${(props) => props.width || '300px'};
  height: ${(props) => props.height || '40px'};
  padding: 0px;
  margin: 0px 0px 10px 0px;
  background-color: #3A3A3C;
  border: none;
  border-radius: 6px;
  color: white;
  text-indent: 5%;
  &::placeholder {
    text-indent: 5%;
  }
  &:focus {
    outline: none;
  }
`

export {CommonInput};