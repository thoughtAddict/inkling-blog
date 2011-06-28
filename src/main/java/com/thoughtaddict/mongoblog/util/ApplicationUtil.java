package com.thoughtaddict.mongoblog.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.*;
import java.util.*;
import java.io.*;


public class ApplicationUtil {


	/** Date MMddyyyyFormatter */
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

	public static Date parseDate(String inDate) {

		Date date = null;

		if (null != inDate) {
			synchronized (dateFormatter) {
				date = dateFormatter.parse(inDate, new ParsePosition(0));
			}
		}

		return date;
	}

	public static String formatDate(Date inDate) {

		String date = null;

		if (null != inDate) {
			synchronized (dateFormatter) {
				date = dateFormatter.format(inDate);
			}
		}

		return date;
	}

	public static String dayFromDate(Date inDate) {

		Calendar cal = new GregorianCalendar();
		cal.setTime(inDate);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		String day = "";
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

		return day;
	}

	public static String escapeLine(String s) {

		if (s == null) {
			return null;
		}

		String retvalue = s;

		if (s.indexOf ("'") != -1) {
			StringBuffer hold = new StringBuffer();
			char c;

			for (int i=0; i < s.length(); i++ ) {
				if ((c=s.charAt(i)) == '\'' ) {
					hold.append ("''");
				} else {
					hold.append(c);
				}
			}

			retvalue = hold.toString();
		}

		return retvalue;
	}

	public static String[] formatPostalCode(String postalCode) {

		String[] zipArr = new String[2];
		if (postalCode != null) {

			if (postalCode.length() > 4) {
				zipArr[0] = postalCode.substring(0, 5);

				if (postalCode.length() > 8) {
					zipArr[1] = postalCode.substring(5, 9);
				} else {
					zipArr[1] = postalCode.substring(5, postalCode.length());
				}
			} else {
				zipArr[0] = postalCode.substring(0, postalCode.length());
			}
		}

		return zipArr;
	}

	public static String formatPhoneNumber(final String inPhoneNumber) {

		StringBuffer sb = new StringBuffer();

		if (inPhoneNumber != null) {
			if (inPhoneNumber.length() > 2) {
				sb.append(inPhoneNumber.substring(0, 3));

				if (inPhoneNumber.length() > 5) {
					sb.append("-" + inPhoneNumber.substring(3, 6));

					if (inPhoneNumber.length() > 9) {
						sb.append("-" + inPhoneNumber.substring(6, 10));
					} else if (inPhoneNumber.length() > 6) {
						sb.append("-" + inPhoneNumber.substring(6, inPhoneNumber.length()));
					}
				} else if (inPhoneNumber.length() > 3) {
					sb.append("-" + inPhoneNumber.substring(3, inPhoneNumber.length()));
				}
			} else {
				sb.append(inPhoneNumber.substring(0, inPhoneNumber.length()));
			}
		}

		return sb.toString();
	}

	public static String getCallingMethod() {
		//Get the stack trace element for the method that called us (level = 4).
		StackTraceElement element = getStackTraceElement(4);
		return element.getMethodName();
	}

	private static StackTraceElement getStackTraceElement(int level) {

		StackTraceElement[] elements = Thread.currentThread().getStackTrace();

		if ((elements != null) && (elements.length >= level)) {
			StackTraceElement element = elements[level];
			if (element != null) {
				return element;
			}
		}

		return null;
	}

	public static BigDecimal stringToBigDecimal(String strValue) {
		BigDecimal bigDecValue = null;

		try {
			bigDecValue = new BigDecimal(strValue.replace("," ,""));
		} catch (Exception e) {
		}

		return bigDecValue;
	}

	public static BigDecimal stringToBigDecimal(String strValue, BigDecimal defaultValue) {

		BigDecimal bigDecValue = stringToBigDecimal(strValue);

		if ( null == bigDecValue ) {
			bigDecValue = defaultValue;
		}

		return bigDecValue;
	}

	public static String bigDecimalToString(BigDecimal inValue) {

		if (null == inValue) {
			return "";
		} else {
			return inValue + "";
		}
	}

	public static String bigDecimalToString(BigDecimal inValue, String defaultValue) {

		String strValue = bigDecimalToString(inValue);

		if ( null == strValue ) {
			strValue = defaultValue;
		}

		return strValue;
	}

	public static BigDecimal doubleToBigDecimal(Double inValue) {

		BigDecimal bigDecValue = null;

		try {
			bigDecValue = new BigDecimal(inValue);
		} catch (Exception e) {
		}

		return bigDecValue;
	}

