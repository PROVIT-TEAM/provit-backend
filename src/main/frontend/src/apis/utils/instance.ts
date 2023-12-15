import axios from "axios";

const instance = axios.create({
    // baseURL: "http://localhost:9090"
    // 위처럼 직접 지정하니까 package.json에 proxy 설정한게 안먹힘
    // cors 에러
})

const authInstance = axios.create({
    // baseURL: "http://localhost:9090",
    headers: {'X-AUTH-TOKEN': 'token값'}
})

export {instance, authInstance};