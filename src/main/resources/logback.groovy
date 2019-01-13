import ch.qos.logback.classic.AsyncAppender
import net.logstash.logback.encoder.LogstashEncoder
import net.logstash.logback.fieldnames.LogstashFieldNames

import static ch.qos.logback.classic.Level.DEBUG

scan("30 seconds")

def LOG_PATH = "logs"
def LOG_ARCHIVE = "${LOG_PATH}/archive"
def LOG_FILE = "${LOG_PATH}/log.out"
def ROLLING_LOG_FILE = "${LOG_PATH}/rolling-log.out"
def ROLLING_FILE_NAME_PATTERN = "${LOG_ARCHIVE}/rolling-file-log-%d{yyyy-MM-dd}.out"
def LOG_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
def PACKAGE_PATH = "com.metricstream.dms.pdf"
def MAX_HISTORY = 30 // days
def TOTAL_SIZE_CAP = "1GB"

appender("Console-Appender", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = LOG_PATTERN
    }
}

appender("File-Appender", FileAppender) {
    file = LOG_FILE
    encoder(PatternLayoutEncoder) {
        pattern = LOG_PATTERN
    }
}

appender("RollingFile-Appender", RollingFileAppender) {
    file = ROLLING_LOG_FILE
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = ROLLING_FILE_NAME_PATTERN
        maxHistory = MAX_HISTORY
        totalSizeCap = TOTAL_SIZE_CAP
    }
    encoder(LogstashEncoder) {
        fieldNames(LogstashFieldNames) {
            timestamp = '@time'
            version = '[ignore]'
            message = 'msg'
            logger = 'logger'
            thread = 'thread'
            levelValue = '[ignore]'
        }
    }
}

appender("Async-Appender", AsyncAppender) {
    appenderRef("RollingFile-Appender")
}

logger(PACKAGE_PATH, DEBUG, ["Console-Appender", "File-Appender", "Async-Appender"], false)
root(DEBUG, ["Console-Appender"])