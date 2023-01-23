package info.solidsoft.java8.defmethods;

public interface Lifecycle {

    /**
     * Do not TOUCH!
     */
    default String start() {
        return "Lifecycle";
    }

}
