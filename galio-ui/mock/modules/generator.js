import { resolveToken } from '../util'

const databases = [{ database: 'galio' }]
const databaseList = {
  url: '/mock/database',
  method: 'get',
  response: ({ headers }) => {
    return {
      code: 20000,
      data: databases,
    }
  },
}

const tables = [
  { tableName: 'gen_table', tableComment: '代码生成表' },
  { tableName: 't_member', tableComment: '会员信息表' },
  { tableName: 't_role', tableComment: '角色表' },
]
const tableList = {
  url: '/mock/tables/:databaseName',
  method: 'get',
  response: ({ query }) => {
    const databaseName = query.databaseName
    return {
      code: 20000,
      data: tables,
    }
  },
}
export default [databaseList, tableList]
