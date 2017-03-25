package mealOrderProcessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogManager
{
	public enum LogLevel {
	    OFF(1), LOW(2), MED(3), HIGH(4), DEBUG(5);
	    private final int value;

	    private LogLevel(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
  
  private static LogLevel logLevel = LogLevel.OFF;
  
  private static File logFile;
  
  public static void setLogLevel(LogLevel level)
  {
	  logLevel = level;
  }
  
  public static void logConsole(String msg, LogLevel logLvl)
  {
	  if(checkLvl(logLvl))
	  {
		  System.out.println(msg);
	  }
  }
  
  /**
   * Log a message to the default file which has been defined by the user.  Always in Append mode. 
   * @param msg Message to be logged
   * @param logLvl Log granularity from the LogLvl Enum
   */
  public static void logMsg(String msg, LogLevel logLvl)
  {
	  logMsg(msg, logLvl, true);
  }
  
  /**
   * Log a message to the default file which has been defined by the user. 
   * @param msg Message to be logged
   * @param logLvl Log granularity from the LogLvl Enum
   * @param append  True to continue, False to overwrite
   */
  public static void logMsg(String msg, LogLevel logLvl, boolean append)
  {
	 logMsg(msg, logLvl, append, logFile);
  }
    
  /**
   * Log a message to the default file which has been defined by the user. 
   * @param msg Message to be logged
   * @param logLvl Log granularity from the LogLvl Enum
   * @param append  True to continue, False to overwrite
   * @param logFile log file to write the information to
   */
  public static void logMsg(String msg, LogLevel logLvl, boolean append, File logFile)
  {
	  try
	  {

		  if(logFile == null)
		  {
			  throw new IOException("Log file not initialized.  Call setLogFile(String logfile) to define");
		  }
		  
		  if(checkLvl(logLvl))
		  {
			  FileWriter fw = null;
			  BufferedWriter bw = null;
			  PrintWriter pw = null;
			  
			  try
			  {
				  fw = new FileWriter(logFile, append);
				  bw = new BufferedWriter(fw);
				  pw = new PrintWriter(bw);
				  
				  pw.println(msg);
			  }
			  finally
			  {
				  if(pw != null)
				  {
					  pw.close();
				  }
				  
				  if(bw != null)
				  {
					  pw.close();
				  }
				  
				  if(fw != null)
				  {
					  pw.close();
				  }
			  }
		  }  
	  }
	  catch(IOException e)
	  {
		  e.printStackTrace();
	  }
  }
  
  /**
   * Set the logFile path of the logger.
   * @param logPath
   */
  public static void setLogFile(File logPath)
  {
	  logFile = logPath;
  }
  
  private static boolean checkLvl (LogLevel logLvl)
  {
	  return logLevel != LogLevel.OFF && logLvl.getValue() <= logLevel.getValue();
  }
}
