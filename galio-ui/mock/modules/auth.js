import { resolveToken } from '../util'

const token = {
  admin: 'admin',
  editor: 'editor',
}

export default [
  {
    url: '/mock/auth/login',
    method: 'post',
    response: ({ body }) => {
      if (['admin', 'editor'].includes(body?.name)) {
        return {
          code: 20000,
          data: {
            token: token[body.name],
          },
        }
      } else {
        return {
          code: 50001,
          message: '没有此用户',
        }
      }
    },
  },
  {
    url: '/mock/auth/refreshToken',
    method: 'post',
    response: ({ headers }) => {
      return {
        code: 20000,
        data: {
          token: resolveToken(headers?.authorization),
        },
      }
    },
  },
]
