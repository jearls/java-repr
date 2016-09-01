/**
 * 
 */
package com.github.jearls.util;

import com.github.jearls.util.repr.ReprState;

/**
 * <p>
 * A utility class for returning the representation of an object.
 * </p>
 * 
 * @author Johnson Earls
 */
public class Repr {
    public static String repr(Object foo) {
        return new ReprState().repr(foo);
    }
}