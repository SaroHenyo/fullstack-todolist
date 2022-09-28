import { get, put, deleteMethod, post } from '../../util/http'

export const getAllTodo = () => {
  const url = '/todo/getAll'
  return new Promise((resolve, reject) => {
    const promise = get(url)
    promise
      .then((response) => {
        resolve({
          type: 'SET_TODOLIST',
          payload: response,
        })
      })
      .catch((error) => {
        reject(error)
      })
  })
}

export const addTodo = (newTodo) => {
  const url = `/todo/addTodo/${newTodo}`
  return new Promise((resolve, reject) => {
    const promise = put(url)
    promise.then((response) => {
      resolve({
        type: 'SET_TODOLIST',
        payload: response,
      })
    })
  })
}

export const removeTodo = (id) => {
  const url = `/todo/deleteTodo/${id}`
  return new Promise((resolve, reject) => {
    const promise = deleteMethod(url)
    promise.then((response) => {
      resolve({
        type: 'SET_TODOLIST',
        payload: response,
      })
    })
  })
}

export const updateTodo = (body) => {
  const url = `/todo/updateTodo`
  return new Promise((resolve, reject) => {
    const promise = post(url, body)
    promise
      .then((response) => {
        resolve({
          type: 'SAVE_UPDATE_TODO',
          payload: response,
        })
      })
      .catch((error) => {
        reject(error)
      })
  })
}
