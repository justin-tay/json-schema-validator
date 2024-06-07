package com.networknt.schema.regex;

import org.graalvm.polyglot.Context;

/**
 * Factory for the js {@link Context}.
 */
public class GraalJSContextFactory {
    /**
     * The holder defers the classloading until it is used.
     */
    private static class Holder {
        private static final Context INSTANCE = Context.newBuilder("js").option("engine.WarnInterpreterOnly", "false")
                .build();
    }

    /**
     * Gets the singleton instance of the Context.
     * <p>
     * This may need to be closed to release resources if no longer needed.
     *
     * @return the Context
     */
    public static Context getInstance() {
        return Holder.INSTANCE;
    }
}
