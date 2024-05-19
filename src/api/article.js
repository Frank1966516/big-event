import request from '@/utils/request'

// 获取文章分类列表
export const getCategoryListService = () => {
    return request.get('/category');
}

// 添加文章分类
export const addCategoryService = (data) => {
    return request.post('/category', data);
}