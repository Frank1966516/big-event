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

// 获取文章列表
export const getArticleListService = (params) => {
    return request.get('/article', { params });
}

// 添加文章
export const addArticleService = (params) => {
    return request.post('/article', params);
}

// 修改文章
export const updateArticleService = (params) => {
    return request.put(`/article`, params);
}

// 删除文章
export const deleteArticleService = (id) => {
    return request.delete('/article?id='+id);
}