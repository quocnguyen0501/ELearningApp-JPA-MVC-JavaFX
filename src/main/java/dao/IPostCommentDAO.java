package dao;

import models.PostCommentModel;
import models.UserPostModel;

import java.util.List;

public interface IPostCommentDAO extends IBaseDAO {
    PostCommentModel insert (PostCommentModel comment);
    PostCommentModel update (PostCommentModel comment);
    void delete (PostCommentModel comment);

    PostCommentModel selectById (Long id);

    List<PostCommentModel> selectAll ();
    List<PostCommentModel> selectByPost (UserPostModel post);

    Long selectNumberAllComment ();
    Long selectNumberCommentOfPost (UserPostModel post);
}
