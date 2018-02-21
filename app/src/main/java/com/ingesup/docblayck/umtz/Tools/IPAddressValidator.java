package com.ingesup.docblayck.umtz.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fabienlebon on 21/02/2018.
 */

public class IPAddressValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public IPAddressValidator() {
        pattern = Pattern.compile(IPADDRESS_PATTERN);
    }

    public boolean validateIpAddress(String ip){
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
