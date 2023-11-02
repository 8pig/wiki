import { Avatar, Col, List, Row, Space } from 'antd'
import axios from 'axios'
import React, { useCallback, useEffect, useState } from 'react'
// 编程式导航，需要用到hook函数来得到导航对象，完成编程导航
import { NavLink, useNavigate } from 'react-router-dom'
import { LikeOutlined, MessageOutlined, StarOutlined } from '@ant-design/icons';
import { get } from 'http';

const IconText = ({ icon, text }: { icon: React.FC; text: string }) => (
  <Space>
    {React.createElement(icon)}
    {text}
  </Space>
);

const Home = () => {
  const navigate = useNavigate()
  const [first, setFirst] = useState({a: 1})
  const [list, setList] = useState<any>([]);
  const [pageInfo, setPageInfo] = useState(() => ({total: 0, current: 1}));
  const [page, setPage] = useState(1)


  useEffect(() => {
    getList(pageInfo)
  }, [])
  const getList = (data:any) => {
    axios.get(`/ebook/list?page=${data.current}&size=3`).then(res => {
      setPageInfo({...pageInfo, total: res.data.content.total});
      setList(res.data.content.list)

  })
}

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
      <button>1</button>



      <List
        grid={{ gutter: 20, column: 3 }}
        itemLayout="vertical"
        size="large"
        pagination={{
          onChange: (page) => {
            console.error(page, pageInfo.total);
            const data = {total: pageInfo.total, current: page}
            // Object.assign(pageInfo, {current: page})
            setPageInfo(data)
            getList({current: page})

          },
          pageSize: 3,
          total: pageInfo.total,
        }}
        dataSource={list}
        footer={
          <div>
            <b>ant design</b> footer part
          </div>
        }
        renderItem={(item: any) => (
          <List.Item
            key={item.id}
            actions={[
              <IconText icon={StarOutlined} text="156" key="list-vertical-star-o" />,
              <IconText icon={LikeOutlined} text="156" key="list-vertical-like-o" />,
              <IconText icon={MessageOutlined} text="2" key="list-vertical-message" />,
            ]}
          >
            <List.Item.Meta
              avatar={<Avatar src={item.avatar} />}
              title={<a href={item.name}>{item.name}</a>}
              description={item.description}
            />
            {item.description}
          </List.Item>
        )}
      />


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
