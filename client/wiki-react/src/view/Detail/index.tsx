import React from 'react'
import { useLocation, useParams, useSearchParams } from 'react-router-dom'
// useParams 获取动态路由参数
// useSearchParams 获取search字符串
// useLocation location对象，可以得到state数据，得到pathname，得到search字符串

const Detail = () => {
  const location = useLocation()
  const params = useParams()
  // search它是一个对象，URLSearchParams对象，可以用里面的get方法获取search中的字段数据
  // setSearch 可以动态的修改当前search字符串中的字段数据，一般用不到
  const [search, setSearch] = useSearchParams()
  // 一般情况下是这种写法
  // const [search] = useSearchParams()

  return (
    <div>
      <h3>页面详情</h3>
      <div>动态路由参数id：{params.id}</div>
      <div>state中的数据：{location.state?.name}</div>
      <div>search字符串中的age：{search.get('age')}</div>
      <button
        onClick={() => {
          // 修改之后state中的数据会丢失
          setSearch('?age=100')
        }}
      >
        修改search字符串
      </button>
    </div>
  )
}

export default Detail
