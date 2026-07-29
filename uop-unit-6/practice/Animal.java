public class Animal {
  protected String name;
  
  public Animal(String name) {
    this.name = name;
  }

  public void makeSound() {
    System.out.println(name + " makes a generic animal sound.");
  }

  public void feed() {
    System.out.println(name + " is fed the standard animal food.");
  }

  public void feed(String foodType) {
    System.out.println(name + " is fed " + foodType + ".");
  }
}
