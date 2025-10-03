//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package app;

import java.util.Scanner;
import service.*;

public class Main {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        BookService bookService = new BookService();
        LoanService loanService = new LoanService();
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("\n" + "=".repeat(40));
        System.out.println("    📚 Library Management System");
        System.out.println("=".repeat(40)+ "\n") ;

        do {

            System.out.println("\n🧾 Menu:\n");
            System.out.println("    1️⃣ Add Book");
            System.out.println("    2️⃣ Add Member");
            System.out.println("    3️⃣ Borrow Book");
            System.out.println("    4️⃣ Return Book");
            System.out.println("    5️⃣ Search Book");
            System.out.println("    6️⃣ Show All Books");
            System.out.println("    7️⃣ Show All Members");
            System.out.println("    8️⃣ Show Loans Report");
            System.out.println("    9️⃣Exit");
            System.out.println("-".repeat(40) + "\n");

            // User's Choice
            while (true) {
                System.out.print("> Enter your choice: ");
                String userInput = input.nextLine().trim();
                if (userInput.isEmpty()) {
                    System.out.println("❌ Your choice cannot be empty.");
                    continue;
                }

                try {
                    choice = Integer.parseInt(userInput);
                    if (choice >= 1 && choice <= 9) break;
                    System.out.println("❌ Invalid input. Enter a number between (1-9).");
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid input");
                }
            }

            switch (choice) {

                case 1:
                    bookService.addBook();
                    break;
                case 2:
                    memberService.addMember();
                    break;
                case 3:
                    loanService.borrowBook();
                    break;
                case 4:
                    loanService.returnBook();
                    break;
                case 5:
                    bookService.searchBook();
                    break;
                case 6:
                    bookService.allBookAvailable();
                    break;
                case 7:
                    memberService.showAllMember();
                    break;
                case 8:
                    loanService.showLoansReport();
                    break;
                case 9:
                    System.out.println("\n👋 Exiting... Goodbye!");
                    break;
            }

        } while (choice != 9);

        input.close();

    }
}