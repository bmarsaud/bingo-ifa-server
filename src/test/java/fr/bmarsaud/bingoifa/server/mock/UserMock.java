package fr.bmarsaud.bingoifa.server.mock;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.bmarsaud.bingoifa.server.entity.User;

public class UserMock {
    public static final List<User> users = List.of(
      new User(1, "bmarsaud", "bmarsaud", null, Timestamp.valueOf(LocalDateTime.of(2019, 2, 9, 20, 17, 9)), new ArrayList<>(HistoryLineMock.history))
    );

    public static final User toCreateUser = new User("toCreate", "toCreate", GridMock.grids.get(0), null, new ArrayList<>());

    public static final User nonExistentUser = new User();
}
