public class Main {
    public static void main(String[] args) {
        ActionCounter counter = new ActionCounter();

        counter.call(1);
        counter.call(2);
        counter.call(2);
        System.out.println(counter.getActions(4));

        counter.call(300);
        System.out.println(counter.getActions(300));
        System.out.println(counter.getActions(301));
    }
}
