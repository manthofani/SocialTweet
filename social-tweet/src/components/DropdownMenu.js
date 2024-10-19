import React from "react";
import TweetService from "../service/TweetService";
import Modal from "./Modal";

const DropdownMenu = (id, text) => {

    const refreshPage = () => {
        window.location.reload(false);
    }

    const deleteTweet = () => {
            let tweetService = new TweetService()
            tweetService.deleteTweets(id.tweetId).then(() => refreshPage())
    }
    
    return (
        <div className="absolute bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700">
            <ul className="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
              <li>
                 <Modal icon={<p className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Edit</p>} 
                        setCount={0}
                        profilePicture={''}
                        id={id}
                        box="update"/>
              </li>
              <li>
                <p className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white" onClick={deleteTweet}>Delete</p>
              </li>
            </ul>
        </div>
    );
  };
  
  export default DropdownMenu;
  