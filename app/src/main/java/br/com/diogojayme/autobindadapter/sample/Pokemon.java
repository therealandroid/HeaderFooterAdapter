package br.com.diogojayme.autobindadapter.sample;

/**
 * Created by diogojayme on 8/28/16.
 */
public class Pokemon {
    private long id;
    private String name;
    private int image;

    public Pokemon(String name, int image) {
        this.id = System.currentTimeMillis();
        this.name =  name == null ? "unknown" : name;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = System.nanoTime();
    }

    public String getName() {
        return name == null ? "unknown" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
