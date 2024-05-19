import requset from '@/utils/request'

// 用户注册
export const UserRegisterService = (data) => {
    // 将data转成form表格格式
    const params = new URLSearchParams()
    for(let key in data){
        params.append(key, data[key])
    }
    return requset.post('/user/register', params);
}