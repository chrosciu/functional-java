package info.solidsoft.java8.scoring;

class Credit {
    private final Person person;
    private final int howLong;
    private double currentScore;

    public Credit(Person person, int howLong, double initialScore) {
        this.person = person;
        this.howLong = howLong;
        this.currentScore = initialScore;
    }

    public Person getPerson() {
        return person;
    }

    public int getHowLong() {
        return howLong;
    }

    public double getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(double currentScore) {
        this.currentScore = currentScore;
    }
}
