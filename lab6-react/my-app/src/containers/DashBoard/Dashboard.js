import Posts from "../Posts/Posts";
import NewPost from "../../components/NewPost/NewPost";
import Post from "../../components/Post/Post";
import { useEffect, useState } from "react";
import PostDetails from "../../components/PostDetails/PostDetails";
import axios from "axios";
export default function Dashboard(){

    let i = 4;
    const [postsState, setPostsState] = useState([]);

    const [postState, setPostState] = useState({
        id:"",
        title:"",
        author:"",
        
    });

    const [selectPostObj, setSelectPostObj] = useState({
        id:0,
        title:"",
        author:""
    });

    const [fetchFlag, setFetchflag] = useState(true);
    const [selectedState, setSelectedState] = useState(0);
    const [selectedAuthor, setSelectedAuthor] = useState();
    const [selectedTitle, setSelectedTitle] = useState();

    const setSelected = (id) => {
        setSelectedState(id);
        postsState.map(p=>{
            if(p.id===id){
                setSelectedTitle(p.title);
                setSelectedAuthor(p.author);
            }
        })
       
        // console.log(id)
        // setSelectedState(id);
        // setSelectedTitle(title);
        // setSelectedAuthor(author);
    }

    // const onChange=(events) =>{
    //     const copy = {...postState};
    //     copy[events.target.title] = events.target.value;
    //     setPostState(copy);
    // }

    const addButtonClicked=()=>{
        const copy = [...postsState];
        const updatedPosts = copy.map(post => {
            if (post.id === 1) {
                return { ...post, title: postState };
            }
            return post;
        });

        setPostsState(updatedPosts);

    }

    const fetchPosts= ()=>{
        axios.get('http://localhost:8080/api/v1/posts')
        .then(reponse => {
            setPostsState(reponse.data);
        })
        .catch(error =>{
        console.log(error.message)
        })
    }
    useEffect(()=>{
        fetchPosts();
    },[fetchFlag])

    const deleteButtonClicked=(id)=>{
        console.log("id:  "+id);
        axios.delete('http://localhost:8080/api/v1/posts/'+id)
            .then(response=>{
                fetchPosts();
            })
            .catch((err) => {
               // console.error(err);
              });

    }

    const handleInputChange=(event)=>{
        setPostState(event.target.value);
    }

    const changeFetchFlag = () => {
        setFetchflag(!fetchFlag);
    }

    return(
    
            <div>
                <div className="Post">
                <Posts
                    posts={postsState}
                    setSelected={setSelected} 
                    
                />
                </div>
                <div>
                    <input type="text" id="title" value={postState.title}
                    onChange={handleInputChange}
                    ></input>
                </div>
                <button onClick={addButtonClicked}>Change Name</button>
                <div className="PostDetail">
                    <PostDetails
                       // id={selectedState} posts={postState}
                      // postObj={selectPostObj}
                      author={selectedAuthor}
                        title={selectedTitle}
                        id={selectedState}
                        deletePost={deleteButtonClicked}
                    />
                </div>
                <div>
                   <NewPost changeFetchFlag={changeFetchFlag}
                   />
                </div>
            </div>      
    )
}