import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
// import App from './App'
import { Provider } from 'react-redux'
import { store } from './redux/store'
import ImageProfile from './components/ImageProfile'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <ImageProfile />
    </Provider>
  </React.StrictMode>,
)
