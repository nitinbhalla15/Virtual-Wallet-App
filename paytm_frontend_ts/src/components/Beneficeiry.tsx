import { useRecoilValue, useSetRecoilState } from "recoil";
import Button  from "./Button";
import { useNavigate } from "react-router-dom";
import { UserListAtom } from "../store/atoms/UserListAtom";
import { FriendInfoAtom } from "../store/atoms/FriendInfo";

export function Beneficeiry() {
    const userList = useRecoilValue(UserListAtom)
    const navigate = useNavigate()
    const setFriendinfo = useSetRecoilState(FriendInfoAtom)
    console.log("user list " ,userList)
    return (
        <div>
            {userList[0].username!="" ? userList.map((user) => {
                console.log("USer list : ",user)
                return (
                    <div className="flex justify-between hover:bg-slate-200 border mt-4">
                        <div className="m-6 flex hover:cursor-pointer ">
                            <div className="rounded-full bg-gray-500 size-8 flex flex-row justify-center pt-0.5 text-md ">
                                {user.userInitial}
                            </div>
                            <div className="mx-4 font-bold text-lg">
                                {user.username}
                            </div>
                        </div>
                        <div>
                            <Button onClickFunction={() => {
                                setFriendinfo(user)
                                navigate("/sendMoney")
                            }} buttonName={"Send Money"}></Button>
                        </div>
                    </div>
                )
            }) : null }
        </div>
    )
}