	public static BigDecimal doubleToBigDecimal(Double inValue, BigDecimal defaultValue) {

		BigDecimal bigDecValue = doubleToBigDecimal(inValue);

		if ( null == bigDecValue ) {
			bigDecValue = defaultValue;
		}

		return bigDecValue;
	}

	public static Short doubleToShort(Double inValue) {

		Short shortValue = null;

		try {
			shortValue = new Short(inValue.shortValue());
		} catch (Exception e) {
		}

		return shortValue;
	}

	public static Short doubleToShort(Double inValue, Short defaultValue) {

		Short shortValue = doubleToShort(inValue);

		if ( null == shortValue ) {
			shortValue = defaultValue;
		}

		return shortValue;
	}

	public static String doubleToString(Double inValue) {

		if (null == inValue) {
			return "";
		} else {
			return inValue + "";
		}
	}

	public static String doubleToString(Double inValue, String defaultValue) {

		String strValue = doubleToString(inValue);

		if ( null == strValue ) {
			strValue = defaultValue;
		}

		return strValue;
	}

	public static Short stringToShort(String strValue) {

		Short shortValue = null;

		try {
			shortValue = new Short(strValue.replace("," ,""));
		} catch (Exception e) {
		}

		return shortValue;
	}

	public static Short stringToShort(String strValue, Short defaultValue) {

		Short shortValue = stringToShort(strValue);

		if ( null == shortValue ) {
			shortValue = defaultValue;
		}

		return shortValue;
	}

	public static Short intToShort(int intValue) {

		Short shortValue = null;

		try {
			shortValue = new Short(String.valueOf(intValue));
		} catch (Exception e) {
		}

		return shortValue;
	}

	public static Short intToShort(int intValue, Short defaultValue) {

		Short shortValue = intToShort(intValue);

		if ( null == shortValue ) {
			shortValue = defaultValue;
		}

		return shortValue;
	}

	public static BigDecimal intToBigDecimal(int intValue) {

		BigDecimal bigDecimalValue = null;

		try {
			bigDecimalValue = new BigDecimal(String.valueOf(intValue));
		} catch (Exception e) {
		}

		return bigDecimalValue;
	}

	public static BigDecimal intToBigDecimal(int intValue, BigDecimal defaultValue) {

		BigDecimal bigDecimalValue = intToBigDecimal(intValue);

		if ( null == bigDecimalValue ) {
			bigDecimalValue = defaultValue;
		}

		return bigDecimalValue;
	}

	public static String intToString(int intValue) {

		String stringValue = null;

		try {
			stringValue = intValue+"";
		} catch (Exception e) {
		}

		return stringValue;
	}

	public static String intToString(int intValue, String defaultValue) {

		String stringValue = intToString(intValue);

		if ( null == stringValue ) {
			stringValue = defaultValue;
		}

		return stringValue;
	}

	public static String intToString(Integer intValue) {

		String stringValue = null;

		try {

			if ( null != intValue) {
				stringValue = intValue.toString();
			}

		} catch (Exception e) {
		}

		return stringValue;
	}

	public static String intToString(Integer intValue, String defaultValue) {

		String stringValue = intToString(intValue);

		if ( null == stringValue ) {
			stringValue = defaultValue;
		}

		return stringValue;
	}

	public static String shortToString(Short inValue) {
		if (null == inValue) {
			return "";
		} else {
			return inValue + "";
		}
	}

	public static String shortToString(Short inValue, String defaultValue) {

		String strValue = shortToString(inValue);

		if ( null == strValue ) {
			strValue = defaultValue;
		}

		return strValue;
	}

	public static String longToString(Long inValue) {

		String strValue = null;

		try {
			strValue = inValue + "";
		} catch (Exception e) {
		}

		return strValue;
	}

	public static String longToString(Long inValue, String defaultValue) {

		String strValue = longToString(inValue);

		if ( null == strValue ) {
			strValue = defaultValue;
		}

		return strValue;
	}

	public static Byte stringToByte(String strValue) {
		Byte byteValue = null;
		try {
			byteValue = new Byte(strValue.replace("," ,""));
		} catch (Exception e) {
		}

		return byteValue;
	}

	public static Byte stringToByte(String strValue, Byte defaultValue) {

		Byte byteValue = stringToByte(strValue);

		if ( null == byteValue ) {
			byteValue = defaultValue;
		}

		return byteValue;
	}

