<script setup>
import { ref } from 'vue'
import { UserUpdatePasswordService } from '@/api/user.js'
import { ElMessage } from 'element-plus'
import router from '@/router/index.js'

// 密码数据
const userData = ref({
    password: '',
    newPassword: '',
    rePassword: ''
})
// 表单验证规则
const rules = {
    password: [
        { required: true, message: '请输入原密码', trigger: 'blur' },
        {
            pattern: /^\S{5,16}$/,
            message: '密码必须是5-16位的非空字符串',
            trigger: 'blur'
        }
    ],
    newPassword: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
        {
            pattern: /^\S{5,16}$/,
            message: '密码必须是5-16位的非空字符串',
            trigger: 'blur'
        }
    ],
    rePassword: [
        { required: true, message: '请再次输入新密码', trigger: 'blur' },
        { validator: (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入新密码'))
            } else if (value !== userData.value.newPassword) {
                callback(new Error('两次输入的密码不一致'))
            } else {
                callback()
            }
        }, trigger: 'blur'
        }
    ]
}

// 更改用户密码
const updatePassword = async () => {
    let params = {
        old_pwd: userData.value.password,
        new_pwd: userData.value.newPassword,
        re_pwd: userData.value.rePassword
    }
    
    let res = await UserUpdatePasswordService(params)
    ElMessage.success(res.message?res.message:'密码修改成功')
    router.push('/login')
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>密码修改</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="userData" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="旧密码" prop="password">
                        <el-input type="password" v-model="userData.password"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="newPassword">
                        <el-input  type="password" v-model="userData.newPassword"></el-input>
                    </el-form-item>
                    <el-form-item label="再次输入" prop="rePassword">
                        <el-input  type="password" v-model="userData.rePassword"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="updatePassword">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>