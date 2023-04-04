import { resultSuccess, doCustomTimes } from '../_util';

const tableList = (pageSize) => {
  const result: any[] = [];
  doCustomTimes(pageSize, () => {
    result.push({
      tableName: '@cname()',
      tableComment: '@city()',
      createTime: '@datetime',
      updateTime: '@datetime',
    });
  });
  return result;
};

export default [
  //表格数据列表
  {
    url: '/api/gen/list',
    timeout: 1000,
    method: 'get',
    response: ({ query }) => {
      const { page = 1, pageSize = 10 } = query;
      const list = tableList(Number(pageSize));
      return resultSuccess({
        page: Number(page),
        pageSize: Number(pageSize),
        pageCount: 60,
        list,
      });
    },
  },
];
