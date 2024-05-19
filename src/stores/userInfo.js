import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserInfoStore = defineStore("userInfo", () => {
    // 用户信息
    const userInfo = ref({});
    // 设置用户信息
    const setUserInfo = (info) => {
        userInfo.value = info;
    };
    // 删除用户信息
    const removeUserInfo = () => {
        userInfo.value = {};
    }
    return {
        userInfo,
        setUserInfo,
        removeUserInfo
    }
},
{
    persist: true
})