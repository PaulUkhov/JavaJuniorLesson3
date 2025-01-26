package ru.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
// Можно было бы имплементироваться от интерфейса Serializable
public class Student implements Externalizable { // Предлагает готовые методы для Сереализации
    @JsonProperty("name")
    String name;
    @JsonProperty("age")
    int age;
    @JsonIgnore
    transient double GPA; // Не позволяет прочитать модификатор поля,но если мы используем Externalizable, то нужно удалять поле из toString в ручную
public Student(){

}
    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }



    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(name);
        objectOutput.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
      name = (String) objectInput.readObject();
      age = objectInput.readInt();
    }
}
