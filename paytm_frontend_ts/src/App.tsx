import React, { Suspense } from 'react'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { ProtectedRoutes } from './components/ProtectedRoutes'

const Signup = React.lazy(() => import('./pages/Signup'))
const Signin = React.lazy(() => import('./pages/Signin'))
const Dashboard = React.lazy(() => import('./pages/Dashboard'))
const Sendmoney = React.lazy(() => import('./pages/Sendmoney'))
const MainPage = React.lazy(() => import('./pages/MainPage'))

function App() {
  return (
    <Suspense fallback="Loading...">
      <BrowserRouter>
        <Routes>
          <Route element={<ProtectedRoutes></ProtectedRoutes>}>
            <Route path='/dashboard' element={<Dashboard></Dashboard>}> </Route>
            <Route path='/sendMoney' element={<Sendmoney></Sendmoney>}> </Route>
            <Route path='/' element={<MainPage></MainPage>}> </Route>
          </Route>
          <Route path='/signup' element={<Signup></Signup>}> </Route>
          <Route path='/signin' element={<Signin></Signin>}> </Route>
        </Routes>
      </BrowserRouter>
    </Suspense>
  )
}

export default App
