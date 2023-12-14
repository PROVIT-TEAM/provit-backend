import { atom } from "recoil";

interface ModalState {
    isOpen: boolean;
    content: React.ReactNode | JSX.Element;
}
export const modalState = atom<ModalState>({
    key: 'modalState',
    default: {
        isOpen: false,
        content: null,
    }
})

export const loginModalState = atom({
    key: 'loginModalState',
    default:{
        isOpen: false,
    }
})

export const signUpModalState = atom({
    key: 'signUpModalState',
    default:{
        isOpen: false,
    }
})

export const signUpEmailModalState = atom({
    key: 'signUpEmailState',
    default:{
        isOpen: false,
    }
})

export const confirmEmailModalState = atom({
    key: 'confirmEmailState',
    default:{
        isOpen: false,
    }
})