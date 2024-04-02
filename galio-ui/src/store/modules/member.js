import { defineStore } from 'pinia'
import { getMember } from '~/src/modules/member/api'
import { removeToken, toLogin } from '@/utils'
import { resetRouter } from '@/router'
import { useTagsStore } from '@/store'
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
      return this.memberInfo?.username
    },
    avatar() {
      return this.memberInfo?.avatar
    },
    role() {
      return this.memberInfo?.rolePerms || []
    },
    isAdmin() {
      return this.memberInfo?.isAdmin
    },
    isSuperAdmin() {
      return this.memberInfo?.isSuperAdmin
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
          const { id, username, avatar, rolePerms, isSuperAdmin, isAdmin, functionPerms } = res.data
          this.memberInfo = { id, username, avatar, rolePerms, functionPerms }
          return Promise.resolve(res.data)
        } else {
          $message.error('getMemberInfo: ' + res.msg)
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
