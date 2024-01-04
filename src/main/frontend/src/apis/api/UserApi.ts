import axios from "axios";
import { atom, useRecoilState } from "recoil";
import {instance, authInstance} from "../utils/instance";

export interface UserInfo {
    email: string,
    password: string,
    name: string,
    year: string,
    month: string,
    day: string,
    marketing: string,
}
export interface LoginInfo{
    email: string,
    password: string,
}


//이메일 회원가입
export async function emailSignUp(info: UserInfo) {
    const request = await instance.post('/user/regist',
    {
        email: info.email,
        password: info.password,
        name: info.name,
        year: info.year,
        month: info.month,
        day: info.day,
        marketing: info.marketing
    })
    .then((response) => {
        console.log(response.data);
        sessionStorage.setItem("userState", JSON.stringify(response.data))
    });
}
//이메일 로그인
export const emailLogin = async (info: LoginInfo) => {
    const request = await instance.post('/user/login',
    {
        email: info.email,
        password: info.password
    })
    .then((response)=>{
        console.log(response.data)
        sessionStorage.setItem("userState", JSON.stringify(response.data))
    })
}
//이메일 인증번호
export const emailAuthentication = async (email: string) => {
    const request = await instance.get('/user/sendEmailTest',{
        params: {
            email: email,
        },
    });
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
    .then(response => {
        if (response.status == 200) {
            console.log(response.data);
        }
        else{
            console.log("failed");
        }
        
    })
}

export async function reload(){
    await instance.get('/oauth2/reload')
    .then(res => {
        console.log("success");
    })
}
