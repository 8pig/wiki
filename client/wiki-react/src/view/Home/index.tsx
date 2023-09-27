import React, { useState } from 'react'
// 编程式导航，需要用到hook函数来得到导航对象，完成编程导航
import { NavLink, useNavigate } from 'react-router-dom'

const Home = () => {
  const navigate = useNavigate()
  const [first, setFirst] = useState({a: 1})

  const jumpUrl = () => {
    // 写法1
    // 参数1：string/number  string它就是一个url地址，number就是回退的步数 正向前 负向后
    // 参数2：对象，可选，可以不写
    // navigate('/about', {
    //   state: {}, // 隐式传递数据
    //   replace: true // 不可回退
    // })

    // 写法2
    navigate('/about')
  }

  return (
    <div>
      <h3>首页页面</h3>
      <hr />
      <button onClick={jumpUrl}>跳转到关于页面</button>
      <br></br>
      <ul>
      {
        [{title: 'title1', age: 10},{title: 'title2', age: 11},{title: 'title3', age: 30}].map(item => {
          return <li
            onClick={() => {
              navigate(`/detail/${item.age}?age=${item.age}`, {
                state:  { name: item.title }, // 隐式传递数据
                replace: true // 不可回退
              })


            }}
          key={item.title}>{ item.title }</li>
        })
      }

      </ul>
    </div>
  )
}

export default Home
