import { resolveToken } from '../util'

const users = {
  admin: {
    id: 1,
    username: 'admin',
    avatar: '640.jpg',
    email: 'Ronnie@123.com',
    rolePerms: ['admin'],
    functionPerms: ['system.member.add', 'system.member.view', 'system.member.edit'],
  },
  editor: {
    id: 2,
    username: 'editor',
    avatar: '640-1.jpg',
    email: 'Ronnie@123.com',
    rolePerms: ['editor'],
    functionPerms: ['system.member.add', 'system.member.view', 'system.member.edit'],
  },
  guest: {
    id: 3,
    username: 'guest',
    avatar: '640-2.jpg',
    rolePerms: [],
    functionPerms: ['system.member.add', 'system.member.view', 'system.member.edit'],
  },
}
export default [
  {
    url: '/mock/member',
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
