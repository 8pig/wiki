import React, { Suspense } from 'react'
// 定义路由规则
import { Routes, Route, Link, NavLink, Navigate } from 'react-router-dom'
// Route用来定义路由规则的，它一定要在Routes组件中包裹起来，否则报错

// 路由匹配成功后要渲染的组件
import Home from './view/Home'
const About = React.lazy(() => import('./view/About'))

const Detail = React.lazy(() => import('./view/Detail'))

const App = () => {
  return (
    <div>
        <div>
          <NavLink end to="/" state={{ id: 1000 }}>首页</NavLink>
          <span> --- </span>
          <NavLink to="/about">关于</NavLink>

        </div>
      <hr />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/about' element={<Suspense fallback={<p>loading</p>}><About /></Suspense>} />
        <Route path='/detail/:id' element={<Suspense fallback={<p>loading</p>}><Detail /></Suspense>} />

        {/* 重定向 */}
        <Route path="/" element={<Navigate to="/home" replace={false} />} />
        {/* 404页面处理 */}
        <Route path="*" element={<div>404</div>} />

      </Routes>

    </div>
  )
}

export default App
