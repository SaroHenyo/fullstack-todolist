const initialState = []

const todoReducer = (state = initialState, action) => {
  switch (action.type) {
    case 'SET_TODOLIST':
      return (state = action.payload)
    case 'SAVE_UPDATE_TODO':
      return (state = action.payload)
    default:
      return state
  }
}

export default todoReducer
