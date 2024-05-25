import  InputBox  from "./InputBox";
import { useSetRecoilState } from "recoil";
import { UserListAtom } from "../store/atoms/UserListAtom";
import { Beneficeiry } from "./Beneficeiry";
import React from "react";

export function UserList() {
    const setUserListAtom = useSetRecoilState(UserListAtom);
    let timeoutId: number;
    return (
        <div className="flex flex-col">
            <InputBox onInputChange={(e:React.ChangeEvent<HTMLInputElement>) => {
                let username = e.target.value;
                if (username == "" || username == null) {
                    username = "123"
                    console.log("user name passed null ", username.length)
                }
                console.log("username entered : ", username)
                if (timeoutId) {
                    clearTimeout(timeoutId)
                }
                timeoutId = setTimeout(() => {
                    fetch(`http://localhost:8081/api/v1/user/findUser/${username}`, {
                        headers: {
                            "content-Type": "application/json",
                            "Authorization": "Bearer " + localStorage.getItem("token")
                        }
                    }
                    ).then(async (res) => {
                        const response = await res.json();
                        setUserListAtom(response.data)
                    })
                }, 300);

            }} inputTitle={"Users"} placeholder={"Search Users ..."}></InputBox>
            <Beneficeiry></Beneficeiry>
        </div>
    )
}