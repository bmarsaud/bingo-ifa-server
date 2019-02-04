package fr.bmarsaud.bingoifa.server.mock;

import java.util.ArrayList;
import java.util.List;

import fr.bmarsaud.bingoifa.server.entity.User;

public class UserMock {
    public static final List<User> users = List.of(
      new User(1, "bmarsaud", "bmarsaud", null, new ArrayList<>(HistoryLineMock.history))
    );

    public static final User toCreateUser = new User("toCreate", "toCreate", GridMock.grids.get(0), new ArrayList<>());

    public static final User nonExistentUser = new User();
}
