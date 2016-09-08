/**
 * 
 */
package com.github.jearls.util.repr;

import java.util.HashSet;
import java.util.Set;

import com.github.jearls.util.Representable;
import com.github.jearls.util.RepresentableContainer;

/**
 * <p>
 * A utility class for returning the representation of an object.
 * </p>
 * 
 * @author Johnson Earls
 */
public class ReprState {
    final Set<Representable> parents = new HashSet<Representable>();
    public ReprState() {
    }

    public String repr(Object foo) {
        if (foo == null) {
            return "null";
        }
        if (foo instanceof String) {
            return repr(new RepresentableString((String)foo));
        }
        if (foo instanceof Object[]) {
            return repr(new RepresentableArray((Object[])foo));
        }
        if (foo instanceof RepresentableContainer) {
            if (parents.contains(foo)) {
                return ((RepresentableContainer) foo).innerRepr();
            } else {
                parents.add((RepresentableContainer)foo);
                String rep = ((RepresentableContainer) foo).repr(this);
                parents.remove((RepresentableContainer)foo);
                return rep;
            }
        }
        if (foo instanceof Representable) {
            return ((Representable) foo).repr(this);
        }
        return foo.toString();
    }
}