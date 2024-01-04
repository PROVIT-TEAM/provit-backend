import styled from "styled-components";

const ModalBackground = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
`;

interface ModalContainerProps {
    width?: string;
    height?: string;
}
const ModalContainer = styled.div<ModalContainerProps>`
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: ${(props) => props.width || '360px'};
    height: ${(props) => props.height || '580px'};
    background-color: #2C2C2E;
    color: white;
    z-index: 1;
    border-radius: 12px;
`;
const ModalHeader = styled.div`
    /* background-color: #5ecfe5; */
    height: 8%;
    display: flex;
    align-items: center;
    justify-content: center;
`
const ModalTitle = styled.span`
    font-size: 16px;
    position: absolute;
    top: 20px;
`
const ModalContent = styled.div`
    justify-content: center;
    text-align: center;
    padding: 10px;
    height: 90%;
`;
const CloseButton = styled.span`
    position: absolute;
    top: 10px;
    right: 15px;
    font-size: 25px;
    cursor: pointer;
`;

interface ErrorLabel {
    color?: string;
}
const StyledLabel = styled.label<ErrorLabel>`
    display: inline-block;
    text-align: left;
    width: 300px;
    margin: 10px 0px 5px 5px;
    color: ${(props) => props.color || '#8E8E93'};
    font-weight: bold;
    font-size: 0.8em;
`


export {ModalBackground, ModalContainer, ModalHeader, ModalTitle, ModalContent, CloseButton, StyledLabel};