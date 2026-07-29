public class Main {
    public static void main(String[] args) {
        Animal[] animals = new Animal[3];
        animals[0] = new Dog("Rex");
        animals[1] = new Cat("Whiskers");
        animals[2] = new Dog("Buddy");

        for (Animal a : animals) {
            a.makeSound();
        }

        Animal generic = new Animal("SomeAnimal");
        generic.feed();
        generic.feed("kibble");


        Dog dog = new Dog("Max");
        dog.doTrick();

        // Animal animalDog = new Dog("Charlie");
        // animalDog.doTrick(); // This line will cause a compile-time error because the reference type is Animal, which does not have the doTrick() method.
    }
}
