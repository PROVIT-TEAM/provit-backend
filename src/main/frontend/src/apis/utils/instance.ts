import axios from "axios";

const instance = axios.create({
    // baseURL: "http://localhost:9090"
    // 위처럼 직접 지정하니까 package.json에 proxy 설정한게 안먹힘
    headers: {
        "Access-Control-Allow-Origin": "http://localhost:3000",
        "Access-Control-Allow-Credentials": "true"
    }
    
})

const authInstance = axios.create({
    // baseURL: "http://localhost:9090",
    headers: {'X-AUTH-TOKEN': '토큰'}
})


export {instance, authInstance};