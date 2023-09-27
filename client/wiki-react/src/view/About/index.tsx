import React from 'react'
import { useNavigate } from 'react-router-dom'

const About = () => {
  const navigate = useNavigate()
  return (
    <div>
      <h3>关于我们</h3>
      <hr />
      <button
        onClick={() => {
          // 回退
          navigate(-1)
        }}
      >
        回到首页
      </button>
    </div>
  )
}

export default About



