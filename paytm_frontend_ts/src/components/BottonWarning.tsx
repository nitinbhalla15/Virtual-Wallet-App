import React from "react";
import { Link } from "react-router-dom";

interface BottomWarning{
    warningHeading:string
    linkHeading:string
    toLink:string
}


const BottomWarning : React.FC<BottomWarning> = (inputProp:BottomWarning)=> {
    return (
        <div className="font-medium p-2 flex justify-center">
            <div>
                {inputProp.warningHeading}
            </div>
            <div className="mx-2 underline">
                <Link to={inputProp.toLink}>{inputProp.linkHeading}</Link>
            </div>
        </div>
    )
}

export default BottomWarning