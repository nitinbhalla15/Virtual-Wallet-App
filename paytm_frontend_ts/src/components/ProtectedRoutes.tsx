import { Navigate, Outlet } from "react-router-dom";

export function ProtectedRoutes() {
    // const isUserAuthenticated = useRecoilValue(IsUserAuthenticatedAtom);
    // console.log("Is User Authenticated " , isUserAuthenticated);
    let isUserAuthenticated:any = window.localStorage.getItem("isAuth");
    if(isUserAuthenticated==null){
        isUserAuthenticated=false;
    }else{
        isUserAuthenticated=true;
    }
    return (
        <div>
            {isUserAuthenticated ? <Outlet /> : <Navigate to={"/signin"}></Navigate> }
        </div>
    )
}