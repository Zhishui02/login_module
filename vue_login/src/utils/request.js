import axios from 'axios';
import router from "@/router";

//创建一个axios实例对象
//存放实例的默认设置
const request = axios.create({
    baseURL: "http://localhost:9090", //默认接口路径
    timeout: 10000, //超时时间10s
})

//使用axios拦截器
//request 拦截器
//请求发送前，可以进行预处理
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=UTF-8';
    let user = JSON.parse(localStorage.getItem("userDate") || '{}');
    config.headers.token = user.token; //设置请求头 token的信息
    return config;
},error => {
    console.log("请求错误" + error.message);
    return Promise.reject(error);
})

//response 拦截器
//返回结果前，可以进行预处理
request.interceptors.response.use(response => {
    let res = response.data;

    //兼容服务端返回的字符串数据
    if (typeof res === 'string') {
        res = res ? JSON.parse(res) : res;
    }
    if (res.code === '401') {
        router.push('/login')
    }
    return res;

},error => {
    console.log("返回结果失败" + error.message);
    return Promise.reject(error);
})

//暴露request
export default request;