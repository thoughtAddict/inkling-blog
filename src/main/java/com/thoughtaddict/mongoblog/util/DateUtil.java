package com.thoughtaddict.mongoblog.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;


public class DateUtil {

	public static final String DEFAULT_DATEFORMAT = "MM/dd/yyyy H:mm a";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String slash_mmddyyyy = "MM/dd/yyyy";
	public static final String yyyyMMdd_Hmmss = "yyyyMMdd_Hmmss";

	public DateUtil() {
	}

	public static String currentDateToString(String format) {

		if ( format.equals("") ) {
			format = DEFAULT_DATEFORMAT;
		}

		Date td = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String today = sdf.format(td);

		return today;
	}


	public static String variableDateToString(String format, int offset) {

		if ( format.equals("") ) {
			format = DEFAULT_DATEFORMAT;
		}

		Calendar cal = Calendar.getInstance() ;
		cal.setTime( new Date() ) ;
		cal.add( Calendar.DATE, offset ) ;
		Date td = cal.getTime() ;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String today = sdf.format(td);

		return today;
	}


	public static String variableDateToString(Date dateValue, String format, int offset) {

		if ( format.equals("") ) {
			format = DEFAULT_DATEFORMAT;
		}

		Calendar cal = Calendar.getInstance() ;
		cal.setTime( dateValue ) ;
		cal.add( Calendar.DATE, offset ) ;
		Date td = cal.getTime() ;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String today = sdf.format(td);

		return today;
	}

	public static Date variableDate(Date dateValue, String format, int offset) {

		Date newDate = new Date();

		if ( format.equals("") ) {
			format = DEFAULT_DATEFORMAT;
		}

		Calendar cal = Calendar.getInstance() ;
		cal.setTime( dateValue ) ;
		cal.add( Calendar.DATE, offset ) ;
		Date td = cal.getTime() ;

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String today = sdf.format(td);

		try {

			newDate = sdf.parse(today);

		}catch(ParseException pe){

			System.out.println(pe.toString());

		}

		return newDate;
	}


	public static String stringDateMask(String inputMask, String outputMask, String dateValue) {

		String output = "";
		String year   = "";
		String month  = "";
		String day    = "";

		if ( inputMask.equals(yyyyMMdd) ) {
			year  = dateValue.substring(0,4);
			month = dateValue.substring(4,6);
			day   = dateValue.substring(6,8);
		}

		if ( outputMask.equals(slash_mmddyyyy) ) {
			output = month + "/" + day + "/" + year;
		}

		return output;
	}

	public static Date stringToDate(String dateValue, Boolean isSQLDate){

		SimpleDateFormat sdf;

		if(isSQLDate) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		} else{
			sdf = new SimpleDateFormat("MM/dd/yyyy");
		}

		Date date = null;

