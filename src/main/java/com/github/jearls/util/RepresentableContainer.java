package com.github.jearls.util;

import java.util.Set;

/**
 * <p>
 * A container object that can return a string representation of itself and its
 * contents.
 * </p>
 * 
 * <p>
 * <code>reprAgain()</code> will be called whenever this instance is recursively
 * represented - in otherwords, when the instance contains itself.
 * </p>
 * 
 * <p>
 * Containers should follow this pattern:
 * </p>
 * 
 * <pre>
 * public String repr(ReprState repr) {
 *     StringBuilder representation = new StringBuilder()
 *     representation.append(innerRepr());
 *     representation.append("{");
 *     representation.append(ReprState.repr(componentA));
 *     representation.append(",");
 *     representation.append(ReprState.repr(componentB));
 *     // ...
 *     representation.append("}");
 *     return representation.toString();
 * }
 * 
 * public string innerRepr() {
 *     return myIdentifier();
 * }
 * </pre>
 * 
 * @author Johnson Earls
 * @version 0.1
 */
public interface RepresentableContainer extends Representable {
    public String innerRepr();
}