import React, {useState,useEffect} from "react";
import TweetService from "../service/TweetService";
import {useSelector} from "react-redux";
import Box from "./Box";

const TweetBox = ({refresh, id}) => {
    const [content, setContent] = useState("");
    const [status,setStatus] = useState(1);
    const currentUser = useSelector(state => state.reduxSlice.currentUser)

    const setStatusClick = () => {
        if (status === 1) {
            setStatus(0);
        } else {
            setStatus(1);
        }
    }

    useEffect(() => {
        if (id) {
            setContent(id.text)
        }
    }, [id]);

    const setContentFunc = x => setContent(x)

    const sendTweet = () => {
        let tweetService = new TweetService()

        if (id) {
            let body = {
                text: content,
                status: status
            }
            tweetService.updateTweet(body,id.tweetId).then(() => refresh())

        } else {
            let body = {
                text: content,
                userId: currentUser,
                status: status
            }
            tweetService.sendTweet(body).then(() => refresh())
        }
    };
    return (
        <Box content={content}
             setContentFunc={setContentFunc}
             setStatusClick={setStatusClick}
             status={status}
             sendFunc={sendTweet}
             submit="Tweet"
             placeHolder="What's on your mind? "/>
    );
};

export default TweetBox;
