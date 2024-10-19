import React from "react";
import FeedItem from "./FeedItem";

const FeedList = ({tweets, isComment}) => {
    return (
        <div>
            {tweets.map((tweet, index) => (
                <FeedItem {...tweet} key={index} isComment={isComment}/>
            ))}
        </div>
    );
};

export default FeedList;
