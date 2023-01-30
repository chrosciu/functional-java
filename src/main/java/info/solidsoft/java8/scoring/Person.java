package info.solidsoft.java8.scoring;

class Person {
    private final int age;
    private final int bmi;
    private final String sex;

    public Person(int age, int bmi, String sex) {
        this.age = age;
        this.bmi = bmi;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public int getBmi() {
        return bmi;
    }

    public String getSex() {
        return sex;
    }

    public boolean isMale(){
        return "MALE".equals(sex);
    }

    public boolean isFemale(){
        return "FEMALE".equals(sex);
    }
}
