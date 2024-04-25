import { resolveToken } from '../util'

const users = {
  admin: {
    id: 1,
    username: 'admin',
    avatar: '640.jpg',
    email: 'Ronnie@123.com',
    rolePerms: ['admin'],
    functionPerms: [
      'system.member.add',
      'system.member.view',
      'system.member.edit',
      'demo.crud.add',
      'demo.crud.view',
      'demo.crud.edit',
      'demo.crud.delete',
    ],
  },
  editor: {
    id: 2,
    username: 'editor',
    avatar: '640-1.jpg',
    email: 'Ronnie@123.com',
    rolePerms: ['editor'],
    functionPerms: [
      'system.member.add',
      'system.member.view',
      'system.member.edit',
      'demo.crud.view',
      'demo.crud.edit',
    ],
  },
  guest: {
    id: 3,
    username: 'guest',
    avatar: '640-2.jpg',
    email: 'guest@123.com',
    rolePerms: [],
    functionPerms: ['demo.crud.add', 'system.member.view', 'system.member.edit'],
  },
}
export default [
  {
    url: '/mock/system/member/personal/info',
    method: 'get',
    response: ({ headers }) => {
      const token = resolveToken(headers?.authorization)
      return {
        code: 20000,
        data: {
          ...(users[token] || users.guest),
        },
      }
    },
  },
]
