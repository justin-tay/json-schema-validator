package com.networknt.schema.benchmark;

import java.util.concurrent.Callable;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.networknt.schema.SpecVersion.VersionFlag;

public class NetworkntTestSuiteBenchmark {
    public static final String VERSION_202012 = "2020-12";
    public static final String VERSION_201909 = "2019-09";
    public static final String VERSION_7 = "7";
    public static final String VERSION_6 = "6";
    public static final String VERSION_4 = "4";

    public static final String TYPE_REQUIRED = "required";
    public static final String TYPE_OPTIONAL = "optional";

    @State(Scope.Thread)
    public static class BenchmarkState {
        @Param({ VERSION_202012, VERSION_201909, VERSION_7, VERSION_6, VERSION_4 })
        private String specification;

        @Param({ TYPE_REQUIRED, TYPE_OPTIONAL })
        private String type;

        private Callable<Object> draft202012 = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases.findTestCases(
                VersionFlag.V202012, "src/test/suite/tests/draft2020-12", TestCaseFilter.requiredType()));
        private Callable<Object> draft201909 = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases.findTestCases(
                VersionFlag.V201909, "src/test/suite/tests/draft2019-09", TestCaseFilter.requiredType()));
        private Callable<Object> draft7 = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases
                .findTestCases(VersionFlag.V7, "src/test/suite/tests/draft7", TestCaseFilter.requiredType()));
        private Callable<Object> draft6 = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases
                .findTestCases(VersionFlag.V6, "src/test/suite/tests/draft6", TestCaseFilter.requiredType()));
        private Callable<Object> draft4 = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases
                .findTestCases(VersionFlag.V4, "src/test/suite/tests/draft4", TestCaseFilter.requiredType()));

        private Callable<Object> draft202012Optional = new NetworkntTestSuiteRunner(
                NetworkntTestSuiteTestCases.findTestCases(VersionFlag.V202012, "src/test/suite/tests/draft2020-12",
                        TestCaseFilter.optionalType()));
        private Callable<Object> draft201909Optional = new NetworkntTestSuiteRunner(
                NetworkntTestSuiteTestCases.findTestCases(VersionFlag.V201909, "src/test/suite/tests/draft2019-09",
                        TestCaseFilter.optionalType()));
        private Callable<Object> draft7Optional = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases
                .findTestCases(VersionFlag.V7, "src/test/suite/tests/draft7", TestCaseFilter.optionalType()));
        private Callable<Object> draft6Optional = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases
                .findTestCases(VersionFlag.V6, "src/test/suite/tests/draft6", TestCaseFilter.optionalType()));
        private Callable<Object> draft4Optional = new NetworkntTestSuiteRunner(NetworkntTestSuiteTestCases
                .findTestCases(VersionFlag.V4, "src/test/suite/tests/draft4", TestCaseFilter.optionalType()));

        private Callable<Object> getTestSuite() {
            switch (specification) {
            case VERSION_202012:
                if (TYPE_OPTIONAL.equals(type)) {
                    return draft202012Optional;
                }
                return draft202012;
            case VERSION_201909:
                if (TYPE_OPTIONAL.equals(type)) {
                    return draft201909Optional;
                }
                return draft201909;
            case VERSION_7:
                if (TYPE_OPTIONAL.equals(type)) {
                    return draft7Optional;
                }
                return draft7;
            case VERSION_6:
                if (TYPE_OPTIONAL.equals(type)) {
                    return draft6Optional;
                }
                return draft6;
            case VERSION_4:
                if (TYPE_OPTIONAL.equals(type)) {
                    return draft4Optional;
                }
                return draft4;
            default:
                throw new RuntimeException("No test suite for specification " + specification);
            }
        }
    }

    @BenchmarkMode(Mode.Throughput)
    @Fork(2)
    @Warmup(iterations = 5, time = 5)
    @Measurement(iterations = 5, time = 5)
    @Benchmark
    public void testsuite(BenchmarkState state, Blackhole blackhole) throws Exception {
        blackhole.consume(state.getTestSuite().call());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(NetworkntTestSuiteBenchmark.class.getSimpleName())
                .addProfiler(GCProfiler.class).build();

        new Runner(opt).run();
    }

}
