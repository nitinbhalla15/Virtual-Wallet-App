import React from "react"

interface ButtonProp{
    buttonName:string
    onClickFunction:(e:React.MouseEvent<HTMLButtonElement>)=>void
}

const Button : React.FC<ButtonProp> = (buttonProp:ButtonProp)=>{
    return(
        <div className="flex justify-center">
            <button onClick={buttonProp.onClickFunction} type="button" className="text-white bg-black  font-medium rounded-lg text-sm px-5 py-2.5 m-4 w-full hover:bg-zinc-900">{buttonProp.buttonName}</button>
        </div>
    )
}


export default Button;