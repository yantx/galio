global.window = this
import { resolveToken } from '../util'
import { rsaEncrypt, rsaDencrypt, aesDecrypt, BcryptjsHash, BcryptjsCompare } from '../../src/utils/crypto'

const token = {
  admin: 'admin',
  editor: 'editor',
}
const memberPass = {
  admin: '123456',
  editor: '123456',
}

function loginAuth(username, password, key) {
  let isMember = ['admin', 'editor'].includes(username)
  if (isMember) {
    // 获取存储的用户密码 此处应是bcrypt加密后的数据
    let pass = BcryptjsHash(memberPass[username])
    // 使用RSA解密对称加密的密钥
    let aesKey = RSAdencrypt(key)
    // 使用aeskey解密password 获取对称加密后的password 与 pass做对比
    let pas = AESdecrypt(password, aesKey, aesKey)
    if (BcryptjsCompare(pas, pass)) {
      return {
        code: 20000,
        data: {
          token: token[username],
        },
      }
    } else {
      return {
        code: 50001,
        msg: '请确认账号密码后再次重试',
      }
    }
  } else {
    return {
      code: 50001,
      msg: '请确认账号后再次重试',
    }
  }
}
export default [
  {
    url: '/mock/auth/login',
    method: 'post',
    response: ({ body }) => {
      let result = loginAuth(body.username, body.password, body.securityKey)
      return result
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
  {
    url: '/mock/auth/getPublicKey',
    method: 'get',
    response: ({ headers }) => {
      return {
        code: 20000,
        data: `MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOwaw9yr1ycdC+dxAQx99sdFWo
        YMGlmY3dEYHVtHWGnBv0YYgGLHB0FCTLPcQKOvEx1WrQjWQlEaFYoUAYlkwbXoCa
        /SZlyTwTTR1OC6tEZXSiu9qXA0BzthXCXv8qUIiulAH8BMwTxKEGepimMoDbMiDQ
        2AYSdLT/oB/i3mmgPQIDAQAB`,
      }
    },
  },
]
