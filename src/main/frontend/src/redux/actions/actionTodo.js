import { get } from '../../util/http'

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
  return (dispatch) => {
    dispatch({
      type: 'ADD_TODO',
      payload: newTodo,
    })
  }
}

export const removeTodo = (id) => {
  return (dispatch) => {
    dispatch({
      type: 'REMOVE_TODO',
      payload: id,
    })
  }
}
