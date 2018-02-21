package com.ingesup.docblayck.umtz.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fabienlebon on 21/02/2018.
 */

public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

}
