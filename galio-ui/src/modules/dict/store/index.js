import { defineStore } from 'pinia'
import { getDictData } from '@/modules/dict/api'
export const useDictStore = defineStore('dict', {
  state() {
    return {
      dictData: [],
    }
  },
  getters: {
    getDictItems: (state) => (dictKey) => {
      return state.dictData.filter((item) => item.dictCode === dictKey)
    },
    getDictLabel: (state) => (dictKey, dictItemValue) => {
      const dictItem = state.dictData.find((item) => item.dictCode === dictKey && item.value === dictItemValue)
      return dictItem ? dictItem.label : ''
    },
  },
  actions: {
    async getDictData() {
      try {
        const res = await getDictData()
        if (res.code === 20000) {
          return Promise.resolve(res.data)
        } else {
          $message.error('getDictData: ' + res.msg)
          return Promise.reject(res)
        }
      } catch (error) {
        return Promise.reject(error)
      }
    },
    loadDict() {
      this.getDictData().then((data) => {
        this.setDict(data)
      })
    },
    reloadDict() {
      this.clearDict()
      this.getDictData().then((data) => {
        this.setDict(data)
      })
    },
    setDict(dictData) {
      this.dictData = dictData
    },
    clearDict() {
      this.dictData = {}
    },
  },
})
