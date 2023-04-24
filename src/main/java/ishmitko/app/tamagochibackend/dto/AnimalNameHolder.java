package ishmitko.app.tamagochibackend.dto;

public class AnimalNameHolder {

    private String name;

    public AnimalNameHolder() {}

    public AnimalNameHolder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AnimalNameHolder{" +
                "name='" + name + '\'' +
                '}';
    }
}
