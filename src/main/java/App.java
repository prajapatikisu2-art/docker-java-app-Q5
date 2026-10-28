public class App {
    public String greet() {
        return "Hello from Docker Java App!";
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.greet());
    }
}