package net.deechael.genshin.storage;

import cc.carm.lib.easysql.EasySQL;
import cc.carm.lib.easysql.api.SQLManager;
import cc.carm.lib.easysql.api.SQLQuery;
import cc.carm.lib.easysql.hikari.HikariConfig;
import net.deechael.genshin.impl.GenshinProfileImpl;
import net.deechael.genshin.GsCorePlugin;

import static net.deechael.genshin.GsCorePlugin.logger;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public final class SqliteStorage {

    private final HikariConfig hikariConfig = new HikariConfig();
    private SQLManager sqlManager;
    private Connection connection;

    public SqliteStorage(File file) {
        logger().debug("Initializing sqlite storage");
        if (!file.exists()) {
            logger().debug("Database file not exists, trying to create");
            File parent = file.getParentFile();
            if (parent != null) {
                logger().debug("Database file has parent directory");
                if (!parent.exists()) {
                    logger().debug("The parent directory of the database file not exists, trying to create");
                    if (parent.mkdirs())
                        logger().warn("Failed to generate the parent directory of the database file");
                    else
                        logger().debug("Created the parent directory of the database file successfully");
                }
            }
            try {
                if (!file.createNewFile())
                    logger().warn("Failed to create database file");
                else
                    logger().debug("Created the database file successfully");
            } catch (IOException e) {
                logger().error("Failed to create database file", e);
            }
        }
        hikariConfig.setDriverClassName("org.sqlite.JDBC");
        logger().debug("Setting database jdbc to Sqlite");
        hikariConfig.setJdbcUrl("jdbc:sqlite:" + file.getPath());
        logger().debug("Setting database jdbc url to \"" + "jdbc:sqlite:" + file.getPath() + "\"");

        this.sqlManager = EasySQL.createManager(hikariConfig);
        logger().debug("Creating sql manager");
        try {
            this.connection = sqlManager.getConnection();
        } catch (SQLException e) {
            logger().error("Failed to connect to the database", e);
        }
        try {
            logger().debug("Connecting to database");
            logger().debug("Starting to initialize tables");
            logger().debug("Checking profiles table");

            // To store players' data which is not related to genshin
            logger().debug("Checking player data table");
            if (hasTable("gscraft_player_data"))
                logger().debug("Player data table exists, skip");
            else {
                logger().debug("Player data table not exists, trying to create");
                sqlManager.createTable("gscraft_player_data")
                        .addColumn("UUID", "TEXT")
                        .addColumn("FIRST_JOIN", "TEXT")
                        .addColumn("LAST_LOGIN", "TEXT")
                        .setTableSettings("")
                        .build()
                        .execute();
                logger().debug("Player data table created");
            }

            // To store player's general datas
            if (hasTable("genshin_profiles"))
                logger().debug("Profiles table exists, skip");
            else {
                logger().debug("Profiles table not exists, trying to create");
                sqlManager.createTable("genshin_profiles")
                        .addColumn("UID", "INTEGER PRIMARY KEY AUTOINCREMENT")
                        .addColumn("UUID", "TEXT")
                        .addColumn("ADVENTURE_EXP", "INT")
                        .addColumn("WORLD_LEVEL_DECREASE", "INT")
                        .addColumn("MORA", "BIGINT")
                        .addColumn("PRIMOGEM", "INT")
                        .addColumn("GENESIS_CRYSTAL", "INT")
                        .addColumn("ASCENSION_QUESTS", "TEXT")
                        .addColumn("ORIGINAL_RESIN", "INT")
                        .setTableSettings("")
                        .build()
                        .execute();
                logger().debug("Profiles table created");
                sqlManager.createUpdate("sqlite_sequence")
                        .setColumnValues("seq", "100000000")
                        .addCondition("NAME", "genshin_profiles")
                        .build()
                        .execute();
                logger().debug("Setting the start value of uid to 100000000");
            }

            // To store the teleport waypoints created by plugins and the server admins
            logger().debug("Checking waypoints table");
            if (hasTable("genshin_waypoints"))
                logger().debug("Waypoints table exists, skip");
            else {
                logger().debug("Waypoints table not exists, trying to create");
                sqlManager.createTable("genshin_waypoints")
                        .addColumn("WAYPOINT_ID", "INTEGER PRIMARY KEY AUTOINCREMENT")
                        .addColumn("WORLD", "TEXT")
                        .addColumn("LOC_X", "INT")
                        .addColumn("LOC_Y", "INT")
                        .addColumn("LOC_Z", "INT")
                        .setTableSettings("")
                        .build()
                        .execute();
                logger().debug("Waypoints table created");
            }

            // To store the statues of the seven created by plugins and the server admins
            logger().debug("Checking statues table");
            if (hasTable("genshin_statues"))
                logger().debug("Statues table exists, skip");
            else {
                logger().debug("Statues table not exists, trying to create");
                sqlManager.createTable("genshin_statues")
                        .addColumn("STATUES_ID", "INTEGER PRIMARY KEY AUTOINCREMENT")
                        .addColumn("WORLD", "TEXT")
                        .addColumn("LOC_X", "INT")
                        .addColumn("LOC_Y", "INT")
                        .addColumn("LOC_Z", "INT")
                        .addColumn("ELEMENT", "TEXT")
                        .setTableSettings("")
                        .build()
                        .execute();
                logger().debug("Statues table created");
            }
            // To store players' unlocked waypoints and statues of the seven
            logger().debug("Checking teleports table");
            if (hasTable("genshin_teleports"))
                logger().debug("Teleports table exists, skip");
            else {
                logger().debug("Teleports table not exists, trying to create");
                sqlManager.createTable("genshin_teleports")
                        .addColumn("UID", "INT")
                        .addColumn("WAYPOINTS", "TEXT")
                        .addColumn("STATUES", "TEXT")
                        .setTableSettings("")
                        .build()
                        .execute();
                logger().debug("Teleports table created");
            }

            // To store players' inventory
            logger().debug("Checking backpacks table");
            if (hasTable("genshin_backpacks"))
                logger().debug("Backpacks table exists, skip");
            else {
                logger().debug("Backpacks table not exists, trying to create");
                sqlManager.createTable("genshin_backpacks")
                        .addColumn("UID", "INT")
                        .addColumn("CHARACTERS", "TEXT")
                        .addColumn("WEAPONS", "TEXT")
                        .addColumn("ARTIFACTS", "TEXT")
                        .addColumn("DEVELOPMENT", "TEXT")
                        .addColumn("FOOD", "TEXT")
                        .addColumn("MATERIALS", "TEXT")
                        .addColumn("GADGETS", "TEXT")
                        .addColumn("QUEST", "TEXT")
                        .addColumn("PRECIOUS", "TEXT")
                        .addColumn("FURNISHINGS", "TEXT")
                        .setTableSettings("")
                        .build()
                        .execute();
                logger().debug("Backpacks table created");
            }
        } catch (SQLException e) {
            GsCorePlugin.logger().error("Failed when executing sql commands", e);
        }
    }

    private boolean hasTable(String tableName) {
        try {
            return sqlManager.createQuery().inTable("sqlite_master")
                    .addCondition("type", "table")
                    .addCondition("name", tableName)
                    .build()
                    .execute()
                    .getResultSet()
                    .next();
        } catch (SQLException e) {
            logger().error("Failed to check if the table exists", e);
            throw new RuntimeException(e);
        }
    }

    public GenshinProfileImpl getProfileByUID(int uid) {
        try {
            SQLQuery query = sqlManager.createQuery()
                    .inTable("genshin_profiles")
                    .selectColumns("UID",
                            "UUID",
                            "ADVENTURE_EXP",
                            "WORLD_LEVEL_DECREASE",
                            "MORA",
                            "PRIMOGEM",
                            "GENESIS_CRYSTAL",
                            "ASCENSION_QUESTS",
                            "ORIGINAL_RESIN")
                    .addCondition("UID", uid)
                    .build()
                    .execute();
            ResultSet resultSet = query.getResultSet();
            if (!resultSet.next())
                logger().error("Failed to get profile by uid", new RuntimeException("Failed to get profile by uid"));
            GenshinProfileImpl profile = new GenshinProfileImpl(
                    resultSet.getInt("UID"),
                    resultSet.getString("UUID"),
                    resultSet.getInt("ADVENTURE_EXP"),
                    resultSet.getInt("WORLD_LEVEL_DECREASE") == 1,
                    resultSet.getLong("MORA"),
                    resultSet.getInt("PRIMOGEM"),
                    resultSet.getInt("GENESIS_CRYSTAL"),
                    resultSet.getString("ASCENSION_QUESTS"),
                    resultSet.getInt("ORIGINAL_RESIN")
            );
            return profile;
        } catch (SQLException e) {
            logger().error("Failed to get profile by uid", e);
            return null;
        }
    }

    public GenshinProfileImpl getProfileByUUID(UUID uuid) {
        String uuidString = uuid.toString();
        try {
            SQLQuery query = sqlManager.createQuery()
                    .inTable("genshin_profiles")
                    .selectColumns("UID",
                            "UUID",
                            "ADVENTURE_EXP",
                            "WORLD_LEVEL_DECREASE",
                            "MORA",
                            "PRIMOGEM",
                            "GENESIS_CRYSTAL",
                            "ASCENSION_QUESTS",
                            "ORIGINAL_RESIN")
                    .addCondition("UUID", uuidString)
                    .build()
                    .execute();
            ResultSet resultSet = query.getResultSet();
            if (!resultSet.next())
                logger().error("Failed to get profile by uuid", new RuntimeException("Failed to get profile by uuid"));
            GenshinProfileImpl profile = new GenshinProfileImpl(
                    resultSet.getInt("UID"),
                    resultSet.getString("UUID"),
                    resultSet.getInt("ADVENTURE_EXP"),
                    resultSet.getInt("WORLD_LEVEL_DECREASE") == 1,
                    resultSet.getLong("MORA"),
                    resultSet.getInt("PRIMOGEM"),
                    resultSet.getInt("GENESIS_CRYSTAL"),
                    resultSet.getString("ASCENSION_QUESTS"),
                    resultSet.getInt("ORIGINAL_RESIN")
            );
            return profile;
        } catch (SQLException e) {
            logger().error("Failed to get profile by uuid", e);
            return null;
        }
    }

    public void close() {
        logger().debug("Trying to close sqlite database");
        try {
            this.connection.close();
            logger().debug("Closed sqlite database successfully");
        } catch (SQLException e) {
            logger().error("Failed to close sqlite database", e);
        }
    }

}
