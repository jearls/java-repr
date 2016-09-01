/**
 * 
 */
package com.github.jearls.util.repr;

import com.github.jearls.util.RepresentableContainer;

/**
 * <p>
 * Returns the representation of an array of objects.
 * </p>
 * <p>
 * This returns a string of the form
 * <i>objectClass</i><code>[]{</code><i>object</i><code>[0], </code><i>object</i><code>[1],</code>&hellip;<code>}</code>.
 * If this array has already been represented in the current call chain, then
 * instead it returns <i>objectClass</i><code>[]@</code><i>arrayIdentity</i>.
 * </p>
 * 
 * @author Johnson Earls
 */
public class RepresentableArray implements RepresentableContainer {
    public static final long serialVersionUID = 1L;

    final Object[]           sArray;

    /**
     * 
     */
    public RepresentableArray(Object[] ary) {
        sArray = ary;
    }

    /**
     * @return the representation of this array
     */
    public String repr(ReprState repr) {
        StringBuilder buf = new StringBuilder();
        buf.append(innerRepr());
        buf.append("[]");
        buf.append("{");
        for (int i = 0; i < sArray.length; i += 1) {
            if (i > 0) {
                buf.append(",");
            }
            buf.append(repr.repr(sArray[i]));
        }
        buf.append("}");
        return buf.toString();
    }

    /**
     * @return the inner recursive representation of this array
     */
    public String innerRepr() {
        StringBuilder buf = new StringBuilder();
        Class<?> arrayType = sArray.getClass().getComponentType();
        if (arrayType.getPackage().getName().equals("java.lang")) {
            buf.append(arrayType.getSimpleName());
        } else {
            buf.append(arrayType.getName());
        }
        buf.append("@" + Integer.toHexString(sArray.hashCode()));
        return buf.toString();
    }
}
