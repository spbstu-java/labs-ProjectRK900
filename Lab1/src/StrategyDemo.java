import java.util.Scanner;

public class StrategyDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hero heroZero = new Hero();
        heroZero.Move("Темерия", "Тусент");

        Hero hero = new Hero(new WalkStrategy());
        String from = "Корусант";
        String to;

        while (true) {
            System.out.println("\nВыберите способ перемещения:");
            System.out.println("1 - Пешком");
            System.out.println("2 - Бег");
            System.out.println("3 - На лошади");
            System.out.println("4 - Лететь на корабле");
            System.out.println("5 - Плыть");
            System.out.println("0 - Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            if (choice == 0) {
                System.out.println("Выход из игры...");
                break;
            }

            System.out.print("Введите пункт назначения: ");
            to = scanner.next();

            switch (choice) {
                case 1:
                    hero.SetMoveStrategy(new WalkStrategy());
                    break;
                case 2:
                    hero.SetMoveStrategy(new RunStrategy());
                    break;
                case 3:
                    hero.SetMoveStrategy(new HorseRideStrategy());
                    break;
                case 4:
                    hero.SetMoveStrategy(new FlyStrategy());
                    break;
                case 5:
                    hero.SetMoveStrategy(new SwimStrategy());
                    break;
                default:
                    System.out.println("Вариант отсутствует");
                    continue;
            }

            hero.Move(from, to);
            from = to;
        }

        scanner.close();
    }
}

interface MoveStrategy {
    void Move(String from, String to);
}

class Hero {
    private MoveStrategy moveStrategy;

    public Hero() { }

    public Hero(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void SetMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void Move(String from, String to) {
        if (moveStrategy != null) {
            moveStrategy.Move(from, to);
        }
        else {
            System.out.println("Способ перемещения героя не установлен!");
        }
    }
}

class WalkStrategy implements MoveStrategy {
    @Override
    public void Move(String from, String to) {
        System.out.println("Иду пешком из " + from + " в " + to);
    }
}

class RunStrategy implements MoveStrategy {
    @Override
    public void Move(String from, String to) {
        System.out.println("Бегу из " + from + " в " + to);
    }
}

class HorseRideStrategy implements MoveStrategy {
    @Override
    public void Move(String from, String to) {
        System.out.println("Еду верхом на лошади из " + from + " в " + to);
    }
}

class FlyStrategy implements MoveStrategy {
    @Override
    public void Move(String from, String to) {
        System.out.println("Лечу из " + from + " в " + to);
    }
}

class SwimStrategy implements MoveStrategy {
    @Override
    public void Move(String from, String to) {
        System.out.println("Плыву по реке из " + from + " в " + to);
    }
}