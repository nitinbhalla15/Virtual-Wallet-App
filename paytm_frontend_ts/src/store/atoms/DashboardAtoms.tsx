import { atom } from "recoil";

export const UserDetailAtom  = atom({
    key:"UserDetailAtom",
    default:{
        username:String(""),
        userInitial:String(""),
        userBalance:Number(0)
    }
})


export const TokenAtom = atom({
    key:"TokenAtom",
    default:""
})