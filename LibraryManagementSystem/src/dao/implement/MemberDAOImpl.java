package dao.implement;

import dao.MemberDAO;
import model.Member;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void addMember (Member member) throws SQLException {
        String sql = "INSERT INTO members (name, email) VALUES (?, ?)";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Member> allMembers() throws SQLException {
        String sql = "SELECT * FROM members";
        List<Member> members = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Member member = new Member(rs.getInt("id_member"), rs.getString("name"), rs.getString("email"));
                members.add(member);
            }
        }
        return members;
    }

}
