package br.com.diogojayme.autobindadapter.sample;

import br.com.diogojayme.autobindadapter.library.Item;

/**
 * Created by diogojayme on 8/29/16.
 */
public class HeaderItem implements Item {

    private String title;

    public HeaderItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
