import React from "react";
import {useSelector} from "react-redux";
import {Close} from "@mui/icons-material";
import CommentBox from "./CommentBox";
import RetweetBox from "./RetweetBox";
import TweetBox from "./TweetBox";

export default function Modal({icon, setCount, id, profilePicture, box}) {
    const [showModal, setShowModal] = React.useState(false);
    const currentUser = useSelector(state => state.reduxSlice.currentUser)

    const refreshPage = () => {
        window.location.reload(false);
    }

    return (
        <>
           <div onClick={() => setShowModal(true)}> {icon} </div> 
            {showModal && currentUser ? (
                <>
                    <div
                        className="flex justify-center items-center overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none">
                        <div className="relative my-6 mx-auto max-w-3xl">
                            <div
                                className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                                <div className="relative p-6 flex-auto" onSubmit={() => setShowModal(false)}>
                                    <Close className="mb-1" onClick={() => setShowModal(false)}/>
                                    <div className="flex space-x-4 px-4 py-3">
                                        {box.toString() === "update" ? '' :<img
                                            src={profilePicture}
                                            alt="Profile"
                                            className="w-11 h-11 rounded-full"/>}
                                        {box.toString() === "comment" ?
                                            <CommentBox tweetId={id} profilePicture={profilePicture} setCount={setCount} closeModal={() => setShowModal(false)}/>
                                        : box.toString() === "retweet" ?
                                            <RetweetBox tweetId={id} profilePicture={profilePicture} setCount={setCount} closeModal={() => setShowModal(false)}/> 
                                        : box.toString() === "update" ?
                                            <TweetBox refresh={refreshPage} id={id}  closeModal={() => setShowModal(false)}/> : null}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="opacity-10 fixed inset-0 z-40 bg-black"/>
                </>
            ) : null}
        </>
    );
}