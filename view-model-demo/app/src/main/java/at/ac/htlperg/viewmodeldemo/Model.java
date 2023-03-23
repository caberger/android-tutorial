package at.ac.htlperg.viewmodeldemo;

/*
public record Model (int count) {
}
*/

public class Model {
        private final int count;
        public Model(int count) {
                this.count = count;
        }

        public int getCount() {
                return count;
        }
}
