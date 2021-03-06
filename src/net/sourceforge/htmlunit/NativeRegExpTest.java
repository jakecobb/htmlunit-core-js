package net.sourceforge.htmlunit;

import org.junit.Assert;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.Scriptable;

/**
 * Tests for NativeRegExp object.
 *
 * @author Ahmed Ashour
 */
public class NativeRegExpTest {

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void empty() throws Exception {
        final String script = "new RegExp().test('AA')";
        test(script, Boolean.TRUE);
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void undefined() throws Exception {
        final String script = "new RegExp(undefined).test('AA')";
        test(script, Boolean.TRUE);
    }

    private void test(final String script, final Object expected) {
        final ContextAction action = new ContextAction() {
            public Object run(final Context cx) {
                try {
                    Scriptable scope = cx.initStandardObjects();
                    final Object o = cx.evaluateString(scope, script, "test_script", 1, null);
                    Assert.assertEquals(expected, o);
                    return o;
                } catch (final Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Utils.runWithAllOptimizationLevels(action);
    }
}
