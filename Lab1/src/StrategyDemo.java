import java.util.Scanner;

public class StrategyDemo {
    interface MoveStrategy {
        void move(String from, String to);
    }

    static final class Hero {
        private MoveStrategy moveStrategy;

        public Hero() { }

        public Hero(MoveStrategy moveStrategy) {
            this.moveStrategy = moveStrategy;
        }

        public void setMoveStrategy(MoveStrategy moveStrategy) {
            this.moveStrategy = moveStrategy;
        }

        public void move(String from, String to) {
            if (moveStrategy != null) {
                moveStrategy.move(from, to);
            }
            else {
                System.out.println("Способ перемещения героя не установлен!");
            }
        }
    }

    static final class WalkStrategy implements MoveStrategy {
        @Override
        public void move(String from, String to) {
            System.out.println("Иду пешком из " + from + " в " + to);
        }
    }

    static final class RunStrategy implements MoveStrategy {
        @Override
        public void move(String from, String to) {
            System.out.println("Бегу из " + from + " в " + to);
        }
    }

    static final class HorseRideStrategy implements MoveStrategy {
        @Override
        public void move(String from, String to) {
            System.out.println("Еду верхом на лошади из " + from + " в " + to);
        }
    }

    static final class FlyStrategy implements MoveStrategy {
        @Override
        public void move(String from, String to) {
            System.out.println("Лечу из " + from + " в " + to);
        }
    }

    static final class SwimStrategy implements MoveStrategy {
        @Override
        public void move(String from, String to) {
            System.out.println("Плыву по реке из " + from + " в " + to);
        }
    }

    public static void main(String[] args) {
        Hero heroZero = new Hero();
        heroZero.move("Темерия", "Тусент");

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

            Scanner scanner = new Scanner(System.in);
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }

            if (choice == 0) {
                System.out.println("Выход из игры...");
                break;
            }

            switch (choice) {
                case 1:
                    hero.setMoveStrategy(new WalkStrategy());
                    break;
                case 2:
                    hero.setMoveStrategy(new RunStrategy());
                    break;
                case 3:
                    hero.setMoveStrategy(new HorseRideStrategy());
                    break;
                case 4:
                    hero.setMoveStrategy(new FlyStrategy());
                    break;
                case 5:
                    hero.setMoveStrategy(new SwimStrategy());
                    break;
                default:
                    System.out.println("Вариант отсутствует");
                    continue;
            }

            System.out.print("Введите пункт назначения: ");
            to = scanner.next();
            hero.move(from, to);
            from = to;
        }
    }
}