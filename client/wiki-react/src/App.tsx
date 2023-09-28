import React, { Suspense } from 'react'
// 定义路由规则
import {Routes, Route, Link, NavLink, Navigate, useNavigate} from 'react-router-dom'
// Route用来定义路由规则的，它一定要在Routes组件中包裹起来，否则报错
import { AppstoreOutlined, MailOutlined, SettingOutlined } from '@ant-design/icons';

const {Footer, Sider, Content } = Layout;

const headerStyle: React.CSSProperties = {

};

const contentStyle: React.CSSProperties = {
    color: '#fff',
    backgroundColor: '#108ee9',
};

const siderStyle: React.CSSProperties = {
    textAlign: 'center',
    lineHeight: '120px',
    color: '#fff',
    backgroundColor: '#3ba0e9',
};

const footerStyle: React.CSSProperties = {
    textAlign: 'center',
    color: '#fff',
    backgroundColor: '#7dbcea',
    position: 'fixed',
    bottom: 0,
    right: 0,
    left: 0
};

// 路由匹配成功后要渲染的组件
import Home from './view/Home'
import {Layout, Menu} from "antd";
const About = React.lazy(() => import('./view/About'))

const Detail = React.lazy(() => import('./view/Detail'))

const App = () => {
    const navigate = useNavigate()
    return (
    <div>
        <Layout>
            <div style={headerStyle}>
                <Menu
                    theme="dark"
                    mode="horizontal"
                    defaultSelectedKeys={['/home']}
                    items={[
                        {
                            label: 'Home',
                            key: '/home',
                            icon: <MailOutlined />,
                        },
                        {
                            label: 'About',
                            key: '/about',
                            icon: <MailOutlined />,
                        }
                    ]}
                    onClick={(e) =>{
                        navigate(e.key)
                    }}
                />

            </div>
            <Layout hasSider>
                <Content style={contentStyle}>
                    <Routes>
                        <Route path='/home' element={<Home />} />
                        <Route path='/about' element={<Suspense fallback={<p>loading</p>}><About /></Suspense>} />
                        <Route path='/detail/:id' element={<Suspense fallback={<p>loading</p>}><Detail /></Suspense>} />

                        {/* 重定向 */}
                        <Route path="/home" element={<Navigate to="/home" replace={false} />} />
                        {/* 404页面处理 */}
                        <Route path="*" element={<div>404</div>} />

                    </Routes>

                </Content>
            </Layout>
            <Footer style={footerStyle}>Footer</Footer>
        </Layout>


    </div>
  )
}

export default App
