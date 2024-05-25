export function BalanceComponent(balanceProp:BalanceComponentInterface){
    return(
        <div className="m-6 text-xl font-bold">
            Your Balance : ${balanceProp.balance}
        </div>
    )
}