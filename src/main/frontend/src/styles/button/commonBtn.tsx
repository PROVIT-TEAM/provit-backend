import styled from "styled-components";

interface StyledBtnProps {
    width?: string;
    height?: string;
    margin?: string;
}
const CommonBtn = styled.button<StyledBtnProps>`
    width: ${(props) => props.width || '300px'};
    height: ${(props) => props.height || '45px'};
    background-color: #446FF6;    
    border-radius: 6px;
    cursor: pointer;
    color: white;
    vertical-align: -0.5px;
    /* ${(props) => (props.margin ? `margin: ${props.margin};` : '')}; */
    margin: ${(props) => props.margin || '0px'};
`

export {CommonBtn};