	public static String byteToString(Byte inValue) {

		if (null == inValue) {
			return "";
		} else {
			return inValue + "";
		}
	}

	public static String byteToString(Byte inValue, String defaultValue) {

		String strValue = byteToString(inValue);

		if ( null == strValue ) {
			strValue = defaultValue;
		}

		return strValue;
	}

	public static Integer stringToInteger(String strValue) {

		Integer intValue = null;

		try {
			if ( "".equals(strValue) ) {
				intValue = new Integer(0);
			} else {
				intValue = new Integer(strValue);
			}
		} catch(Exception e) {
		}

		return intValue;
	}

	public static Integer stringToInteger(String strValue, Integer defaultValue) {

		Integer intValue = stringToInteger(strValue);

		if ( null == intValue ) {
			intValue = defaultValue;
		}

		return intValue;
	}

	public static Long stringToLong(String strValue) {

		Long longValue = null;

		try {
			if ( "".equals(strValue) ) {
				longValue= new Long(0);
			} else {
				longValue= new Long(strValue);
			}
		} catch(Exception e) {
		}

		return longValue;
	}

	public static Long stringToLong(String strValue, Long defaultValue) {

		Long longValue = stringToLong(strValue);

		if ( null == longValue ) {
			longValue = defaultValue;
		}

		return longValue;
	}

	public static Boolean stringToBoolean(String strValue) {

		Boolean booleanValue = null;

		try {

			if ( "YES".equalsIgnoreCase(strValue)  ||
					"Y".equalsIgnoreCase(strValue)	   ||
					"TRUE".equalsIgnoreCase(strValue) ||
					"T".equalsIgnoreCase(strValue)    ||
					"1".equalsIgnoreCase(strValue)  ) {

				booleanValue = new Boolean(true);

			} else if ( "NO".equalsIgnoreCase(strValue)  ||
					"N".equalsIgnoreCase(strValue)	    ||
					"FALSE".equalsIgnoreCase(strValue) ||
					"F".equalsIgnoreCase(strValue)     ||
					"0".equalsIgnoreCase(strValue)     ||
					"".equalsIgnoreCase(strValue)   ) {

				booleanValue = new Boolean(false);
			}

		} catch(Exception e) {
		}

		return booleanValue;
	}

	public static Boolean stringToBoolean(String strValue, Boolean defaultValue) {

		Boolean booleanValue = stringToBoolean(strValue);

		if ( null == booleanValue ) {
			booleanValue = defaultValue;
		}

		return booleanValue;
	}

	public static Long intToLong(int intValue) {

		Long longValue = null;

		try {
			longValue= new Long(intValue);
		} catch(Exception e) {
		}

		return longValue;
	}

	public static Long intToLong(int intValue, Long defaultValue) {

		Long longValue = intToLong(intValue);

		if ( null == longValue ) {
			longValue = defaultValue;
		}

		return longValue;
	}

	public static Byte intToByte(int value) {

		Integer integer = new Integer(value);
		return new Byte(integer.byteValue());
	}

	public static Date stringToDate(String inDate) {

		if ( "".equals(inDate) ) {
			return null;
		} else {
			return parseDate(inDate);
		}
	}

	public static String dateToString(Date inDate) {
		return formatDate(inDate);
	}

	public static String streamToString(InputStream is, String endLine) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + endLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	public static BigDecimal round7Decimals(final BigDecimal inNum){

		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(6);
		String str = nf.format(inNum);
		str = (str != null && !"".equals(str)) ? str.replaceAll(",", ""):str;

		return new BigDecimal(str);
	}

	public static boolean checkNull(BigDecimal inValue){

		if(null != inValue ){
			return false;
		}else{
			return true;
		}
	}
	public static boolean checkNULL(BigDecimal inValue){

		if(null != inValue ){
			return false;
		}else{
			return true;
		}
	}
	public static boolean isNull(BigDecimal inValue){

		if(null != inValue ){
			return false;
		}else{
			return true;
		}
	}

	public static boolean checkNull(String inValue){

		if(null != inValue && 0 != inValue.trim().length()){

			return false;
		}else{
			return true;
		}
	}
	public static boolean checkNULL(String inValue){
		if(null != inValue && 0 != inValue.trim().length()){
			return false;
		}else{
			return true;
		}
	}
	public static boolean isNull(String inValue){

		if(null != inValue && 0 != inValue.trim().length()){
			return false;
		}else{
			return true;
		}
	}

