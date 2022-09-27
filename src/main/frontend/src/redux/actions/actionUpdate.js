export const setUpdateTodo = (todo) => {
    return (dispatch) => {
        dispatch({
            type: "SETUP_UPDATE_TODO",
            payload: todo
        })
    }
}

export const saveUpdateTodo = (updatedTodo) => {
    return (dispatch) => {
        dispatch({
            type: "SAVE_UPDATE_TODO",
            payload: updatedTodo
        })
    }
}