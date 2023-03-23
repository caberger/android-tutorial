package at.ac.htlperg.viewmodeldemo;

/*
public record Model (int count) {
}
*/

import java.io.Serializable;

public class Model implements Serializable {
    private final int count;
    public Model(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
