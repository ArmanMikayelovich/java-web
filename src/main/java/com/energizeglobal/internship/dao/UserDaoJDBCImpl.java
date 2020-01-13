package com.energizeglobal.internship.dao;

import com.energizeglobal.internship.model.LoginRequest;
import com.energizeglobal.internship.model.RegistrationRequest;
import com.energizeglobal.internship.model.User;
import com.energizeglobal.internship.util.DateConverter;
import com.energizeglobal.internship.util.exception.InvalidCredentialsException;
import com.energizeglobal.internship.util.exception.ServerSideException;
import com.energizeglobal.internship.util.exception.UsernameAlreadyExists;
import com.energizeglobal.internship.util.exception.UsernameNotFountException;
import lombok.Cleanup;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/web?createDatabaseIfNotExist=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String REGISTER_QUERY = "INSERT INTO users " +
            "(username, password, birthday, email, country) " +
            "values (?,?,?,?,?)";
    private static final String USERNAME_CHECK_QUERY = "SELECT username from users where username=?";
    private static final String LOGIN_QUERY = "SELECT username,birthday,email,country " +
            "FROM users " +
            "WHERE username=? AND password=?";
    private static final String IS_ADMIN_QUERY = "SELECT isAdmin FROM users WHERE username=?";

    private static final String CHANGE_ADMIN_QUERY = "UPDATE users SET isAdmin = ? WHERE username=?";

    private static final String SELECT_ALL_QUERY = "SELECT username, birthday, email, country FROM users";

    private static final String DELETE_QUERY = "DELETE FROM users WHERE username = ?";

    private static final String GET_PASSWORD = "SELECT password FROM users WHERE username = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password =? WHERE username=?";


    @Override
    public boolean isUsernameExists(String username) {
        try (final Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            final PreparedStatement preparedStatement = connection.prepareStatement(USERNAME_CHECK_QUERY);
            preparedStatement.setString(1, username);
            @Cleanup final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
            return true;

        } catch (SQLException ex) {
            throw new ServerSideException();
        }
    }

    @Override
    public void register(RegistrationRequest registrationRequest) {
        if (isUsernameExists(registrationRequest.getUsername())) {
            throw new UsernameAlreadyExists();
        }

        try (final Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             final PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_QUERY);) {
            preparedStatement.setString(1, registrationRequest.getUsername());
            preparedStatement.setString(2, registrationRequest.getPassword());
            preparedStatement.setDate(3, DateConverter
                    .convertLocalDateToSqlDate(registrationRequest.getBirthday()));
            preparedStatement.setString(4, registrationRequest.getCountry());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ServerSideException();
        }
    }

    @Override
    public User login(LoginRequest loginRequest) {
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_QUERY)) {
            preparedStatement.setString(1, loginRequest.getUsername());
            preparedStatement.setString(2, loginRequest.getPassword());
            @Cleanup final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String username = resultSet.getString("username");
                final LocalDate birthday = DateConverter.convertDateToLocalDate(resultSet.getDate("birthday"));
                final String email = resultSet.getString("email");
                final String country = resultSet.getString("country");
                return new User(username, birthday, email, country);
            }
            throw new InvalidCredentialsException();
        } catch (SQLException e) {
            throw new ServerSideException();
        }
    }

    @Override
    public Boolean isAdmin(String username) {
        if (!isUsernameExists(username)) {
            throw new UsernameNotFountException();
        }
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(IS_ADMIN_QUERY)) {
            preparedStatement.setString(1, username);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getBoolean("isAdmin");
        } catch (SQLException e) {
            throw new ServerSideException();
        }
    }

    @Override
    public void changeAdminState(String username, boolean adminState) {
        if (!isUsernameExists(username)) {
            throw new UsernameNotFountException();
        }
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ADMIN_QUERY)) {
            preparedStatement.setBoolean(1, adminState);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ServerSideException();
        }
    }

    @Override
    public void updatePassword(LoginRequest userCredentials, String newPassword) {

    }

    @Override
    public void updateUserInfo(User user) {

    }


    @Override
    public List<User> findAll() {
        final List<User> users = new ArrayList<>();
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final String username = resultSet.getString("username");
                final Date birthday = resultSet.getDate("birthday");
                final String email = resultSet.getString("email");
                final String country = resultSet.getString("country");
                users.add(new User(username, birthday.toLocalDate(), email, country));
            }
        } catch (SQLException e) {
            throw new ServerSideException();
        }
        return users;
    }

    @Override
    public void remove(String username) {
        if (!isUsernameExists(username)) {
            throw new UsernameNotFountException();
        }
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ServerSideException();
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}
