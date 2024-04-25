package tools;

import static tools.DateTimeUtil.getCurrentLocalDateTimeStamp;

import static java.text.MessageFormat.format;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import static org.testng.Reporter.log;

public class Logger {

	public static final String LOG_PATTERN = "\n{0} - {1}";

	public static void report(String message, Object... params) {
		log(format(LOG_PATTERN, getCurrentLocalDateTimeStamp(), format(message, params)), true);
	}

	public static void debug(String message, Object... params) {
		report(format(message, params), FINEST);
	}

	public static void info(String message, Object... params) {
		report(format(message, params), INFO);
	}

	public static void warn(String message, Object... params) {
		report(format(message, params), WARNING);
	}

	public static void error(Throwable error, String message, Object... params) {
		String pattern = "{0}: {1}";
		report(format(pattern, format(message, params), error.getMessage()), SEVERE);
	}

	public static void error(String message, Object... params) {
		report(format(message, params), SEVERE);
	}
}
