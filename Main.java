import java.util.LinkedList;
import java.util.Queue;

public class Main implements QueueBehaviour, MarketBehaviour {
    private Queue<String> queue;
    private Queue<String> orders;
    private Queue<String> completedOrders;

    public Main() {
        this.queue = new LinkedList<>();
        this.orders = new LinkedList<>();
        this.completedOrders = new LinkedList<>();
    }

    // Методы интерфейса QueueBehaviour
    @Override
    public void enterQueue(String person) {
        queue.add(person);
        System.out.println(person + " entered the queue.");
    }

    @Override
    public void leaveQueue() {
        if (!queue.isEmpty()) {
            String person = queue.poll();
            System.out.println(person + " left the queue.");
        } else {
            System.out.println("Queue is empty, no one to leave.");
        }
    }

    @Override
    public void serveNext() {
        if (!queue.isEmpty()) {
            String person = queue.peek();
            System.out.println(person + " is being served.");
            completedOrders.add(orders.poll());
        } else {
            System.out.println("Queue is empty, no one to serve.");
        }
    }

    @Override
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    // Методы интерфейса MarketBehaviour
    @Override
    public void acceptOrder(String order) {
        orders.add(order);
        System.out.println("Order accepted: " + order);
    }

    @Override
    public void deliverOrder() {
        if (!completedOrders.isEmpty()) {
            String order = completedOrders.poll();
            System.out.println("Order delivered: " + order);
        } else {
            System.out.println("No completed orders to deliver.");
        }
    }

    @Override
    public void update() {
        System.out.println("Updating market...");
        serveNext();
        deliverOrder();
    }

    public static void main(String[] args) {
        Main market = new Main();

        // Добавляем людей в очередь
        market.enterQueue("Alice");
        market.enterQueue("Bob");
        market.enterQueue("Charlie");

        // Принимаем заказы
        market.acceptOrder("Order1");
        market.acceptOrder("Order2");
        market.acceptOrder("Order3");

        // Обновляем состояние магазина
        market.update(); // Обслуживает Alice и доставляет Order1
        market.update(); // Обслуживает Bob и доставляет Order2
        market.update(); // Обслуживает Charlie и доставляет Order3

        // Проверяем состояние очереди и доставленных заказов
        market.leaveQueue(); // Пытается убрать человека из пустой очереди
        market.deliverOrder(); // Пытается доставить заказ из пустой очереди
    }
}
