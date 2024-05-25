import Heading from "../components/Heading";
import SubHeading from "../components/SubHeading";
import InputBox from "../components/InputBox";
import Button from "../components/Button";
import BottomWarning from "../components/BottonWarning";
import { useNavigate } from "react-router-dom";
import { useSetRecoilState } from "recoil";
import { UserDetailAtom } from "../store/atoms/DashboardAtoms";
import React from "react";


export default function Signup() {
    const navigate = useNavigate();
    let userDetails = {
        firstName: "",
        lastName: "",
        email: "",
        password: ""
    }
    const setUserDetails = useSetRecoilState(UserDetailAtom)

    return (
        <div>
            <div className="flex justify-center">
                <div className="size-1/3 mt-8 border rounded-md shadow-2xl">
                    <Heading title={"Sign Up"}></Heading>
                    <SubHeading description={"Enter your information to create an account"}></SubHeading>
                    <InputBox onInputChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        userDetails.firstName = e.target.value;
                    }} inputTitle={"First Name"} placeholder={"Enter your first Name"}></InputBox>
                    <InputBox onInputChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        userDetails.lastName = e.target.value;
                    }} inputTitle={"Last Name"} placeholder={"Enter your last Name"}></InputBox>
                    <InputBox onInputChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        userDetails.email = e.target.value;
                    }} inputTitle={"Email"} placeholder={"Enter your email id"}></InputBox>
                    <InputBox onInputChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                        userDetails.password = e.target.value;
                    }} inputTitle={"Password"} placeholder={"Set your password"}></InputBox>
                    <Button onClickFunction={() => {
                        let formData = {
                            "firstName": userDetails.firstName,
                            "lastName": userDetails.lastName,
                            "email": userDetails.email,
                            "password": userDetails.password
                        }
                        fetch("http://localhost:8081/api/v1/auth/signup", {
                            method: 'POST',
                            headers: {
                                "content-Type": "application/json"
                            },
                            body: JSON.stringify(formData)
                        }).then(async (res) => {
                            const response = await res.json();
                            console.log("Response : ", response);
                            if (response.message == "SUCCESS") {
                                setUserDetails(response.user_info);
                                localStorage.setItem("token", response.token)
                                navigate("/dashboard")
                            }
                        })
                    }} buttonName={"Sign Up"}></Button>
                    <BottomWarning warningHeading={"Already have an account ?"} linkHeading={"Signin / Login"} toLink={"/signin"}></BottomWarning>
                </div>
            </div>
        </div>
    )
}