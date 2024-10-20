import React, {useEffect, useState} from "react";
import LogoutIcon from '@mui/icons-material/Logout';
import {useDispatch, useSelector} from "react-redux";
import {logout, setUserDetails} from "../redux/reduxSlice";
import UserService from "../service/UserService";
import {useNavigate} from "react-router-dom";
import profile from "../images/default-profile.png"

const UserBox = () => {
    const username = useSelector(state => state.reduxSlice.username)

    const [user, setUser] = useState()
    const dispatch = useDispatch()


    useEffect(() => {
        let userService = new UserService()
        userService.getUserByUsername(username).then(res => {
            setUser(res.data)
            dispatch(setUserDetails(res.data))
        })
    }, [username,dispatch])

    const navigate = useNavigate()

    const logoutClick = () => {
        dispatch(logout())
    }
    return (
        <>
            {user &&
                <div className="flex items-center mb-6">
                    <div className="flex hover:bg-primary-light cursor-pointer rounded-full py-2 px-6 transform transition-colors duration-200"
                        onClick={() => navigate("/" + user.username)}>
                    <img
                        src={user.profileImageLink ? `http://localhost:8080/v1/users/${username}/image/download` : profile}
                        alt="Profile"
                        className="w-11 h-11 rounded-full"
                    />
                    <div className="flex flex-col ml-3">
                        <span className="font-bold text-md text-black">{user.name}</span>
                        <span className="text-sm text-gray-dark">@{user.username}</span>
                    </div>
                    </div>
                    <div className="hover:bg-primary-light cursor-pointer rounded-full py-2 px-6 transform transition-colors duration-200">
                    <LogoutIcon style={{marginLeft: "auto"}} onClick={logoutClick}/>
                    </div>
                </div>}
        </>
    );
};

export default UserBox;
