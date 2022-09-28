export const setUpdateTodo = (todo) => {
  return (dispatch) => {
    dispatch({
      type: 'SETUP_UPDATE_TODO',
      payload: todo,
    })
  }
}
