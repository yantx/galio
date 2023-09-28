import { createProdMockServer } from 'vite-plugin-mock/es/createProdMockServer'
import api from './modules'

export function setupProdMockServer() {
  createProdMockServer(api)
}
