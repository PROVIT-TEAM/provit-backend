import styled from "styled-components";
import { modalDimmer, modalBody, modalTitle, modalContents, modalFooter, modalBtn, modalHeader, modalCloseBtn, centerDiv } from "./common";

export const StyledModalDimmer = styled.div`
  ${modalDimmer}
`;
export const StyledModalBody = styled.div`
  ${modalBody}
`;
export const StyledModalTitle = styled.div`
  ${modalTitle}
`;
export const StyledModalContents = styled.div`
  ${modalContents}
`;
export const StyledModalFooter = styled.div`
  ${modalFooter}
`;
export const StyledModalButton = styled.button`
  ${modalBtn};
  height: 52px;
  :hover {
    opacity: 50%;
    transition: 0.5s;
  }
`;
export const StyledModalHeader = styled.div`
  ${modalHeader};
  height: 60px;
`
export const StyledModalCloseButton = styled.button`
  ${modalCloseBtn};
  width: 52px;
  height: 32px;
`;

export const StyledCenterDiv = styled.div`
  ${centerDiv}
`
export const SteyledlBtnWithBorder = styled(StyledModalButton)`
  border-right: 1px solid #cbcbcb;
`;
