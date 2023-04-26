package ishmitko.app.tamagochibackend.model;

import jakarta.persistence.*;

@Entity
@Table(name="Animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="uuid")
    private String uuid;
    @Column(name="name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "health")
    private double health;
    @Column(name = "satiation")
    private double satiation;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
