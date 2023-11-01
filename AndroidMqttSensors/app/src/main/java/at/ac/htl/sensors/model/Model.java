package at.ac.htl.sensors.model;

/** Our single state of truth model.
 * We use the Three Principles.
 * - Single source of truth
 * - State is read-only
 * - Changes are made with pure functions
 *
 * Everything is public and every part of the application only gets a copy of our application state.
 * So any part of the application is free to change anything it likes in its own local copy.
 * @see <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">Three Principles</a>
 */
public class Model {
    public record LocationData(double longitude, double latitude, Boolean valid) {
        public LocationData() {
            this(0, 0, false);
        }
    }
    public record LocationPermissions (boolean fine, boolean coarse) {
        public LocationPermissions() {
            this(false, false);
        }
    }
    public boolean locationServicesStarted = false;
    public LocationData locationData = new LocationData();
    public LocationPermissions permissions = new LocationPermissions();
}
