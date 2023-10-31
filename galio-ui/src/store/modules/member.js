import { defineStore } from 'pinia'
import { getMember } from '~/src/modules/member/api'
import { removeToken, toLogin } from '@/utils'
import { resetRouter } from '@/router'
import { useTagsStore, useRouterStore } from '@/store'
// todo member切换成member 防止前后定义不一致； 完成menus整合；增设btn权限；
export const useMemberStore = defineStore('member', {
  state() {
    return {
      memberInfo: {},
    }
  },
  getters: {
    memberId() {
      return this.memberInfo?.id
    },
    name() {
      return this.memberInfo?.name
    },
    avatar() {
      return this.memberInfo?.avatar
    },
    role() {
      return this.memberInfo?.rolePerms || []
    },
    functionPerms() {
      return this.memberInfo?.functionPerms || []
    },
  },
  actions: {
    async getMemberInfo() {
      try {
        const res = await getMember()
        if (res.code === 20000) {
          const { id, username, avatar, rolePerms, functionPerms } = res.data
          this.memberInfo = { id, username, avatar, rolePerms, functionPerms }
          return Promise.resolve(res.data)
        } else {
          return Promise.reject(res)
        }
      } catch (error) {
        return Promise.reject(error)
      }
    },
    async logout() {
      const { resetTags } = useTagsStore()
      removeToken()
      resetTags()
      resetRouter()
      this.$reset()
      toLogin()
    },
    setMemberInfo(memberInfo = {}) {
      this.memberInfo = { ...this.memberInfo, ...memberInfo }
    },
  },
})
