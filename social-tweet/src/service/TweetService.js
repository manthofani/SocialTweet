import axios from 'axios'

export default class TweetService {

    getTweets() {
        return axios.get('/tweets')
    }

    getPublicTweets() {
        return axios.get('/tweets/home')
    }

    getTweetById(id) {
        return axios.get("/tweets/" + id);
    }

    sendTweet(body) {
        return axios.post("/tweets", body)
    }

    getTweetsCommentByTweetId(id) {
        return axios.get((`/tweets/${id}/comments`))
    }

    deleteTweets(id){
        return axios.delete("/tweets/" + id)

    }

    updateTweet(body,id){
        return axios.patch(`/tweets/${id}`, body)
    }
}