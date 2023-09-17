class AnimalThread extends Thread {
    private String name;
    private int priority;
    private volatile int dynamicPriority; // Динамический приоритет потока

    public AnimalThread(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.dynamicPriority = priority; // Устанавливаем начальный динамический приоритет
    }

    // Метод для изменения динамического приоритета потока
    public void setThreadPriority(int priority) {
        this.dynamicPriority = priority;
        setPriority(priority);
    }

    @Override
    public void run() {
        int distance = 0;
        while (distance < 100) {
            try {
                sleep(100); // добавляем небольшую задержку, чтобы увидеть изменение приоритета
                distance += dynamicPriority; // увеличиваем пройденное расстояние на динамический приоритет потока
                System.out.println(name + " преодолел " + distance + " метров.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " финишировал!");
    }
}

public class RabbitAndTurtle {
    public static void main(String[] args) {
        AnimalThread rabbitThread = new AnimalThread("Кролик", 2);
        AnimalThread turtleThread = new AnimalThread("Черепаха", 1);

        rabbitThread.start();
        turtleThread.start();
        try {
            Thread.sleep(4000); // Задержка перед изменением приоритетов
            turtleThread.setThreadPriority(3); // Устанавливаем новый приоритет черепахе
            rabbitThread.setThreadPriority(1); // Устанавливаем новый приоритет кролику
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}