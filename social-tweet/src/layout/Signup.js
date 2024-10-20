import React, {useState} from 'react';
import TwitterIcon from "@mui/icons-material/Twitter";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import AuthService from "../service/AuthService";
import {login} from "../redux/reduxSlice";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import Content from "../layout/Content";


const Signup = () => {
    const [name, setName] = useState(null)
    const [email, setEmail] = useState(null)
    const [username, setUsername] = useState(null)
    const [password, setPassword] = useState(null)

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const signupClick = (e) => {
        let body = {
            name, email, username, password
        }
        let authService = new AuthService()
        authService.signup(body).then(() => {
            authService.login({username, password}).then(res => {
                dispatch(login(res.data))
                navigate("/")
            })
        })
        e.preventDefault()
    }
    return (
        <section className="flex flex-col w-full h-screen m-0" style={{fontFamily: `Segoe UI, Arial, sans-serif`}}>
            <div className="flex flex-row  h-screen ">
            <div className="flex flex-col w-1/2 m-5 p-10 rounded-md bg-blue-100">
            <div className="max-h-72 m-auto max-w-sm">
                    <div className="text-center">
                        <TwitterIcon style={{color: "#1DA1F2", marginBottom: "10px"}} fontSize="large"/>
                        <h1 className="text-xl md:text-2xl font-bold leading-tight">Join Twitter today.</h1>
                    </div>
                    <form className="mt-3">
                        <div>
                            <label className="block mb-2 text-sm font-bold text-gray-700" form="fullName">
                                Full name
                            </label>
                            <input
                                className="bg-gray-200 w-full px-3 py-2 mb-1 text-sm leading-tight text-gray-700 border rounded shadow appearance-none focus:outline-none focus:shadow-outline focus:bg-white"
                                id="fullName"
                                type="text"
                                placeholder="First Name"
                                onChange={e => setName(e.target.value)}
                            />
                        </div>
                        <div>
                            <label className="block mb-2 text-sm font-bold text-gray-700" form="email">
                                Email
                            </label>
                            <input
                                className="bg-gray-200 w-full px-3 py-2 mb-1 text-sm leading-tight text-gray-700 border rounded shadow appearance-none focus:outline-none focus:shadow-outline focus:bg-white"
                                id="email"
                                type="email"
                                placeholder="Email"
                                onChange={e => setEmail(e.target.value)}
                            />
                        </div>
                        <div>
                            <label className="block mb-2 text-sm font-bold text-gray-700" form="username">
                                Username
                            </label>
                            <input
                                className="bg-gray-200 w-full px-3 py-2 mb-1 text-sm leading-tight text-gray-700 border rounded shadow appearance-none focus:outline-none focus:shadow-outline focus:bg-white"
                                id="username"
                                type="text"
                                placeholder="Username"
                                onChange={e => setUsername(e.target.value)}
                            />
                        </div>
                        <div>
                            <label className="block mb-1 text-sm font-bold text-gray-700" form="password">
                                Password
                            </label>
                            <input
                                className="bg-gray-200 w-full px-3 py-2 mb-1 text-sm leading-tight text-gray-700 border rounded shadow appearance-none focus:outline-none focus:shadow-outline focus:bg-white"
                                id="password"
                                type="password"
                                placeholder="*************"
                                onChange={e => setPassword(e.target.value)}
                            />
                        </div>

                        <button type="submit" className="w-full block hover:bg-indigo-400 focus:bg-indigo-400 text-white font-semibold rounded-lg
              px-4 py-3 mt-4" style={{backgroundColor: "#00acee"}} onClick={signupClick}>Sign up
                        </button>
                    </form>
                    <div className="text-center mt-5">
                        <p className="cursor-pointer text-blue-500 hover:text-blue-700 font-semibold text-center"
                           style={{color: "#00acee"}} onClick={() => navigate("/login")}>
                            Already have an account? Log in</p>
                        <ArrowBackIosIcon style={{ marginTop:"10px",  color: "#00acee", cursor: "pointer"}} onClick={() => navigate("/")}/>
                    </div>       
                </div>
                </div>
                <Content/>      
                </div>
        </section>
    );
}
export default Signup;