import React from "react"

interface SubHeadingProp{
    description:String
}

const SubHeading : React.FC<SubHeadingProp>=(inputProp:SubHeadingProp)=>{
    return (
        <div className="p-2  flex justify-center text-gray-600">
            {inputProp.description}
        </div>
    )
}

export default SubHeading

