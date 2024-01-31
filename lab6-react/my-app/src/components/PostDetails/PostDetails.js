import axios from "axios";
import { Fragment, useEffect, useState} from "react"
import Comment from "../Comment/Comment";

const PostDetails = (props)=>{
console.log("cccccc");
const [postDetail, setPostDetail] = useState({});
const space = <Fragment>&nbsp;&nbsp;</Fragment>;

useEffect(
    () => {
        axios.get('http://localhost:8080/api/v1/posts/' + props.id)
            .then(response => {
                setPostDetail(response.data)
                console.log("RESPONSE:", response.data)
            })
            .catch(err => console.log(err.message))
    },
    [props.id])

    return(
        <div className="ContentDetail" >
                        <div >
                <span className="Label">Id:</span> {props.id}
            </div>
            <div >
                <span className="Label">Title:</span> {props.title}
            </div>
            <div >
                <span className="Label">Author:</span> {props.author}
            </div>
           
            <br />
                    <div style={{ textAlign: "left" }}>
                        {space} Comments <br />
                        {postDetail.comments != null ? postDetail.comments.map(comment => {
                            return <Comment comment={comment.name} key={comment.id}/>
                        }) : null}
                    </div>
            <div className="Delete">
                <input
                    type="button"
                    value="Delete"
                    onClick={()=>props.deletePost(props.id)} />
            </div>
          
        </div>
    )


}

export default PostDetails;