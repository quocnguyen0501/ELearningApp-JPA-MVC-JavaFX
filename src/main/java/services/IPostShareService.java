package services;

import models.PostShareModel;
import models.UserPostModel;

import java.util.List;

public interface IPostShareService {
    PostShareModel insert (PostShareModel share);
    PostShareModel update (PostShareModel share);
    void delete (PostShareModel share);

    PostShareModel selectById (Long id);

    List<PostShareModel> selectAll ();
    List<PostShareModel> selectByPost (UserPostModel post);

    Long selectNumberAllShare ();
    Long selectNumberShareOfPost (UserPostModel post);
}
