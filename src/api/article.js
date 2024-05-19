import request from '@/utils/request'

// 获取文章分类列表
export const getCategoryListService = () => {
    return request.get('/category');
}

// 添加文章分类
export const addCategoryService = (params) => {
    return request.post('/category', params);
}

// 修改文章分类
export const updateCategoryService = (params) => {
    return request.put(`/category`, params);
}

// 删除文章分类
export const deleteCategoryService = (id) => {
    return request.delete('/category?id='+id);
}