import { request } from '@/utils'

export default {
  /** 查询表数据列表 */
  getTableList: (params = {}) => request.post('/tbale/list', { params }),
  /** 查询字段数据列表 */
  getColumnList: (params = {}) => request.post('/column/list', { params }),
  /** 获取表信息 */
  getTableInfo: (tableId) => request.get(`/tbale/${tableId}`),
  /** 预览代码 */
  preview: (tableId) => request.get(`/preview/${tableId}`),
  /** 下载代码 */
  download: (tableName) => request.get(`/download/${tableName}`),
  /** 生成代码（自定义路径） */
  genCode: (tableName) => request.get(`/genCode/${tableName}`),
  /** 同步数据库 */
  synchDb: (tableName) => request.get(`/synchDb/${tableName}`),
  /** 批量生成代码 */
  batchGenCode: (tableNames) => request.get(`/batchGenCode/${tableNames}`),
  /** 导入表 */
  importTable: (data) => request.post('/post', data),
  /** 修改数据表关联的代码生成属性 */
  update: (data) => request.put(`/`, data),
  /** 删除数据表 */
  delete: (tableIds) => request.delete(`/`, { tableIds }),
  /** 获取数据库列表 */
  getDatabases: (params = {}) => request.post('/db/list', { params }),
  getTables: (databaseName) => request.get(`/tables/${databaseName}`),
}
