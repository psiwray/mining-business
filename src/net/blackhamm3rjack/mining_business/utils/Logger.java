package net.blackhamm3rjack.mining_business.utils;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.blackhamm3rjack.mining_business.annotations.Versioning;

/**
 * Util class for logging to the main system
 * 
 * @author Luca
 *
 */
@Versioning(major = 1, minor = 1, patch = 3)
public class Logger {
	public enum Tag {
		INFO(0, "Info"), WARNING(1, "Warning"), DEBUG(2, "Debug"), ERROR(3, "Error");

		private int code;
		private String name;

		Tag(int code, String name) {
			this.code = code;
			this.name = name;
		}

		public int getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	private static DateFormat dateFormat = new SimpleDateFormat();
	private static boolean stopOnError = false;
	private static boolean outputDebug = false;
	private static PrintStream outputStream = System.out;
	private static PrintStream fileOutputStream;

	private static void checkError(Tag tag) {
		if (tag.equals(Tag.ERROR) && stopOnError)
			System.exit(tag.getCode());
	}

	private static boolean checkDebug(Tag tag) {
		if (tag.equals(Tag.DEBUG) && !outputDebug)
			return true;

		return false;
	}

	public static <T> void print(Tag tag, Class<T> owner, String message) {
		if (checkDebug(tag))
			return;

		String data = String.format("[%s] %d:%s:[0x$%s] @ %s> %s", dateFormat.format(new Date()), tag.getCode(),
				tag.getName(), Thread.currentThread().getName(), owner.getSimpleName(), message);

		outputStream.println(data);
		if (fileOutputStream != null) {
			fileOutputStream.println(data);
			fileOutputStream.flush();
		}

		checkError(tag);
	}

	public static void print(Tag tag, String message) {
		if (checkDebug(tag))
			return;

		String data = String.format("[%s] %d:%s:[0x$%s] @ ??> %s", dateFormat.format(new Date()), tag.getCode(),
				tag.getName(), Thread.currentThread().getName(), message);

		outputStream.println(data);
		if (fileOutputStream != null) {
			fileOutputStream.println(data);
			fileOutputStream.flush();
		}

		checkError(tag);
	}

	public static <T> void print(Tag tag, Class<T> owner, String message, Object... params) {
		if (checkDebug(tag))
			return;

		String data = String.format("[%s] %d:%s:[0x$%s] @ %s> %s", dateFormat.format(new Date()), tag.getCode(),
				tag.getName(), Thread.currentThread().getName(), owner.getSimpleName(), String.format(message, params));

		outputStream.println(data);
		if (fileOutputStream != null) {
			fileOutputStream.println(data);
			fileOutputStream.flush();
		}

		checkError(tag);
	}

	public static void print(Tag tag, String message, Object... params) {
		if (checkDebug(tag))
			return;

		String data = String.format("[%s] %d:%s:[0x$%s] @ ??> %s", dateFormat.format(new Date()), tag.getCode(),
				tag.getName(), Thread.currentThread().getName(), String.format(message, params));

		outputStream.println(data);
		if (fileOutputStream != null) {
			fileOutputStream.println(data);
			fileOutputStream.flush();
		}

		checkError(tag);
	}

	public static void print() {
		outputStream.println();
	}

	public static DateFormat getDateFormat() {
		return dateFormat;
	}

	public static void setDateFormat(DateFormat dateFormat) {
		Logger.dateFormat = dateFormat;
	}

	public static boolean shouldStopOnError() {
		return stopOnError;
	}

	public static void setStopOnError(boolean stopOnError) {
		Logger.stopOnError = stopOnError;
	}

	public static boolean shouldOutputDebug() {
		return outputDebug;
	}

	public static void setOutputDebug(boolean outputDebug) {
		Logger.outputDebug = outputDebug;
	}

	public static PrintStream getOutputStream() {
		return outputStream;
	}

	public static void setOutputStream(PrintStream outputStream) {
		Logger.outputStream = outputStream;
	}

	public static void setFileOutputStream(PrintStream fileOutputStream) {
		Logger.fileOutputStream = fileOutputStream;
	}

	public static PrintStream getFileOutputStream() {
		return fileOutputStream;
	}
}
