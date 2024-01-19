package miu.edu.demoinclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    long id;
    String title;
    String content;
    String author;
    List<CommentResponse> comments;
}
