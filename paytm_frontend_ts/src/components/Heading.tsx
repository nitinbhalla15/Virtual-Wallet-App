import React from "react"

interface HeadingProp{
    title:string
}

const Heading : React.FC<HeadingProp>=(inputProp:HeadingProp)=>{
    return(
        <div className="text-4xl font-bold flex justify-center p-2">
            {inputProp.title}
        </div>
    )
}


export default Heading;