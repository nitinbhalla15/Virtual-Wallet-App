import { atom } from "recoil";

export const UserListAtom = atom({
    key:"UserListAtom",
    default:[{
        username:String(""),
        userInitial:String(""),
        userBalance:Number(0)
    }]
})