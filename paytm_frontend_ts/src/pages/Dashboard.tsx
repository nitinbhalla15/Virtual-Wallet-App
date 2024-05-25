import { useMemo } from "react";
import { AppBar } from "../components/AppBar";
import { BalanceComponent } from "../components/BalanceComponent";
import { UserList } from "../components/UserList";
import { useRecoilValue } from "recoil";
import { UserDetailAtom } from "../store/atoms/DashboardAtoms";

export default function Dashboard(){

    const userDetails = useRecoilValue(UserDetailAtom);

    return(
        <div>
            <AppBar appBarHeading={"Payments App"} username={userDetails.username}
            userInitial={userDetails.userInitial}></AppBar>
            <BalanceComponent balance={userDetails.userBalance}></BalanceComponent>
            <UserList></UserList>
        </div>
    )
}