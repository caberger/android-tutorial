package at.ac.htlperg.viewmodeldemo;

import java.util.List;

/*
public record Model (int count){
}
*/
public class Model {
        private final List<User> users;

        public Model(List<User> users) {
                this.users = users;
        }
        public Model() {
                users = List.of();
        }

        public List<User> getUsers() {
                return users;
        }
}
