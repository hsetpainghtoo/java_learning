
public class Dog extends Animal implements Trainable {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says Woof!");
    }

    @Override
    public void doTrick() {
        System.out.println(name + " rolls over!");
    }
}