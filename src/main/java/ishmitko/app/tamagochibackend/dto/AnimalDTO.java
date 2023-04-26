package ishmitko.app.tamagochibackend.dto;

public class AnimalDTO {

    public AnimalDTO() {}

    public AnimalDTO(String uuid, String name, Integer age, double health, double satiation) {
        this.name = name;
        this.uuid = uuid;
        this.age = age;
        this.health = health;
        this.satiation = satiation;
    }

    private String name;
    private String uuid;
    public Integer age;
    private double health;
    private double satiation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getSatiation() {
        return satiation;
    }

    public void setSatiation(double satiation) {
        this.satiation = satiation;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", age=" + age +
                ", health=" + health +
                ", satiation=" + satiation +
                '}';
    }
}
