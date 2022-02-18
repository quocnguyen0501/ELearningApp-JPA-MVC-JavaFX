package models;

import java.sql.Timestamp;

//@Entity
//@Table(name = "post")
public class PostModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column (name = "time_post", nullable = false)
    private Timestamp timePost;
}
