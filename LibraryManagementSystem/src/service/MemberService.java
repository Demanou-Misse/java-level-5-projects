package service;

import dao.implement.MemberDAOImpl;
import model.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    MemberDAOImpl memberDAOImpl = new MemberDAOImpl();
    Scanner input = new Scanner(System.in);

    // Add Member
    public void addMember() {
        String name;
        String email;

        System.out.println("\n--- 🙍‍♂️ Add New Member ---");

        while (true) {
            System.out.print("Name: ");
            name = input.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("❌ Your name cannot be empty.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Email: ");
            email = input.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("❌ Your email cannot be empty.");
                continue;
            }
            break;
        }

        Member member = new Member(name, email);
        try {
            memberDAOImpl.addMember(member);
            System.out.println("✅ Member registered successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Show All Member
    public void showAllMember() {
        System.out.println("\n🙍‍♂️ --- All Members ---\n");

        try {
            List<Member> members = memberDAOImpl.allMembers();
            System.out.println("*".repeat(40));
            for (Member member : members) {
                System.out.println("    🔹 ID: " + member.getId_member() + " | NAME: " + member.getName() + " | EMAIL: " + member.getEmail());
            }
            System.out.println("*".repeat(40));
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

}