		try{
			date = sdf.parse(dateValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	public static Date stringToDate(String dateValue){
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("MM/dd/yyyy H:mm");
		Date date = null;
		try{
			date = sdf.parse(dateValue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String dateToString(Date dateValue) {

		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATEFORMAT);

		return sdf.format(dateValue);
	}


	public static String dateToString(Date dateValue, String format) {

		if ( format.equals("") ) {
			format = DEFAULT_DATEFORMAT;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(dateValue);
	}

	public static String dayFromDate(Date inDate) {

		String day = "";
		if ( null != inDate ) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(inDate);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			if ( dayOfWeek == 1 ) {
				day = "Sunday";
			} else if ( dayOfWeek == 2 ) {
				day = "Monday";
			} else if ( dayOfWeek == 3 ) {
				day = "Tuesday";
			} else if ( dayOfWeek == 4 ) {
				day = "Wednesday";
			} else if ( dayOfWeek == 5 ) {
				day = "Thursday";
			} else if ( dayOfWeek == 6 ) {
				day = "Friday";
			} else if ( dayOfWeek == 7 ) {
				day = "Saturday";
			}
		}

		return day;
	}

	public static int dayNumberFromDate(Date inDate) {

		int dayOfWeek = -1;

		if ( null != inDate ) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(inDate);
			dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		}

		return dayOfWeek;
	}

	public static String intToDateString(int inDate, String format) {

		String dateString = "";

		if ( format.equals("") ) {
			format = DEFAULT_DATEFORMAT;
		}

		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_YEAR, inDate);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		dateString = formatter.format( date.getTime() );

		return dateString;
	}

	public static String getMonthName(int month) {

		String monthName = "";

		if ( month != 0 ) {

			if ( month == 1 || month == 13 ) {
				monthName = "January";
			} else if ( month == 2 || month == 14 ) {
				monthName = "February";
			} else if ( month == 3 || month == 15 ) {
				monthName = "March";
			} else if ( month == 4 || month == 16 ) {
				monthName = "April";
			} else if ( month == 5 || month == 17 ) {
				monthName = "May";
			} else if ( month == 6 || month == 18 ) {
				monthName = "June";
			} else if ( month == 7 || month == 19 ) {
				monthName = "July";
			} else if ( month == 8 || month == 20 ) {
				monthName = "August";
			} else if ( month == 9 || month == 21 ) {
				monthName = "September";
			} else if ( month == 10 || month == 22 ) {
				monthName = "October";
			} else if ( month == 11 || month == 23 ) {
				monthName = "November";
			} else if ( month == 12 || month == 24 ) {
				monthName = "December";
			} else {
				monthName = "Unknown";
			}
		}

		return monthName;
	}

	public static String getMonthName(String month) {

		String monthName = "";

		if ( null != month && !"".equals(month) ) {

			if ( "01".equals(month) ) {
				monthName = "January";
			} else if ( "02".equals(month) ) {
				monthName = "February";
			} else if ( "03".equals(month) ) {
				monthName = "March";
			} else if ( "04".equals(month) ) {
				monthName = "April";
			} else if ( "05".equals(month) ) {
				monthName = "May";
			} else if ( "06".equals(month) ) {
				monthName = "June";
			} else if ( "07".equals(month) ) {
				monthName = "July";
			} else if ( "08".equals(month) ) {
				monthName = "August";
			} else if ( "09".equals(month) ) {
				monthName = "September";
			} else if ( "10".equals(month) ) {
				monthName = "October";
			} else if ( "11".equals(month) ) {
				monthName = "November";
			} else if ( "12".equals(month) ) {
				monthName = "December";
			} else {
				monthName = "Unknown";
			}
		}

		return monthName;
	}


	public static String daysPassedMessage(Date timePassed) {

		String message = "";

		Calendar past = Calendar.getInstance();
		Calendar now  = Calendar.getInstance();
		past.setTime(timePassed);
		now.setTime(new Date());
		long pastTime = past.getTime().getTime();
		long nowTime  = now.getTime().getTime();
		long diffTime = nowTime - pastTime;
		long diffDays = diffTime / (1000*60*60*24);

		if ( diffDays > 1 && diffDays <= 2 ) {
			message = "1 day ago";
		} else if ( diffDays > 2 ) {
			message = diffDays + " days ago";
		} else {
			message = "Today";
		}

		return message;
	}

	public static int timelineStatus(Date timeOfEvent) {

		int retval = 0;

		Calendar eventTime = Calendar.getInstance();
		Calendar now  = Calendar.getInstance();
		eventTime.setTime(timeOfEvent);
		now.setTime(new Date());

		if(now.get(Calendar.MONTH) == eventTime.get(Calendar.MONTH)){
			if(now.get(Calendar.DAY_OF_MONTH) > eventTime.get(Calendar.DAY_OF_MONTH)){
				retval = 1;
			} else if ( now.get(Calendar.DAY_OF_MONTH) < eventTime.get(Calendar.DAY_OF_MONTH) ) {
				retval = 2;
			} else {
				retval = 0;
			}
		} else if(now.get(Calendar.MONTH) > eventTime.get(Calendar.MONTH)){
			retval = 1;
		} else if(now.get(Calendar.MONTH) < eventTime.get(Calendar.MONTH)){
			retval = 2;
		}
		return retval;
	}
	
}