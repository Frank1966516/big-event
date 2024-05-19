import { createRouter, createWebHistory} from "vue-router";

import Layout from "@/views/Layout.vue";
import Login from "@/views/Login.vue";

// 定义路由关系
const routes = [
    {
        path: "/",
        component: Layout,
        children: [
            
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