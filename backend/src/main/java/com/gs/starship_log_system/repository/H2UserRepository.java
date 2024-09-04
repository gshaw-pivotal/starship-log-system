package com.gs.starship_log_system.repository;

import com.gs.starship_log_system.model.ImportUser;
import com.gs.starship_log_system.model.LoginRequest;
import com.gs.starship_log_system.model.User;

import java.sql.*;
import java.util.UUID;

public class H2UserRepository implements UserRepository {

    private final String dbUrl;

    private final String dbUser;

    private final String dbPass;

    private final String queryLogin = "select * from users where username = '%s' and password = '%s' and rank = '%s' and name = '%s'";

    private final String saveUser = "insert into users (id, username, password, rank, name) values ('%s', '%s', '%s', '%s', '%s')";

    private final String getUserByUsername = "select * from users where username = '%s'";

    public H2UserRepository(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    @Override
    public boolean validCred(LoginRequest loginRequest) {
        try {
            Connection conn = getConnection();

            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        String.format(
                                queryLogin,
                                loginRequest.getUsername(),
                                loginRequest.getPassword(),
                                loginRequest.getRank(),
                                loginRequest.getName()
                        )
                );

                boolean match = rs.next();
                conn.close();
                return match;
            }

            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void save(ImportUser user) {
        try {
            Connection conn = getConnection();

            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(
                        String.format(
                                saveUser,
                                user.getId().toString(),
                                user.getUsername(),
                                user.getPassword(),
                                user.getRank(),
                                user.getName()
                        )
                );
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("SQLException in H2UserRepository.save: " + e.getMessage());
        }
    }

    @Override
    public User getUser(String username) {
        try {
            Connection conn = getConnection();
            User user = null;

            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                        String.format(
                                getUserByUsername,
                                username
                        )
                );

                if (rs.next()) {
                    user = User.builder()
                            .id(UUID.fromString(rs.getString(rs.findColumn("id"))))
                            .name(rs.getString(rs.findColumn("name")))
                            .rank(rs.getString(rs.findColumn("rank")))
                            .username(username)
                            .build();
                }

                conn.close();
                return user;
            }
        } catch (SQLException e) {
            return null;
        }

        return null;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            return null;
        }
    }
}
