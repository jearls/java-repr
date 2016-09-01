package com.github.jearls.util;

import com.github.jearls.util.repr.ReprState;

/**
 * A simple (non-container) object that can return a string representation of
 * itself. <b>Do not use this</b> interface for classes that contain other
 * <code>Representable</code> objects! Containers should use the
 * <code>{@link RepresentableContainer}</code> interface.
 * 
 * @author Johnson Earls
 * @version 0.1
 */
public interface Representable {
    String repr(ReprState repr);
}