import {Avatar, Col, List, Row, Space, Table} from 'antd'
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

const Ebook = () => {
    const navigate = useNavigate()
    const [first, setFirst] = useState({a: 1})
    const [list, setList] = useState<any>([]);
    const [pageInfo, setPageInfo] = useState(() => ({total: 0, current: 1}));


    useEffect(() => {
        getList(pageInfo)
    }, [])
    const getList = (data:any) => {
        axios.get(`/ebook/list?page=${data.current}&size=3`).then(res => {
            setPageInfo({...pageInfo, total: res.data.content.total});
            setList(res.data.content.list)

        })
    }



    return (
        <div>
           <Table
           dataSource={list}
           columns={[
               {
                   title: 'id',
                   dataIndex: 'id',
               },
               {
                   title: '名称',
                   dataIndex: 'name',
               },
               {
                   title: 'docCount',
                   dataIndex: 'docCount',
               },
               {
                   title: 'description',
                   dataIndex: 'description',
               },
               {
                   title: 'cover',
                   dataIndex: 'description',
               },
               {
                   title: '操作',
                   dataIndex: 'ac',
                   render(row){
                       return (<Space><span>编辑</span><span>操作</span></Space>)
                   }
               },
           ]}

           ></Table>



        </div>
    )
}

export default Ebook;
