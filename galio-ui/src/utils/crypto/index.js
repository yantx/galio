// 引入JSEncrypt文件
import JSEncrypt from 'jsencrypt-ext'
import CryptoJS from 'crypto-js'
import Bcryptjs from 'bcryptjs'

//公钥
let defaultPublicKey = `MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOwaw9yr1ycdC+dxAQx99sdFWo
YMGlmY3dEYHVtHWGnBv0YYgGLHB0FCTLPcQKOvEx1WrQjWQlEaFYoUAYlkwbXoCa
/SZlyTwTTR1OC6tEZXSiu9qXA0BzthXCXv8qUIiulAH8BMwTxKEGepimMoDbMiDQ
2AYSdLT/oB/i3mmgPQIDAQAB`

//私钥
let defaultPrivateKey = `
MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM7BrD3KvXJx0L53
EBDH32x0VahgwaWZjd0RgdW0dYacG/RhiAYscHQUJMs9xAo68THVatCNZCURoVih
QBiWTBtegJr9JmXJPBNNHU4Lq0RldKK72pcDQHO2FcJe/ypQiK6UAfwEzBPEoQZ6
mKYygNsyINDYBhJ0tP+gH+LeaaA9AgMBAAECgYBxdAEJ30hZlm7PNRJRwvuszaF7
Ray9LCeWWVb/Z4jeFqeYUDSN/rCuYadXB0uduX5EtDFqw0tis+vvcHx1gXO8y8uV
HPb+2oh8K5B6CzlCukGJUdLNqbFNfEatO4fHU6YEK2/a5ZCFtBOmqSIDVwz2zIEs
gmdx2hivoKm6aSioQQJBAPfeYSAU3+w6BWHfdujS9l+x2FMik+UySl8l37kxmDJZ
nWMtQlHLcXOOfCsxJ170Zq9lD2OuHYmohB/AzPiDz4kCQQDVigfNLcyj2aTgSdwB
5AIVnouvLZZIyeZT7ozwHUAk1lsr4E/oBrCp3++S+9R4V7iussAiJvqPM08zI5Dx
vEoVAkBKK85OhlCXZKlVp0Py2aYF7rb38aHl9M2SeUfgJ/oOHwjcs59j0IgLdziq
yBOq82GC3K/lOSX3mminA8+SEgdZAkEAvCxeo5hwdRMeD+gGUEQ08gg836o0fQAu
UFgvKokcNVoQDaIlyDndBFWqMMRqf4IN5STv2yiuM/LrIA1/dQaP4QJBAKBV88L+
pvchxjUz7tJ/XrnENsWjj9rfRfeyqxLKBWGE66MaGUPf7g95cF/ZZ8CFr5Sj8znM
jj7EU9yY0TUQogo=
`

/**
 * 加密方法
 * @param  pas 源内容
 * @param  publicKey 公钥
 * @returns 密文
 */
export function rsaEncrypt(pas, publicKey = defaultPublicKey) {
  // 实例化jsEncrypt对象
  let encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey)

  return encryptor.encrypt(pas)
}

/**
 * 解密方法
 * @param securityData 密文
 * @param privateKey 私钥
 * @returns 解密结果
 */
export function rsaDencrypt(securityData, privateKey = defaultPrivateKey) {
  //实例化jsEncrypt对象
  let decrypt = new JSEncrypt()

  //设置私钥
  decrypt.setPrivateKey(privateKey)

  return decrypt.decrypt(pas)
}

const defaultAESkey = '1234123412ABCDEF' //十六位十六进制数作为密钥
const defaultAESiv = 'ABCDEF1234123412' //十六位十六进制数作为密钥偏移量

/**
 * iv 是密钥偏移量，这个一般是接口返回的，或者前后端协定一致。
 *由于对称解密使用的算法是 AES-128-CBC算法，数据采用 PKCS#7 填充 ， 因此这里的 key 需要为16位
 */

//解密方法
export function aesDecrypt(word, key = defaultAESkey, iv = defaultAESiv) {
  key = CryptoJS.enc.Utf8.parse(key)
  iv = CryptoJS.enc.Utf8.parse(iv)
  let encryptedHexStr = CryptoJS.enc.Hex.parse(word)
  let srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr)
  let decrypt = CryptoJS.AES.decrypt(srcs, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 })
  let decryptedStr = decrypt.toString(CryptoJS.enc.Utf8)
  return decryptedStr.toString()
}

//加密方法
export function aesEncrypt(word, key = defaultAESkey, iv = defaultAESiv) {
  key = CryptoJS.enc.Utf8.parse(key)
  iv = CryptoJS.enc.Utf8.parse(iv)
  let srcs = CryptoJS.enc.Utf8.parse(word)
  let encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 })
  return encrypted.ciphertext.toString().toUpperCase()
}
/**
 * 加密处理 - 同步方法
 * bcryptjs.hashSync(data, salt)
 *    - data  要加密的数据
 *    - slat  用于哈希密码的盐。如果指定为数字，则将使用指定的轮数生成盐并将其使用。推荐 10
 */
export function BcryptjsHash(data, salt = 10) {
  const hashData = Bcryptjs.hashSync(data, salt)
  return hashData
}

/**
 * 校验 - 使用同步方法
 * bcryptjs.compareSync(data, encrypted)
 *    - data        要比较的数据, 使用登录时传递过来的密码
 *    - encrypted   要比较的数据, 使用从数据库中查询出来的加密过的密码
 */
export function BcryptjsCompare(data, encrypted) {
  const isOk = Bcryptjs.compareSync(data, encrypted)
  return isOk
}
