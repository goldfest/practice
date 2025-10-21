public class Main {
    public static void main(String[] args) {

        BankAccount account1 = new BankAccount("Иван Лобашов");
        BankAccount account2 = new BankAccount("Степан Афанасьев");

        System.out.println("Создание счетов");
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\nПополнение счета");
        boolean depositResult = account1.deposit(1000);
        System.out.println("Пополнение на 1000: " + depositResult);
        System.out.println("Баланс account1: " + account1.getBalance());

        System.out.println("\nСнятие денег");
        boolean withdrawResult = account1.withdraw(300);
        System.out.println("Снятие 300: " + withdrawResult);
        System.out.println("Баланс account1: " + account1.getBalance());

        System.out.println("\nПеревод денег");
        boolean transferResult = account1.transfer(account2, 200);
        System.out.println("Перевод 200 на account2: " + transferResult);
        System.out.println("Баланс account1: " + account1.getBalance());
        System.out.println("Баланс account2: " + account2.getBalance());

        System.out.println("\nТест некорректных операций");
        //пробуем снять больше
        boolean invalidWithdraw = account1.withdraw(1000);
        System.out.println("Снятие 1000 (недостаточно средств): " + invalidWithdraw);

        account2.setBlocked(true);
        boolean transferToBlocked = account1.transfer(account2, 100);
        System.out.println("Перевод на заблокированный счет: " + transferToBlocked);

        System.out.println("\nТест equals");
        BankAccount account3 = new BankAccount("Иван Лобашов");
        System.out.println("account1 equals account2: " + account1.equals(account2));
        System.out.println("account1 equals account3: " + account1.equals(account3));

    }
}