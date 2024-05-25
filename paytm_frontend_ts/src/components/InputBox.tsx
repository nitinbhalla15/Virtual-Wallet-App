interface InputProp{
    inputTitle:string
    placeholder:string
    onInputChange?: (e:React.ChangeEvent<HTMLInputElement>)=>void;
}

const InputBox : React.FC<InputProp> = (inputProp:InputProp)=>{

    return (
        <div className="flex justify-center flex-col m-4">
            <div className="font-medium p-2">{inputProp.inputTitle}</div>
            <input onChange={inputProp.onInputChange} className="p-2 border-2 rounded" type="text" placeholder={inputProp.placeholder}></input>
        </div>
    )
}


export default InputBox