	public static String isNullThenDefaultStringElseStringValue(Object obj, Object defaultValue) {

		String resultString = "";

		if ( null == obj ) {

			if (defaultValue instanceof BigDecimal) {
				BigDecimal objValue = (BigDecimal) defaultValue;
				resultString = bigDecimalToString(objValue);
			} else if (defaultValue instanceof Short) {
				Short objValue = (Short) defaultValue;
				resultString = shortToString(objValue);
			} else if (defaultValue instanceof Long) {
				Long objValue = (Long) defaultValue;
				resultString = longToString(objValue);
			} else if (defaultValue instanceof Integer) {
				Integer objValue = (Integer) defaultValue;
				resultString = intToString(objValue);
			} else if (defaultValue instanceof Date) {
				Date objValue = (Date) defaultValue;
				resultString = dateToString(objValue);
			}

		} else {

			if (obj instanceof BigDecimal) {
				BigDecimal objValue = (BigDecimal) obj;
				resultString = bigDecimalToString(objValue);
			} else if (obj instanceof Short) {
				Short objValue = (Short) obj;
				resultString = shortToString(objValue);
			} else if (obj instanceof Long) {
				Long objValue = (Long) obj;
				resultString = longToString(objValue);
			} else if (obj instanceof Integer) {
				Integer objValue = (Integer) obj;
				resultString = intToString(objValue);
			} else if (obj instanceof Date) {
				Date objValue = (Date) obj;
				resultString = dateToString(objValue);
			}

		}

		return resultString;
	}

	public static boolean checkNull(Date inValue){

		if(null != inValue){
			return false;
		}else{
			return true;
		}
	}
	public static boolean checkNULL(Date inValue){

		if(null != inValue){
			return false;
		}else{
			return true;
		}
	}
	public static boolean isNull(Date inValue){

		if(null != inValue){
			return false;
		}else{
			return true;
		}
	}

