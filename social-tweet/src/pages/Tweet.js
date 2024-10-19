import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import {ReplyIcon, ReTweetIcon, LikeIcon, ShareIcon} from "../icons/Icon";
import TweetService from "../service/TweetService";
import FeedList from "../components/FeedList";
import {ArrowBack} from "@mui/icons-material";
import Divider from "../components/Divider";
import profile from "../images/default-profile.png";

const Tweet = () => {
    const [tweet, setTweet] = useState()
    const [comments, setComments] = useState({content: []});

    const {id} = useParams();
    const navigate = useNavigate()
    useEffect(() => {
        let tweetService = new TweetService()
        tweetService.getTweetById(id).then(res => setTweet(res.data))
        tweetService.getTweetsCommentByTweetId(id).then(res => setComments(res.data))
    }, [id]);
    return (
        <div className={"flex flex-col border-r border-l border-gray-extraLight w-1/2 mr-auto"}>
            <header
                className="sticky top-0 z-10 bg-white flex justify-between items-center p-4 border-b border-gray-extraLight ">
                <span className="font-medium text-lg text-gray-900">Replying to @{tweet && tweet.username}</span>
                <ArrowBack className="w-6 h-6 text-primary-base cursor-pointer" onClick={() => navigate("/")}/>
            </header>
            <div className="flex space-x-4 px-4 py-3">
                <img
                    src={tweet ? `http://localhost:8080/v1/users/${tweet.username}/image/download` : profile}
                    alt="Profile"
                    className="w-11 h-11 rounded-full"/>
                <div className="flex-1">
                    <div className="flex items-center text-sm">
                        <h4 className="font-bold">{tweet && tweet.name}</h4>
                        <span className="ml-2 text-gray-dark">@{tweet && tweet.username}</span>
                        <div className="mx-2 bg-gray-dark h-1 w-1 border rounded-full"/>
                        <span className="text-gray-dark">
                        {new Date(tweet && tweet.creationTimestamp).toLocaleDateString("id-ID",  { weekday: 'long' }) } ,
                        {new Date(tweet && tweet.creationTimestamp).toLocaleString("en-EN")}
                        </span>
                    </div>
                <p className="mt-2 text-gray-900 text-sm">{tweet && tweet.text}</p>
                <ul className="-ml-1 mt-3 flex justify-between max-w-md">
                <li className="flex"> <ReplyIcon className="mr-2 w-5 h-5 group-hover:text-primary-base"/> {tweet && tweet.comments.length} </li>
                <li className="flex"> <ReTweetIcon className="mr-2 w-5 h-5 group-hover:text-primary-base"/> {tweet && tweet.retweets.length} </li>
                <li className="flex"> <LikeIcon className="mr-2 w-5 h-5 group-hover:text-primary-base"/> {tweet && tweet.likes.length} </li>
                <li className="flex"> <ShareIcon className="mr-2 w-5 h-5 group-hover:text-primary-base"/></li>
                </ul>
                </div>
            </div>
            <Divider/>
            <div className="flex flex-col border-r border-l border-gray-extraLight w-full mr-auto">
                {comments.content.length > 0 && <FeedList tweets={comments.content} isComment={true}/>}
            </div>
        </div>
    );
};

export default Tweet;