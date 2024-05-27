public interface MarketBehaviour {
    void acceptOrder(String order);
    void deliverOrder();
    void update();
}