	public static BigDecimal isNullThenDefault(BigDecimal inValue, BigDecimal defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static BigDecimal isNullThenDefault(Object inValue, BigDecimal defaultValue){

		if(null != inValue){
			return (BigDecimal) inValue;
		}else{
			return defaultValue;
		}
	}

	public static Integer isNullThenDefault(Integer inValue, Integer defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static Integer isNullThenDefault(Object inValue, Integer defaultValue){

		if(null != inValue){
			return (Integer) inValue;
		}else{
			return defaultValue;
		}
	}

	public static String isNullThenDefault(String inValue, String defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static String isNullThenDefault(Object inValue, String defaultValue){

		if(null != inValue){
			return (String) inValue;
		}else{
			return defaultValue;
		}
	}

	public static Date isNullThenDefault(Date inValue, Date defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static Date isNullThenDefault(Object inValue, Date defaultValue){

		if(null != inValue){
			return (Date) inValue;
		}else{
			return defaultValue;
		}
	}

	public static Short isNullThenDefault(Short inValue, Short defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static Short isNullThenDefault(Object inValue, Short defaultValue){

		if(null != inValue){
			return (Short) inValue;
		}else{
			return defaultValue;
		}
	}

	public static Float isNullThenDefault(Float inValue, Float defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static Float isNullThenDefault(Object inValue, Float defaultValue){

		if(null != inValue){
			return (Float) inValue;
		}else{
			return defaultValue;
		}
	}

	public static Long isNullThenDefault(Long inValue, Long defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static Long isNullThenDefault(Object inValue, Long defaultValue){

		if(null != inValue){
			return (Long) inValue;
		}else{
			return defaultValue;
		}
	}

	public static Double isNullThenDefault(Double inValue, Double defaultValue){

		if(null != inValue){
			return inValue;
		}else{
			return defaultValue;
		}
	}

	public static Double isNullThenDefault(Object inValue, Double defaultValue){

		if(null != inValue){
			return (Double) inValue;
		}else{
			return defaultValue;
		}
	}

	public static BigDecimal divideByHundered(BigDecimal inValue){

		if(inValue != null){
			BigDecimal intRatePer = new BigDecimal ((inValue.doubleValue()) / 100);
			intRatePer = round7Decimals(intRatePer);
			return intRatePer;
		}else{
			return null;
		}
	}

	public static BigDecimal multiplyByHundered(BigDecimal inValue){

		if(inValue != null){
			BigDecimal intRatePer = new BigDecimal ((inValue.doubleValue()) * 100);
			intRatePer = round7Decimals(intRatePer);
			return intRatePer;
		}else{
			return null;
		}
	}

	public static Integer parseInteger (String inInt)  {

		Integer integer = null;
		if ( inInt!=null) {
			try {
				integer = Integer.parseInt(inInt);
			} catch (Exception ex) {
			}
		}

		return integer;
	}

	public static Double convertBigDecimalToDouble (BigDecimal bigDecimal)
	{
		Double number = null;
		if ( bigDecimal !=null)
			number = new Double(bigDecimal.doubleValue());

		return number;
	}

	public static boolean isStringInList ( String inputString, String[] stringCompareList) {

		boolean inList= false;

		if ( ( stringCompareList !=null )  && (inputString !=null)) {

			for ( int i = 0; i <stringCompareList.length; i ++) {
				if (inputString.compareTo(stringCompareList[i])==0) {
					inList = true;
					break;
				}
			}
		}

		return inList;
	}

	public static String coalesceStr(String inValue, String coalesceValue) {

		if(!checkNULL(inValue))
			return inValue;
		else
			return coalesceValue;
	}

	public static String getFileName(String inFileName){
		String separator =  "\\";
		return getFileName(inFileName, separator);
	}

	public static String getFileName(String inFileName, String separator){
		int ind =  inFileName.lastIndexOf(separator);
		int filePathLength = inFileName.length();

		inFileName = inFileName.substring(ind+1, filePathLength);
		return inFileName;
	}

	public static Date getAbsoluteEndDate(final Date date) {
		Date newDate = null;
		if( date != null ) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			// Set the hour, minute, second to max values for the day.
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			newDate = calendar.getTime();
		}
		return newDate;
	}

	public static String stripNonNumericFromString(String strValue) {

		strValue = strValue.replaceAll("[^0-9]", "");

		return strValue;
	}

	public static String replaceNonNumericInString(String strValue, String replaceValue) {

		strValue = strValue.replaceAll("[^0-9]", replaceValue);

		return strValue;
	}

	public static String getZipCode( String strZipcode, String strZipCodePlusFour ) {

		if ( ( strZipCodePlusFour == null ) || strZipCodePlusFour.equals( "" ) ) {
			return strZipcode;
		}
		String zipCode = strZipcode;

		if ( ( zipCode != null ) && ( zipCode.length() > 0 ) ) {

			if ( ( strZipCodePlusFour != null ) && ( strZipCodePlusFour.trim().length() > 0 ) ) {
				zipCode += strZipCodePlusFour;
			}
		}

		return zipCode;
	}

	public static String getSubstring( int intStartIndex, int intEndIndex, String str ) {

		intStartIndex = intStartIndex - 1;
		intEndIndex = intStartIndex + intEndIndex;
		String strValue = "";

		if ( str == null ) {
			str = "";
		}

		try {
			if ( ( str.length() - intEndIndex ) >= 0 ) {
				if ( ( str.length() - intEndIndex ) >= 0 ) {
					strValue = str.substring( intStartIndex, intEndIndex );
					strValue = strValue.trim();
				} else {
					strValue = str.substring( intStartIndex, str.length() - 1 );
					strValue = strValue.trim();
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return strValue;
	}

	public static String formatSsn( String strSsn ) {
		String strValue = "";

		if ( strSsn.length() < 9 ) {
			return strSsn;
		}
		String strInitial = strSsn.substring( 0, 3 );
		String strMiddle = strSsn.substring( 3, 5 );
		String strLast = strSsn.substring( 5, 9 );
		strValue = strInitial + "-" + strMiddle + "-" + strLast;
		return strValue;
	}

	public static String getDateMMDDYYYY( String strDate ) {
		if ( ( strDate != null ) && ( strDate.length() >= 8 ) ) {
			String strCurrentYear = strDate.substring( 0, 4 );
			String strCurrentMonth = strDate.substring( 4, 6 );
			String strCurrentDate = strDate.substring( 6, 8 );
			strDate = strCurrentMonth + "/" + strCurrentDate + "/" + strCurrentYear;
			return strDate;
		}
		return "";
	}

	public static String getFormattedPhoneNumber( String strPhoneNumber ) {
		String strValue = strPhoneNumber;
		if ( ( strValue != null ) && ( strValue.length() >= 10 ) ) {
			strValue = strPhoneNumber.substring( 0, 3 ) + "-" + strPhoneNumber.substring( 3, 6 ) + "-"
			+ strPhoneNumber.substring( 6, 10 );
		}
		return strValue;
	}

	public static boolean isStringEmpty( String toBeChecked ) {

		if ( toBeChecked == null || ( toBeChecked != null && toBeChecked.trim().length() == 0 ) ) {
			return true;
		}
		return false;
	}

	public static String isStringEmpty( String toBeChecked, String defaultString ) {

		if ( toBeChecked == null || ( toBeChecked != null && toBeChecked.trim().length() == 0 ) ) {
			return defaultString;
		}
		return toBeChecked;
	}

	public static String currencyFormatter( double value ) {

		String temp = "0.00";

		if ( value < 0 ) {
			temp = String.valueOf( NumberFormat.getCurrencyInstance().format( value * -1 ) );
			if ( temp.indexOf( '$' ) != -1 ) {
				temp = temp.substring( 1, temp.length() );
			}
			temp = "-" + temp;
		} else {
			temp = String.valueOf( NumberFormat.getCurrencyInstance().format( value ) );
			if ( temp.indexOf( '$' ) != -1 ) {
				temp = temp.substring( 1, temp.length() );
			}
		}

		return temp.replaceAll( ",", "" );
	}

	public static String currencyFormatter( BigDecimal value ) {

		double doubleValue = value.doubleValue();

		return currencyFormatter(doubleValue);
	}

	public static String currencyFormatter( String value ) {

		return currencyFormatter(Double.parseDouble( value ));
	}

	public static String checkInterestAmtFormat( String interestRateToFormat ) {

		StringBuffer formattedInterestRate = new StringBuffer();

		if ( interestRateToFormat != null ) {

			String decimalPart = interestRateToFormat.substring( interestRateToFormat.indexOf( "." ) + 1 );
			int lengthOfDecimal = decimalPart.length();
			int ctrDecimal = 3 - lengthOfDecimal;
			for ( int decimalCtr = 0; decimalCtr < ctrDecimal; decimalCtr++ ) {
				formattedInterestRate.append( "0" );
			}
			return interestRateToFormat + formattedInterestRate.toString();

		} else {
			return "";
		}
	}

	public static Long parseLong(String inLong) {
		Long longValue = null;
		if (inLong != null) {
			try {
				longValue = Long.valueOf(inLong);
			} catch (Exception ex) {
				//do nothing
			}
		}

		return longValue;
	}

	public static String formatString(final String message,
			final Object[] values) {
		if (values == null || values.length == 0) {
			return message;
		}
		MessageFormat formatter = new MessageFormat(message);
		return formatter.format(values);
	}

	public static int compareStrings(String str1, String str2, boolean ignoreCase)
	{
		char[] char1 = str1.toCharArray();
		char[] char2 = str2.toCharArray();

		int len = Math.min(char1.length,char2.length);

		for(int i = 0, j = 0; i < len && j < len; i++, j++)
		{
			char ch1 = char1[i];
			char ch2 = char2[j];
			if(Character.isDigit(ch1) && Character.isDigit(ch2)
					&& ch1 != '0' && ch2 != '0')
			{
				int _i = i + 1;
				int _j = j + 1;

				for(; _i < char1.length; _i++)
				{
					if(!Character.isDigit(char1[_i]))
					{
						//_i--;
						break;
					}
				}

				for(; _j < char2.length; _j++)
				{
					if(!Character.isDigit(char2[_j]))
					{
						//_j--;
						break;
					}
				}

				int len1 = _i - i;
				int len2 = _j - j;
				if(len1 > len2)
					return 1;
				else if(len1 < len2)
					return -1;
				else
				{
					for(int k = 0; k < len1; k++)
					{
						ch1 = char1[i + k];
						ch2 = char2[j + k];
						if(ch1 != ch2)
							return ch1 - ch2;
					}
				}

				i = _i - 1;
				j = _j - 1;
			}
			else
			{
				if(ignoreCase)
				{
					ch1 = Character.toLowerCase(ch1);
					ch2 = Character.toLowerCase(ch2);
				}

				if(ch1 != ch2)
					return ch1 - ch2;
			}
		}

		return char1.length - char2.length;
	}

	public static Integer convertBigIntegerToInteger(BigInteger bigInt)
	{
		Integer integer = null;

		if ( bigInt !=null)
			integer = new Integer(bigInt.intValue());

		return integer;
	}

	public static boolean isParsableToInt(String i) {

		try {
			Integer.parseInt(i);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean isParsableToDouble(String i) {

		try {
			Double.parseDouble(i);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean bitWiseAND(int findNumber, int bitWiseNumber) {
					
		int result = bitWiseNumber & findNumber;
		
		if ( result == findNumber ) {
			return true;
		} else {
			return false;
		}				
	}
}