import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private final String NUMBER = "\\d+";
    private final String DOUBLE = "(\\d+),(\\d+)";
    private Pattern pattern;
    private Matcher matcher;

    private boolean validate(String data, String regex) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public boolean validateDouble(String data) {
        return validate(data, DOUBLE);
    }

    public boolean validateChoice(String choice, int start, int end) {
        if (validate(choice, NUMBER)) {
            int number = Integer.parseInt(choice);
            return number >= start && number <= end;
        }
        return false;
    }

    public boolean validateNumber(String data) {
        return validate(data, NUMBER);
    }
}
