package ru.example;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

//1. Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
// Обеспечьте поддержку сериализации для этого класса.
//Создайте объект класса Student и инициализируйте его данными.
//Сериализуйте этот объект в файл.
//Десериализуйте объект обратно в программу из файла.
//Выведите все поля объекта, включая GPA, и обсудите.
//почему значение GPA не было сохранено/восстановлено.
//2. *** Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
public class Main {
    public static void main(String[] args) throws IOException {
        // Обычная Сереализация
        Student student = new Student("Pavel", 18, 56);
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("student.txt"))) {
            student.writeExternal(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Обычная Десериализация
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream("student.txt"))) {
            Student desiralizationStudent = new Student(null, 0, 0);
            desiralizationStudent.readExternal(stream);
            System.out.println(desiralizationStudent);
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Json Сереализация
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("student.json"),student);
            System.out.println("file successfully recorded");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Json Десериализация
        try {
          Student studentJson = objectMapper.readValue(new File("student.json"),Student.class);
            System.out.println("Десериализуем файл " + studentJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // XML Сереализация
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File("student.xml"),student);
            System.out.println("Файл успешно записан" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        // XML Десериализация
        try {
            Student studentXml = xmlMapper.readValue(new File("student.xml"),Student.class);
            System.out.println(studentXml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
