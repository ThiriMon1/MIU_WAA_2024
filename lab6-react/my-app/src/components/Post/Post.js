
const Post=(props) => {
    return(
        <div className="Content" onClick={props.setSelected}>
                        <div >
                <span className="Label">Id:</span> {props.id}
            </div>
            <div >
                <span className="Label">Title:</span> {props.title}
            </div>
            <div >
                <span className="Label">Author:</span> {props.author}
            </div>
          
        </div>
    )
}
export default Post;