import { mockRequest } from '@/utils'

export default {
  getPosts: (params = {}) => mockRequest.get('posts', { params }),
  getPostById: (id) => mockRequest.get(`/post/${id}`),
  addPost: (data) => mockRequest.post('/post', data),
  updatePost: (data) => mockRequest.put(`/post/${data.id}`, data),
  deletePost: (id) => mockRequest.delete(`/post/${id}`),
}
