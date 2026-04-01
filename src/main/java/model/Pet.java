package model;

import lombok.Data;
import java.util.List;

@Data
public class Pet {

    private  long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tags> tags;
    private String status;

}
