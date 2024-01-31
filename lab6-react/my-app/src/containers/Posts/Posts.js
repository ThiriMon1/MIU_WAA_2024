import { useState, useEffect } from "react";
import Post from "../../components/Post/Post";
import axios from "axios"

const Posts=(props)=>{
   
    const postList = props.posts.map(post=>{
        return<Post
            title={post.title}
            author={post.author}
            id={post.id}
            key={post.id}
            setSelected={()=>{props.setSelected(post.id,post.title,post.author)}}
            />
            
    });
    return postList;
}
export default Posts;