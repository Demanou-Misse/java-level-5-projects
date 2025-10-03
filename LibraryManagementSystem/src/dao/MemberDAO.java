package dao;

import model.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO {

    void addMember (Member member) throws SQLException;
    List<Member> allMembers() throws SQLException;

}
