package diogojme.github.com.adapterbuilder.sample;

/**
 * Created by diogojayme on 1/9/17.
 */

public class SampleModel {
    private String name;
    private String image;

    public SampleModel(String name, String image){
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
