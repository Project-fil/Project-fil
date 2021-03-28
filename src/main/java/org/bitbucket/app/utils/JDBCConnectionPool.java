package org.bitbucket.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JDBCConnectionPool {

    private long timeout;

    private String url, user, password;

    private AtomicInteger count;

    private Map<Connection, ConnectionEntry> pool = new HashMap<>();

    public JDBCConnectionPool(long timeout, String driver, String url, String user, String password){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        this.timeout = timeout;
        this.url = url;
        this.user = user;
        this.password = password;
        this.count = new AtomicInteger(0);
    }

    private Connection connect(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void expire(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(Connection connection) {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Connection connection() {
        long now = System.currentTimeMillis();
        for (Connection connection : this.pool.keySet()) {
            ConnectionEntry entry = this.pool.get(connection);
            if ((now - entry.createAt) > this.timeout) {
                expire(connection);
                this.pool.remove(connection);
            } else {
                if (validate(connection)) {
                    if (entry.status == ConnectionStatus.off) {
                        entry.status = ConnectionStatus.on;
                        return connection;
                    }
                } else {
                    expire(connection);
                    this.pool.remove(connection);
                }
            }
        }
        Connection connection = connect();
        ConnectionEntry entry = new ConnectionEntry(this.count.incrementAndGet(), ConnectionStatus.on, now);
        this.pool.put(connection, entry);

        return connection;
    }

    public void parking(Connection connection) {
        long now = System.currentTimeMillis();
        ConnectionEntry entry = this.pool.get(connection);
        entry.status = ConnectionStatus.off;
        entry.createAt = now;
    }

    private class ConnectionEntry{

        private int number;

        private ConnectionStatus status;

        private long createAt;

        public ConnectionEntry(int number, ConnectionStatus status, long createAt) {
            this.number = number;
            this.status = status;
            this.createAt = createAt;
        }

        @Override
        public String toString() {
            return "Connection number=" + number;
        }
    }

    private enum ConnectionStatus {
        on, off
    }

}