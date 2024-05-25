import { useRecoilValue } from "recoil";
import Heading from "../components/Heading";
import  InputBox  from "../components/InputBox";
import { FriendInfoAtom } from "../store/atoms/FriendInfo";
import {  UserDetailAtom } from "../store/atoms/DashboardAtoms";
import {  useNavigate } from "react-router-dom";

export default function Sendmoney() {

    const friendInfo = useRecoilValue(FriendInfoAtom)
    const userDetails = useRecoilValue(UserDetailAtom);
    // const jwtToken= useRecoilValue(TokenAtom)
    let amount=0;
    const navigate= useNavigate();    
    return (
        <div className="flex justify-center">
            <div className="size-1/3 mt-8 border rounded-md shadow-2xl">
                <Heading title={"Send Money"}></Heading>
                <div className="flex flex-col">
                    <div className="m-6 flex hover:cursor-pointer ">
                        <div className="rounded-full bg-gradient-to-r from-green-400 via-green-500 to-green-600 size-9 flex flex-row justify-center pt-1 text-md text-white">
                            {friendInfo.userInitial}
                        </div>
                        <div className="mx-4 font-bold text-2xl">
                            {friendInfo.username}
                        </div>
                    </div>
                        <InputBox onInputChange={(e)=>{
                            amount = parseInt(e.target.value)
                        }} inputTitle={"Amount (in $)"} placeholder={`Current Balance : $${userDetails.userBalance}`}></InputBox>
                    <div className="flex justify-center">
                        <button type="button" className="text-white bg-gradient-to-r from-green-400 via-green-500 to-green-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 shadow-lg shadow-green-500/50 dark:shadow-lg dark:shadow-green-800/80 font-medium rounded-lg p-4 m-4 w-full"
                        onClick={()=>{
                            let fromToTranfer = {
                                from:userDetails.username,
                                to:friendInfo.username,
                                money:amount,
                            }
                            console.log("payload : ",fromToTranfer)
                            fetch(`http://localhost:8081/api/v1/account/transfer/${fromToTranfer.from}/${fromToTranfer.to}/${fromToTranfer.money}`,{
                                method:"POST",
                                headers:{
                                    "content-Type":"application/json",
                                    "Authorization":"Bearer "+localStorage.getItem("token")
                                }
                            }).then(async(res)=>{
                                const response = await res.json();
                                console.log("response after transfer : ",response)
                                navigate("/dashboard")
                            })
                        }}>Initiate Transfer</button>
                    </div>
                </div>
                        
            </div>
        </div>
    )
}