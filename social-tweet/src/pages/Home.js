import React from 'react';
import TwitterIcon from '@mui/icons-material/Twitter';
import {useNavigate} from "react-router-dom";
import Content from "../layout/Content";


const Home = () => {

    const navigate = useNavigate()

    return (
        <div className="flex flex-col w-full h-screen m-0" style={{fontFamily: `Segoe UI, Arial, sans-serif`}}>
            <div className="flex flex-row h-screen ">
                {/* Left Panel */}
                <div className="flex w-1/2 m-5 rounded-md bg-blue-100">
                    <div className="max-h-72 m-auto max-w-sm">
                        <TwitterIcon style={{color: "#1DA1F2", marginBottom: "10px"}} fontSize="large"/>
                        <h1 className="text-2xl mb-6 font-bold" style={{lineHeight: "32px"}}>See what's happening in
                            <br/> the world right now</h1>
                        <span>Join Social Tweet today.</span>
                        <button
                            className="flex justify-center items-center w-full h-11 text-white font-bold py-2 px-4 rounded-2xl mt-3"
                            style={{backgroundColor: "#1DA1F2", border: `1px solid #1DA1F2`}}
                            onClick={() => navigate("/signup")}>Sign up
                        </button>
                        <button
                            className="flex justify-center items-center w-full h-11 bg-transparent py-2 px-4 rounded-2xl mt-1"
                            style={{color: "#1DA1F2", border: `1px solid #1DA1F2`}}
                            onClick={() => navigate("/login")}> Log in
                        </button>
                    </div>
                </div>
                {/* End Left Panel */}
                <Content/>                
            </div>
        </div>
    );
};

export default Home;