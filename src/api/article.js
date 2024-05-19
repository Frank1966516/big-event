import request from '@/utils/request'

// 获取文章分类列表
export const getCategoryListService = () => {
    return request.get('/category');
}