import { ElMessage } from 'element-plus';
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
        // alert('服务异常');
        ElMessage.error('服务异常');
        return Promise.reject(err);//异步的状态转化成失败的状态
    }
)

export default instance;