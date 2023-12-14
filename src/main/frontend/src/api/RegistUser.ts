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
        await axios.post('/user/postRegist',{
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

// export async function registUser(info: UserInfo){
//   try{
//     const response = await axios.get(
//       '/user/regist', {
//         params: {
//           email: info.email,
//           name: info.name,
//           userId: info.userId,
//           password: info.password,
//           birth: info.birth,
//           marketing: info.marketing,
//         }
//       }
//     );
//   } catch (e) {
//     console.log(e);
//   }
// }