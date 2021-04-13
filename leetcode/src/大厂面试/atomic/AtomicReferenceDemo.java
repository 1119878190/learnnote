package 大厂面试.atomic;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String userName;
    int age;

    public User() {
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User user1 = new User("z3",22);
        User user2 = new User("li4",23);

        AtomicReference<User> atomicReference = new AtomicReference<>();

        atomicReference.set(user1);

        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user1,user2)+"\t"+atomicReference.get().toString());
    }
}
