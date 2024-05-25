import React, { useMemo } from "react"

import  Heading  from "../components/Heading"
import  SubHeading  from "../components/SubHeading"
import  InputBox  from "../components/InputBox"
import  Button  from "../components/Button"
import  BottomWarning  from "../components/BottonWarning"
import { useNavigate } from "react-router-dom"
import { useSetRecoilState } from "recoil"
import { UserDetailAtom } from "../store/atoms/DashboardAtoms"

export default function Signin() {
    const navigate = useNavigate();
    const setUserDetails = useSetRecoilState(UserDetailAtom)

    let userSignInRequest = {
        email:"",
        password:""
    }

    return (
        <div className="flex justify-center">
            <div className="size-1/3 mt-8 border rounded-md shadow-2xl">
                <Heading title={"Sign In"}></Heading>
                <SubHeading description={"Enter your credentials to access your acccount"}></SubHeading>
                <InputBox inputTitle="Email" placeholder="Enter your email id" onInputChange={(e:React.ChangeEvent<HTMLInputElement>)=>{
                    userSignInRequest.email=e.target.value
                }}></InputBox>
                <InputBox inputTitle="Password" placeholder="Enter your password" onInputChange={(e:React.ChangeEvent<HTMLInputElement>)=>{
                    userSignInRequest.password=e.target.value
                }}></InputBox>
                <Button onClickFunction={() => {
                    fetch("http://localhost:8081/api/v1/auth/signin" ,{
                        method:'POST',
                        headers:{
                            "content-Type":"application/json"
                        },
                        body:JSON.stringify(userSignInRequest)
                    }).then(async(res)=>{
                        const resposne = await res.json();
                        if(resposne.message=="SUCCESS"){
                            setUserDetails(resposne.user_info);
                            localStorage.setItem("token",resposne.token)
                            navigate("/dashboard")
                        }
                    })
                }} buttonName={"Sign In"}></Button>
                <BottomWarning warningHeading={"Don't have an account ?"} linkHeading={"Sign Up"} toLink={"/signup"}></BottomWarning>
            </div>
        </div>

    )
}