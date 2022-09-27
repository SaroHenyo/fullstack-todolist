import React from 'react'
import TodoBody from './TodoBody'
import TodoHeader from './TodoHeader'

export default function TodoList() {
  return (
    <div className="todo-body">
      <h1>Task List 2022</h1>
      <TodoHeader />
      <br />
      <hr />
      <br />
      <br />
      <TodoBody />
      <br />
      <br />
    </div>
  )
}
