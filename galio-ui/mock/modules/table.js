import { Random } from 'mockjs'

const tableList = (pageSize) => {
  const result = []
  doCustomTimes(pageSize, () => {
    result.push({
      id: '@increment',
      createDate: '@datetime',
      updateDate: '@datetime',
      category: '@city()',
      title: '@ctitle()',
      author: '@cname()',
      avatar: Random.image('400x400', Random.color(), Random.color(), Random.first()),
      date: `@date('yyyy-MM-dd')`,
      time: `@time('HH:mm')`,
      content: '@cparagraph',
      'isPublish|1': [true, false],
    })
  })
  return result
}
function doCustomTimes(times, callback) {
  let i = -1
  while (++i < times) {
    callback(i)
  }
}
function resultSuccess(data, { msg = 'ok' } = {}) {
  return {
    code: 20000,
    data,
    msg,
  }
}
export default [
  //表格数据列表
  {
    url: '/mock/tbale/list',
    timeout: 1000,
    method: 'get',
    response: ({ query }) => {
      const { pageNumber = 1, pageSize = 10 } = query
      const list = tableList(Number(pageSize))
      return resultSuccess({
        pageNumber: Number(pageNumber),
        pageSize: Number(pageSize),
        total: 66,
        pageData: list,
      })
    },
  },
]
