public interface QueueBehaviour {
    void enterQueue(String person);
    void leaveQueue();
    void serveNext();
    boolean isQueueEmpty();
}
