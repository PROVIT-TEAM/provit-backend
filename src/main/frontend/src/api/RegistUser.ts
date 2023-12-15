import axios from "axios";
import { atom } from "recoil";


export interface UserInfo {
    email: string;
    name: string;
    userId: string;
    password: string;
    birth: string;
    marketing: boolean;
}
export interface LoginInfo{
    username: string;
    password: string;
}
export const loginInput = atom<LoginInfo>({
    key: 'loginInputState',
    default: {
        username: '',
        password: '',
    }
})

export const inputState = atom<UserInfo>({
    key: 'inputState',
    default: {
        email: 'string',
        name: 'string',
        userId: 'string',
        password: 'string',
        birth: 'string',
        marketing: false,
    }
})

export async function postTest(info: UserInfo){
    try{
        await axios.post('/user/regist',{
            email: info.email,
            name: info.name,
            userId: info.userId,
            password: info.password,
            birth: info.birth,
            marketing: info.marketing,
        });
    } catch (e) {
        console.log(e);
    }
}

export async function loginTest(info: LoginInfo){
    try{
        await axios.post('/user/login',
        {
            username: info.username,
            password: info.password,
        })
        .then((response)=>{
            console.log(response.data)
            return response.data;
        });
    } catch (e) {
        console.log(e);
    }
}

//똑바로 헤더 설정이 안되는거 같음, 서버쪽으로 넘어오지도 않음
//spring 필터에서 이미 걸리는듯
//instance로 해결 완료!
export const instance = axios.create({
    headers: {'X-AUTH-TOKEN': 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6ZXplMDhAZ21haWwuY29tIiwiaWF0IjoxNzAyNjY3MDc5LCJleHAiOjE3MDI2Njc5Nzl9.wo5jAK-7nqZTqnCeMkXC6PxC2C0I-UXmCB__ORKTrrQ'}
})
export async function tokenTest(){
    try{
        await instance.post('/user/authTest')
        .then((response)=>{
            console.log(response.data);
        });
    } catch (e) {
        console.log(e);
    }
}