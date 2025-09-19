window.BENCHMARK_DATA = {
  "lastUpdate": 1758279749722,
  "repoUrl": "https://github.com/justin-tay/json-schema-validator",
  "entries": {
    "JSON Schema Validator Benchmark": [
      {
        "commit": {
          "author": {
            "email": "49700559+justin-tay@users.noreply.github.com",
            "name": "Justin Tay",
            "username": "justin-tay"
          },
          "committer": {
            "email": "49700559+justin-tay@users.noreply.github.com",
            "name": "Justin Tay",
            "username": "justin-tay"
          },
          "distinct": true,
          "id": "c9cdfa97874e1ebba8852e847dfbe59c583e798d",
          "message": "Add benchmark for performance regression testing",
          "timestamp": "2025-09-19T18:55:00+08:00",
          "tree_id": "1fafdf13bac312107c25a6c6163be5b5cfbdf0c4",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/c9cdfa97874e1ebba8852e847dfbe59c583e798d"
        },
        "date": 1758279748681,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8147.915848630105,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1260.178602187926,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1313.2143209684407,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1424.0116540367192,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3194.2535407551222,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3915.7729921296245,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1230.50054486792,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1260.5106389491611,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3258.983947984765,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3439.482716284862,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4873.783216664418,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      }
    ]
  }
}