import { Link } from "react-router-dom";
import styled from "styled-components";

const StyledHeader = styled.div`
    display: flex;
    align-items: center;
    width: 100%;
    height: 10vh;
    background-color: #b9b7b7;
    padding: 0px 20px;

    font-size: 1.5em;
    font-weight: bold;
    
`;
const StyledLogo = styled.div`

`
const StyledNav = styled.div`
    margin-left: auto;
`;
const StyledNavLink = styled(Link)`
    margin-left: 15px;
    text-decoration: none;
    color: black;
`

const Header = () => {
  return (
    <StyledHeader>
        <StyledLogo>PROVIT</StyledLogo>
        <StyledNav>
            <StyledNavLink to={"/login"}>로그인</StyledNavLink>
            <StyledNavLink to={"/signup"}>회원가입</StyledNavLink>
        </StyledNav>
    </StyledHeader>
  );
};

export default Header;