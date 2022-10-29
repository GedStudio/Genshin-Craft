package net.deechael.genshin.test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import net.deechael.genshin.GsCorePlugin;
import net.deechael.genshin.storage.SqliteStorage;

import java.io.File;

public class SqliteTest {

    public static void main(String[] args) {

        ch.qos.logback.classic.Logger lgr = (ch.qos.logback.classic.Logger) GsCorePlugin.logger();
        LoggerContext loggerContext = lgr.getLoggerContext();
        loggerContext.reset();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("[%date] [%logger{32}] [%thread] [%level] %message%n");
        encoder.start();

        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
        appender.setContext(loggerContext);
        appender.setEncoder(encoder);
        appender.start();

        lgr.addAppender(appender);
        lgr.setLevel(Level.DEBUG);

        SqliteStorage sqlite = new SqliteStorage(new File("test.db"));
        sqlite.close();
    }

}
