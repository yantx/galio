const dictItems = [
  {
    orderNum: 0,
    label: '男',
    value: '1',
    cssClass: '',
    listClass: '',
    isDefault: '0',
    dictCode: 'sex_dict',
  },
  {
    orderNum: 0,
    label: '女',
    value: '2',
    cssClass: '',
    listClass: '',
    isDefault: '0',
    dictCode: 'sex_dict',
  },
]

export default [
  {
    url: '/mock/system/dict/list',
    method: 'post',
    response: ({ headers }) => {
      return {
        code: 20000,
        data: dictItems,
      }
    },
  },
]
