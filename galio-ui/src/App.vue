<template>
  <el-config-provider :locale="locale" name="el" :message="config">
		<router-view></router-view>
	</el-config-provider>
</template>

<script setup lang="ts">
import zhCn from 'element-plus/dist/locale/zh-cn';
import { reactive } from 'vue';

const locale = zhCn;

// 然后将element-ui中pagination选项改掉
zhCn.el.pagination = {
	pagesize: '条/页',
	total: `共 {total} 条`,
	goto: '前往第',
	pageClassifier: '页',
};
const config = reactive({
	max: 4,
});
</script>