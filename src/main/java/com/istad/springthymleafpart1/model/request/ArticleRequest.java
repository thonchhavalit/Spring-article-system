package com.istad.springthymleafpart1.model.request;


import com.istad.springthymleafpart1.model.Author;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {
    @NotEmpty (message = "title can't be empty")
    private String title;
    @NotEmpty (message = "description can't be empty")
    private String description;
//    private String imgUrl;
    private MultipartFile file;
    private int authorID;

}
