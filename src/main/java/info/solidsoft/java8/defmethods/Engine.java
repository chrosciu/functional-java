package info.solidsoft.java8.defmethods;

public interface Engine {

    /**
     * Do not TOUCH!
     */
    default String start() {
        return "Engine";
    }

}

