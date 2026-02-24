/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util;

/**
 *
 * @author Admin
 */
public interface Acceptable {

    public static final String DEV_ID_VALID = "^DEV\\d{3}$";
    public static final String DEV_NAME_VALID = "^[A-Za-z]+(\\s+[A-Za-z]+)+$";
    public static final String LANGUAGE_VALID = "^[A-Za-z#]+(,\\s*[A-Za-z#]+)*$";
    public static final String SALARY_VALID = "^(1000|[1-9]\\d{3,})$";
    public static final String PROJECT_ID_VALID = "^PROJ\\d{2}$";

    public static boolean isValid(String str, String pattern) {
        if (str == null) {
            return false;
        }
        return str.matches(pattern);
    }
}

