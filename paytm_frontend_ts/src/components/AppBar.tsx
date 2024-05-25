export function AppBar(appBarProps:AppBarProps) {
    return (
        <div className="p-6 border flex justify-between">
            <div className="text-3xl font-bold">
                {appBarProps.appBarHeading}
            </div>
            <div className="flex p-2 justify-center font-bold text-lg">
                <div className="flex justify-center ">
                    Hello, {appBarProps.username}
                </div>
                <div className="mx-5 rounded-full bg-gray-500 size-8 flex flex-row justify-center pt-0.5 cursor-pointer">
                {appBarProps.userInitial}</div>
            </div>
        </div>
    )
}