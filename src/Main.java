import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Modifier;
import java.util.Arrays;

class Student {
    private String name;
    private int age;
    private double rating;

    public Student(String name, int age, double rating) {
        this.name = name;
        this.age = age;
        this.rating = rating;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

class Helper {
    private static void outputParametersInfo(Parameter[] params) {
        System.out.print("(");
        int len = params.length;
        if (len > 0) {
            for (int i = 0; i < len - 1; i++) {
                System.out.print(params[i].getType().getTypeName().concat(" ").concat(params[i].getName()).concat(", "));
            }
            System.out.print(params[len - 1].getType().getTypeName().concat(" ").concat(params[len - 1].getName()));
        }
        System.out.print(")");
    }

    static void outputcClassInfo(Class<?> cl) throws ClassNotFoundException {
        System.out.println();
        System.out.println("Конструктори класу: ");
        System.out.println();
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 0) {
            for (Constructor<?> constructor : constructors) {
                System.out.print(Modifier.toString(constructor.getModifiers()).concat(" " ).concat(constructor.getName()));
                Parameter[] parameters = constructor.getParameters();
                outputParametersInfo(parameters);
                System.out.println();
            }
        } else {
            System.out.println("У класу відсутні конструктори");
        }

        // Поля класу
        System.out.println();
        System.out.println();
        System.out.println("Поля класу: ");
        System.out.println();
        Field[] fields = cl.getDeclaredFields();
        Arrays.sort(fields, (f1, f2)-> f1.getType().getTypeName().compareTo(f2.getType().getTypeName()));

        if (fields.length > 0) {
            for  (Field field : fields) {
                System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType().getTypeName() + " " + field.getName());
            }
        } else {
            System.out.println("У клас відсутні поля");
        }

        System.out.println();
        System.out.println();
        System.out.println("Методи класу: ");
        System.out.println();
        Method[] methods = cl.getDeclaredMethods();
        Arrays.sort(methods, (m1, m2)-> m1.getName().compareTo(m2.getName()));

        if (methods.length > 0) {
            for (Method method : methods) {
                System.out.print(Modifier.toString(method.getModifiers()).concat( " ").concat(method.getReturnType().getTypeName()).concat(" ").concat( method.getName()));
                Parameter[] parameters = method.getParameters();
                outputParametersInfo(parameters);
                System.out.println();
            }
        } else {
            System.out.println("У класу відсутні Методи");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Class<?> cl;
        try {
            cl = Class.forName("Student");
            Helper.outputcClassInfo(cl);
        } catch (ClassNotFoundException e) {
            System.out.println();
            System.out.println("Помилка: інформації про клас \"Student\" не знайдено!");
        }
    }
}