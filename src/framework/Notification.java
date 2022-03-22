package framework;

public interface Notification extends Observer {


    abstract void update(Subject subject, Object arg);

    default void sendNotification() {
        System.out.println("Notification Sent! ");
    }
}
