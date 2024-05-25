import { atom } from "recoil";

export const FriendInfoAtom = atom({
    key: "FriendInfoAtom",
    default: {
        username:String(""),
        userInitial:String(""),
        userBalance:Number(0)}
}
)