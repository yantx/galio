import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/crud/posts', { params }),
  getPostById: (id) => request.get(`/post/${id}`),
  addPost: (data) => request.post('/post', data),
  updatePost: (data) => request.put(`/post/${data.id}`, data),
  deletePost: (id) => request.delete(`/post/${id}`),
  deletePosts: (ids) => request.delete(`/post/${ids}`),
}
