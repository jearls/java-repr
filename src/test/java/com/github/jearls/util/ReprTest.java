package com.github.jearls.util;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;

import com.github.jearls.util.repr.ReprState;

public class ReprTest {
    @Test
    public void testStringRepresentations() {
        String repr = Repr.repr("test");
        assertEquals("\"test\"", repr);
        repr = Repr.repr(repr);
        assertEquals("\"\\\"test\\\"\"", repr);
        repr = Repr.repr(repr);
        assertEquals("\"\\\"\\\\\\\"test\\\\\\\"\\\"\"", repr);
        repr = Repr.repr("this\nis\ta\r\ntest");
        assertEquals("\"this\\nis\\ta\\r\\ntest\"", repr);
    }

    @Test
    public void testArrayRepresentations() {
        String[] arrayOfString = new String[] { "this", "is", "a", "test" };
        assertEquals("String[]{\"this\",\"is\",\"a\",\"test\"}", Repr.repr(arrayOfString));
        Date[] arrayOfDate = new Date[] { new Date() };
        String dateString = arrayOfDate[0].toString();
        assertEquals("java.util.Date[]{" + dateString + "}", Repr.repr(arrayOfDate));
    }

    private class TestObject implements Representable {
        String sName;
        int    sValue;

        public TestObject(String name, int value) {
            sName = name;
            sValue = value;
        }

        public String repr(ReprState repr) {
            return "TestObject(" + repr.repr(sName) + "," + repr.repr(sValue) + ")";
        }
    }

    private class LinkedList implements RepresentableContainer {
        public LinkedList mPrevious, mNext;
        final TestObject  sValue;

        public LinkedList(TestObject value) {
            mPrevious = mNext = null;
            sValue = value;
        }

        public LinkedList insertBefore(LinkedList other) {
            this.mPrevious = other.mPrevious;
            this.mNext = other;
            other.mPrevious = this;
            return this;
        }

        public LinkedList insertAfter(LinkedList other) {
            this.mNext = other.mNext;
            this.mPrevious = other;
            other.mNext = this;
            return this;
        }

        public String repr(ReprState repr) {
            StringBuilder buf = new StringBuilder(this.innerRepr());
            buf.append("(");
            buf.append(repr.repr(sValue));
            buf.append(",");
            buf.append(repr.repr(mPrevious));
            buf.append(",");
            buf.append(repr.repr(mNext));
            buf.append(")");
            return buf.toString();
        }
        
        public String innerRepr() {
            StringBuilder buf = new StringBuilder("LinkedList@");
            buf.append(Integer.toHexString(this.hashCode()));
            return buf.toString();
        }
    }

    @Test
    public void testRepresentableRepresentation() {
        TestObject testObject = new TestObject("foo", 2);
        assertEquals("TestObject(\"foo\",2)", Repr.repr(testObject));
    }

    @Test
    public void testRecursivelyRepresentableRepresentation() {
        LinkedList list = new LinkedList(new TestObject("foo", 2));
        assertEquals("LinkedList(TestObject(\"foo\",2),null,null)", Repr.repr(list));
        list.mNext = new LinkedList(new TestObject("bar", 3)).insertAfter(list);
        System.err.println("list=" + Repr.repr(list));
        assertEquals("LinkedList(TestObject(\"foo\",2),null,LinkedList(TestObject(\"bar\",3),"
                + list.toString() + ",null))", Repr.repr(list));
    }
}
