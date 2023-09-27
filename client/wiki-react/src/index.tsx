import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
// 定义当前项目的路由模式
import { BrowserRouter as Router } from 'react-router-dom'

import { ConfigProvider, Button, Space, Input, Divider } from 'antd';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
      <ConfigProvider
      theme={{
        token: {
          // Seed Token，影响范围大
          colorPrimary: '#00b96b',
          borderRadius: 2,
          // 派生变量，影响范围小
          colorBgContainer: '#f6ffed',
        },
      }}
    >
      <Router>
        <App />
      </Router>
      </ConfigProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
