import { defineStore } from 'pinia'
import { getDictData } from '@/modules/dict/api'
export const useDictStore = defineStore('dict', {
  state() {
    return {
      dictData: [],
    }
  },
  getters: {
    /**
     * Get dictionary item by dictCode
     * @param {string} dictCode dictionary code
     * @return {object} dictionary item, return {orderNum : 0, label : 'test', value : 'test', cssClass : 'test', listClass : 'test', isDefault: true }
     */
    getDictItems(dictKey) {
      // Get dictionary item by dictKey
      return this.dictData.filter((item) => item.dictCode === dictKey)
    },
    getDictLabel(dictKey, dictItemValue) {
      // Get dictionary item label by dictKey and dictItemValue
      return getDictItems(dictKey).find((item) => item.value === dictItemValue).label
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
