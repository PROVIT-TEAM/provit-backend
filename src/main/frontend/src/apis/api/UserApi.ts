import axios from "axios";
import { atom } from "recoil";
import {instance, authInstance} from "../utils/instance";

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
export const signUptState = atom<UserInfo>({
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

export async function signUp(info: UserInfo){
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

export async function login(info: LoginInfo){
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
export const login2 = async (user: LoginInfo) => {
    const isUser = {username: user.username, password: user.password};
    
    const request = await instance.post('/user/login',isUser)
    .then((response)=>{
        console.log(response.data)
    })
}

export async function tokenTest(){
    try{
        await authInstance.post('/user/authTest')
        .then((response)=>{
            console.log(response.data);
        });
    } catch (e) {
        console.log(e);
    }
}

export async function naver(){
    const request = await instance.get('/oauth2/google')
    // .then((response)=>{
    //     console.log(response)
    // })
    
}

export async function naverCallback(uri: string){
    const request = await axios.get(uri)
    .then((response)=>{
        console.log("callback:", response.data)
    })
}