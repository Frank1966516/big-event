import { createRouter, createWebHistory} from "vue-router";

import Layout from "@/views/Layout.vue";
import Login from "@/views/Login.vue";
import ArticleCategory from "@/views/article/ArticleCategory.vue";
import ArticleManage from "@/views/article/ArticleManage.vue";
import UserAvatar from "@/views/user/UserAvatar.vue";
import UserInfo from "@/views/user/UserInfo.vue";
import UserResetPassword from "@/views/user/UserResetPassword.vue";

// 定义路由关系
const routes = [
    {
        path: "/",
        component: Layout,
        redirect: "/article/category",
        // 子路由
        children: [
            {
                path: "/article/category",
                component: ArticleCategory
            },
            {
                path: "/article/manage",
                component: ArticleManage
            },
            {
                path: "/user/avatar",
                component: UserAvatar
            },
            {
                path: "/user/info",
                component: UserInfo
            },
            {
                path: "/user/password",
                component: UserResetPassword
            }
        ]
    },
    {
        path: "/login",
        component: Login
    }
]

// 创建路由实例
const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;