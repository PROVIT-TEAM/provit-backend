import { atom } from 'recoil';

//이메일로 가입
export const signUpInfoState = atom({
    key: 'signUpInfoState',
    default: {
        email: '',
        password: '',
        name: '',
        year: '',
        month: '',
        day: '',
        marketing: '',
    },
});

//이메일 로그인
export const loginInput = atom({
    key: 'loginInputState',
    default: {
        email: '',
        password: '',
    }
})