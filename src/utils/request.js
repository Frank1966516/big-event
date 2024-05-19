import { ElMessage } from 'element-plus';
import { useTokenStore } from '@/stores/token';
import router from '@/router';


//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({baseURL})


//添加响应拦截器
instance.interceptors.response.use(
    result=>{
        // 判断业务状态码
        if(result.data.code === 0){
            return result.data;
        }
        // 业务状态码不等于0
        ElMessage({
            message: result.message? result.message:'操作失败',
            type: 'error',
        })
        // 如果结果不符合预期（result.data不合法），
        // 就抛出一个Promise reject错误，并返回错误信息。
        return Promise.reject(result.data);
    },
    err=>{
        if(err.response.status === 401){
            ElMessage({
                message: '未授权，请登录',
                type: 'error',
            })
            // 跳转到登录页面
            router.push('/login');
        }
        ElMessage.error('服务异常');
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

// 添加请求拦截器
instance.interceptors.request.use(
    config=>{
        // 添加请求头
        let tokenStore = useTokenStore();
        if(tokenStore.token)
            config.headers.Authorization = tokenStore.token;
        return config;
    },
    err=>{
        return Promise.reject(err);
    }
)

export default instance;