<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref, watch } from 'vue'
import { getCategoryListService, addCategoryService} from '@/api/article.js'
import { ElMessage } from 'element-plus'

// 弹窗
// 控制添加分类弹窗
const dialogVisible = ref(false)

// 添加分类数据模型
const categoryModel = ref({
    categoryName: '',
    categoryAlias: ''
})
// 添加分类表单校验
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' },
    ]
}

// 文章分类
const categorys = ref([])

// 文章分类列表查询
const getCategoryList = async () => {
    const { data } = await getCategoryListService()
    categorys.value = data
}
getCategoryList()

// 添加文章分类
const addCategory = async () => {
    let res = await addCategoryService(categoryModel.value)
    ElMessage.success(result.message? result.message:'添加成功')
    //隐藏弹窗
    dialogVisible.value = false
    //再次访问后台接口，查询所有分类
    getAllCategory()
}


// 监视弹窗
watch(dialogVisible, () => {
    // 清空模型数据
    categoryModel.value = {
        categoryName: '',
        categoryAlias: ''
    }
})
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible = true">添加分类</el-button>
                </div>
            </div>
        </template>

        <!-- 文章表单 -->
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" ></el-button>
                    <el-button :icon="Delete" circle plain type="danger"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" title="添加弹层" width="30%" :visible="dialogVisible">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="分类名称" prop="categoryName">
                    <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="分类别名" prop="categoryAlias">
                    <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="addCategory"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>