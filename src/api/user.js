import request from '@/utils/request'

// 用户注册
export const UserRegisterService = (data) => {
    // 将data转成form表格格式
    const params = new URLSearchParams()
    for(let key in data){
        params.append(key, data[key])
    }
    return request.post('/user/register', params);
}

// 用户登录
export const UserLoginService = (data) => {
    // 将data转成form表格格式
    const params = new URLSearchParams()
    for(let key in data){
        params.append(key, data[key])
    }
    return request.post('/user/login', params);
}

// 获取用户信息
export const UserInfoService = () => {
    return request.get('/user/userInfo');
}

// 修改用户信息
export const UserUpdateService = (params) => {
    return request.put('/user/update', params);
}

// 修改用户头像
export const UserUpdateAvatarService = (param) => {
    let params = new URLSearchParams();
    params.append('avatarUrl',param)
    return request.patch('/user/updateAvatar', params);
}