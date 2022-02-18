package helper;

import java.util.Arrays;
import java.util.List;

public enum CourseContent {
    SORTING(0, "Sorting", ImgConst.SORTING, Arrays.asList("array", "algorithm", "bubble", "sort")),
    LINKED_LIST(1, "Linked List", ImgConst.LINKED_LIST, Arrays.asList("stack", "queue", "doubly", "deque")),
    BINARY_HEAP(2, "Binary Heap", ImgConst.BINARY_HEAP, Arrays.asList("dynamic", "range", "sum", "cs2040")),
    HASH_TABLE(3, "Hash Table", ImgConst.HASH_TABLE, Arrays.asList("open", "address", "linear", "quadratic")),
    BINARY_SEARCH_TREE(4, "Binary Search Tree", ImgConst.BINARY_SEARCH_TREE, Arrays.asList("set", "bst", "avl", "table")),
    GRAPH_STRUCTURES(5, "Grap Structures", ImgConst.GRAPH_STRUCTURES, Arrays.asList("tree", "complete", "dag", "bipartite"));

    private int id;
    private String name;
    private String imgSrc;
    private List<String> listContent;

    CourseContent(int id, String name, String imgSrc, List<String> listContent) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
        this.listContent = listContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public List<String> getListContent() {
        return listContent;
    }

    public void setListContent(List<String> listContent) {
        this.listContent = listContent;
    }
}
