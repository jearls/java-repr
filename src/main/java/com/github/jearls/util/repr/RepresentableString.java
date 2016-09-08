/**
 * 
 */
package com.github.jearls.util.repr;

import com.github.jearls.util.Representable;

/**
 * <p>
 * Implementation of Representable for Strings.
 * </p>
 * 
 * <p>
 * This escapes all backslahes (<code>\</code>) and double-quotes
 * (<code>"</code>) by prefixing them with a backslash (e.g. <code>"</code>
 * &rarr; <code>\"</code>), and replaces a subset of special characters with
 * their Java equivalents: newline &rarr; <code>\n</code>, CR &rarr;
 * <code>\r</code>; backspace &rarr; <code>\b</code>; tab &rarr;
 * <code>\t</code>; and formfeed &rarr; <code>\f</code>.
 * </p>
 * <p>
 * The transformed string is then returned surrounded by double-quotes
 * (<code>"</code>).
 * </p>
 * 
 * <p>
 * <b>TODO</b>: Include unicode character representation?
 * </p>
 * 
 * @param str
 *            the string to represent
 * @return the java representation of the string.
 * @author Johnson Earls
 * @version 0.1
 */
public class RepresentableString implements Representable {
    final String sStr;

    /**
     * @param str
     *            the string to represent
     */
    public RepresentableString(String str) {
        sStr = str;
    }

    /**
     * @return the representation of the string.
     * @see com.github.jearls.util.Representable#repr()
     */
    public String repr(ReprState repr) {
        return "\"" + (sStr.replaceAll("([\\\\\"])", "\\\\$1").replace("\n", "\\n").replace("\r", "\\r")
                .replace("\b", "\\b").replace("\t", "\\t").replace("\f", "\\f")) + "\"";
    }
}
