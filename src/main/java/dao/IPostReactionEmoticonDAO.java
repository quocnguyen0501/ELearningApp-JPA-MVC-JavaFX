package dao;

import models.PostReactionEmoticonModel;
import models.UserModel;
import models.UserPostModel;

import java.util.List;

public interface IPostReactionEmoticonDAO extends IBaseDAO{
    PostReactionEmoticonModel insert (PostReactionEmoticonModel reactionEmoticon);
    PostReactionEmoticonModel update (PostReactionEmoticonModel reactionEmoticon);
    void delete (PostReactionEmoticonModel reactionEmoticon);

    PostReactionEmoticonModel selectById (Long id);

    List<PostReactionEmoticonModel> selectAll ();
    List<PostReactionEmoticonModel> selectByPost (UserPostModel post);
    PostReactionEmoticonModel selectByPostAndUser (UserModel user, UserPostModel post);

    Long selectNumberAllReactionEmoticon ();
    Long selectNumberReactionEmoticonOfPost (UserPostModel post);

    String getReactionEmoticonByUserAndPost (UserModel user, UserPostModel post);
    Long getNumberReactionEmoticonByUserAndPost (UserModel user, UserPostModel post);
}
