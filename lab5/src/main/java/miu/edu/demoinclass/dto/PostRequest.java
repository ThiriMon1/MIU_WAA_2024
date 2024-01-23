package miu.edu.demoinclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    String title;
    String content;
    String author;
    List<CommentRequest> comments;
}
