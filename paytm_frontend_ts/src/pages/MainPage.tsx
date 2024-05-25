"use client";
import { useNavigate } from "react-router-dom";
import { TypewriterEffect } from "../components/TypewriterEffect";

export default function MainPage() {
    const navigate = useNavigate();

    const words = [
        {
            text: "Transfer",
        },
        {
            text: "Money",
        },
        {
            text: "My",
        },
        {
            text: "Way",
        }
    ];
    return (
        <div className="flex flex-col items-center justify-center h-[40rem] bg-black size-full">
            <p className="text-neutral-600 dark:text-neutral-200 text-base  mb-10">
                The only road to payments
            </p>
            <TypewriterEffect words={words} />
            <div className="flex flex-col md:flex-row space-y-4 md:space-y-0 space-x-0 md:space-x-4 mt-10">
                <button className="w-40 h-10 rounded-xl bg-white text-black border border-black  text-sm" onClick={()=>{
                    navigate("/signup")
                }}>
                    Signup
                </button>
            </div>
        </div>
    );
}