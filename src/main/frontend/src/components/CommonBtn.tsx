import styled from "styled-components";

const StyledButton = styled.button<styleProps>`
    width: ${(props) => props.$width};
    height: ${(props) => props.$height};
    color: ${(props) => props.color};
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1rem;
    background-color: ${(props) => props.$bgColor};
    padding-left: ${(props) => props.$paddingLeft};
    &:hover {
        transition: all 0.3s;
        background-color: ${(props) => props.$hoverColor};
    }
    margin-left: ${(props) => props.$marginLeft};
    margin-top: ${(props) => props.$marginTop};
`;

interface props {
    text?: string;
    onClick?: () => void;
}
interface styleProps {
    $bgColor?: string;
    color?: string;
    $paddingLeft?: string;
    $hoverColor?: string;
    $width?: string;
    $height?: string;
    $marginLeft?: string;
    $marginTop?: string;
}
export function ActiveButton({
    text,
    onClick,
    $bgColor,
    color,
    $paddingLeft,
    $hoverColor,
    $width = "100%",
    $height = "56px",
    $marginLeft,
    $marginTop,
    }: props & styleProps) {
    const handleClickAction = () => {
        if (onClick) onClick();
    };

    return (
        <StyledButton
            onClick={handleClickAction}
            $bgColor={$bgColor}
            color={color}
            $paddingLeft={$paddingLeft}
            $hoverColor={$hoverColor}
            $width={$width}
            $height={$height}
            $marginLeft={$marginLeft}
            $marginTop={$marginTop}
        >
            {text}
        </StyledButton>
        );
}