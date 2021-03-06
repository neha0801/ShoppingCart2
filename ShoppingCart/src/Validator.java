public class Validator
{
	public static boolean validateEmail(String emailStr)
	{
		boolean isValid = false;
		isValid = emailStr.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		return isValid;
	}
	
	public static boolean validateNullEmptyString(String string)
	{
		boolean isValid = false;
		if(string == null || string.trim().length() < 1)
		{
			isValid = false;
		}
		else
		{
			isValid = true;
		}
		return isValid;
	}
	public static boolean validateInt(String numberStr)
	{
		boolean isValid = false;
		isValid = numberStr.matches("^\\d+$");
		return isValid;
	}
	
	public static boolean validateDateWithFormat(String dateStr)
	{
		boolean isValid = false;
		isValid = dateStr.matches("(0?[1-9]|1[012])/((19|20)\\d\\d)");
		return isValid;
	}
}