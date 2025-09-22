window.BENCHMARK_DATA = {
  "lastUpdate": 1758516652729,
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
      },
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
          "id": "034d48f78842ab12ce55525a8c027e4d3e5baf29",
          "message": "Refactor and remove validation message handler",
          "timestamp": "2025-09-20T12:25:49+08:00",
          "tree_id": "fce300ecb51c2da7d4ec5b12298405221cf1e0b9",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/034d48f78842ab12ce55525a8c027e4d3e5baf29"
        },
        "date": 1758343763960,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8150.570851563104,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1286.3546656502465,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1349.1923538738365,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1414.2758550699994,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3232.7423257650134,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3970.151499421016,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1215.6219974214825,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1279.5793268162008,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3207.104320576281,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3446.760895359826,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4861.954989232295,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "d7755e9f5f3314f0d7e66a108a9de7dc491194cd",
          "message": "Refactor set to list in execution context",
          "timestamp": "2025-09-21T00:32:20+08:00",
          "tree_id": "47943ba9ee9e6800e0c01a048a239f5e91b59fd5",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/d7755e9f5f3314f0d7e66a108a9de7dc491194cd"
        },
        "date": 1758386347046,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7875.575524386234,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1298.2391892296175,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1358.4975870319693,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1414.0838917311632,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3209.1581665012823,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3881.9286542472278,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1370.5555723336827,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1443.3724765661289,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3876.3638277088025,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3982.3894181377786,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5401.547965708841,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "dbac2ad3fc2d8abbcc272dec5a6a8f87400852c6",
          "message": "Refactor set to list in execution context",
          "timestamp": "2025-09-21T08:06:35+08:00",
          "tree_id": "6255633869ef44b9e55324a2e0888a4397c9b2b0",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/dbac2ad3fc2d8abbcc272dec5a6a8f87400852c6"
        },
        "date": 1758413587118,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7775.439739834819,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1336.7324974097194,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1394.1196342816377,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1433.4947718197952,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3283.7296851336528,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4069.017912917134,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1413.3921669799554,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1485.3462466186077,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3962.2418458417233,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4101.629416646368,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5693.303938259912,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "c2fbd2da08ebf0dbe68c9a75de8923e3aa5fb790",
          "message": "Refactor set to list in execution context",
          "timestamp": "2025-09-21T08:19:58+08:00",
          "tree_id": "3f028146685e2ff682c2d5bebfdb2b50eee6340e",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/c2fbd2da08ebf0dbe68c9a75de8923e3aa5fb790"
        },
        "date": 1758414393612,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8104.917058624206,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1331.9025406383514,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1385.0887149543298,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1429.5756799033934,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3193.875735124857,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4018.0444919437664,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1451.3925372784263,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1476.6579631311108,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3891.829617988394,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4135.406835196867,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5924.292646637769,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "12d32d32a01dca2637e1cbc03c1183ccf0db8a21",
          "message": "Refactor to remove ErrorMessageType",
          "timestamp": "2025-09-21T10:04:03+08:00",
          "tree_id": "11a0c701c55f721c98799bb2c2c8007d8b0e1d49",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/12d32d32a01dca2637e1cbc03c1183ccf0db8a21"
        },
        "date": 1758420647966,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8277.84743054971,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1296.3393925983478,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1369.0100262826581,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1389.9714719673836,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3248.623801797807,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3964.618184673327,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1331.8878211716317,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1429.289999114974,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3813.274275616753,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4019.989529945815,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5741.472406698529,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "28d87f5f4b08750ea702e9e6378da34613a14dc6",
          "message": "Remove failFast from MessageSourceValidationMessage builder",
          "timestamp": "2025-09-21T12:47:32+08:00",
          "tree_id": "9e5ba31f0edb46ec1ca26bd04a3c7429d2cdd8b3",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/28d87f5f4b08750ea702e9e6378da34613a14dc6"
        },
        "date": 1758430455051,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8211.924500129606,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1338.1392227456201,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1370.0459705291921,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1438.5732043834141,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3278.748093888618,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4067.9030570683067,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1412.4649051606948,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1464.5859894450587,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3875.461992441747,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3976.7777898130403,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5871.23307549406,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "4d59bacf48bae8048f72e568caf4b2895ba18ce0",
          "message": "Remove failFast from MessageSourceValidationMessage builder",
          "timestamp": "2025-09-21T12:58:53+08:00",
          "tree_id": "5ca2997e1aed793406730fe455b8ecb2b57e81b8",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/4d59bacf48bae8048f72e568caf4b2895ba18ce0"
        },
        "date": 1758431151612,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8354.589562294508,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1307.9353562580584,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1370.5870958019511,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1408.9507014145101,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3225.090457336834,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4038.5781757544023,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1400.798220930799,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1459.9088896545643,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3789.0211729013845,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4037.1727613192884,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5576.0841893028055,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "d05850594af6aec8369c9e0f3db310c6cf571383",
          "message": "Remove hard coded instance location for the error message",
          "timestamp": "2025-09-21T18:29:34+08:00",
          "tree_id": "dad164e08e5d8f79d6ed45b6bf023df4c7523976",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/d05850594af6aec8369c9e0f3db310c6cf571383"
        },
        "date": 1758450968076,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7796.674633466965,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1330.4016508015918,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1370.3471513051418,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1434.9844232978182,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3305.1521037587863,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3994.5872374302635,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1410.499966788507,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1542.297707307064,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3940.3619369463104,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4237.63223323615,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5890.150958377293,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "4156f5a8a90ae13a0c2a24899860ec92a7ce3e30",
          "message": "Remove hard coded instance location for the error message",
          "timestamp": "2025-09-21T20:25:45+08:00",
          "tree_id": "5a37e97f2c2b4b58aa496598fc10fa7766d2a13a",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/4156f5a8a90ae13a0c2a24899860ec92a7ce3e30"
        },
        "date": 1758458051826,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7901.461853916252,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1309.2187835813436,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1356.8049872934282,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1423.7211978683033,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3274.6251592991816,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3992.8071606032095,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1380.3360005284467,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1486.5515737280864,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3871.853959509153,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4030.8250878583144,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5803.911175375728,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "512b465a4d8030e557b1987a5d224b6bedeaf1e8",
          "message": "Remove hard coded instance location for the error message",
          "timestamp": "2025-09-21T20:34:19+08:00",
          "tree_id": "a4237b07040314f63ee97c000550c67ebb8387fc",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/512b465a4d8030e557b1987a5d224b6bedeaf1e8"
        },
        "date": 1758458586639,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8364.55774250209,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1324.7068754602344,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1381.3251472237237,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1434.346400368657,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3242.3346919713276,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4032.0636070335204,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1441.9308428814518,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1478.9197136139353,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3928.506009430224,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4188.639945964393,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5872.591829973412,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "96d3350944f02db5b1fb09376309349bed338a63",
          "message": "Rename ValidationMessage to Error",
          "timestamp": "2025-09-21T22:03:34+08:00",
          "tree_id": "b845f33e857afbf6a027507b31ef3c8caa5658d2",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/96d3350944f02db5b1fb09376309349bed338a63"
        },
        "date": 1758463815541,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8293.234041396121,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1336.2956929540676,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1381.5709279998493,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1412.1853073759812,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3251.7146394613665,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4039.194563418368,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1438.169662229053,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1469.7242644311943,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3885.7262079049838,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4184.425816294565,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5853.164274303209,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "ff55305eb15d1ab86ca2b3c5f2fe5a4f314db954",
          "message": "Rename JsonValidator to KeywordValidator and fix class hierarchy",
          "timestamp": "2025-09-22T09:24:49+08:00",
          "tree_id": "fe20cabf7fbb7f3770fd0d999339400890e9f3ad",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/ff55305eb15d1ab86ca2b3c5f2fe5a4f314db954"
        },
        "date": 1758504694596,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7765.430941777072,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1321.2073390810756,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1374.6473068033429,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1423.108935524905,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3209.5399693662616,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3923.098732941559,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1400.2189622563471,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1502.4462524313124,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3862.3896116613164,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4099.394213976406,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5829.205212044863,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "7a8c5f5afe655f12cd4dd3fb1058e9d83a61a1bf",
          "message": "Move keyword and keyword validators to keyword package",
          "timestamp": "2025-09-22T09:45:10+08:00",
          "tree_id": "38520af1ad87ff57b8421782b69550fa46772b75",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/7a8c5f5afe655f12cd4dd3fb1058e9d83a61a1bf"
        },
        "date": 1758505904482,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7735.7866572884495,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1334.414458891422,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1376.14724286787,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1417.3580238681252,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3226.9317499283393,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3954.8897436697494,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1405.5916032689308,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1467.9688850320438,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3886.0933689413027,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4136.238845845995,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5763.075110917632,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "bb621d67722462faecccc1737ba6f8ca6a974d33",
          "message": "cache keyword value",
          "timestamp": "2025-09-22T09:57:20+08:00",
          "tree_id": "2f49094a9a17ce9fdf67a2021a5f32b6f561f5de",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/bb621d67722462faecccc1737ba6f8ca6a974d33"
        },
        "date": 1758506641213,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8168.555610622778,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1359.8256057822903,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1385.8432050863212,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1436.1189463406374,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3300.5129623342095,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4093.6099361175316,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1417.080214246517,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1535.6619104494093,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3885.34705790583,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4270.710018376788,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5927.041803891346,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "7a8c5f5afe655f12cd4dd3fb1058e9d83a61a1bf",
          "message": "Move keyword and keyword validators to keyword package",
          "timestamp": "2025-09-22T09:45:10+08:00",
          "tree_id": "38520af1ad87ff57b8421782b69550fa46772b75",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/7a8c5f5afe655f12cd4dd3fb1058e9d83a61a1bf"
        },
        "date": 1758507117186,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7771.084172907048,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1298.3683653607031,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1374.1312065901486,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1420.3499907104658,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3227.4104842993133,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3974.5091142293663,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1414.5143954157927,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1470.7230215308919,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3894.30054440954,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4151.275862685098,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5769.631963724297,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "bb621d67722462faecccc1737ba6f8ca6a974d33",
          "message": "cache keyword value",
          "timestamp": "2025-09-22T09:57:20+08:00",
          "tree_id": "2f49094a9a17ce9fdf67a2021a5f32b6f561f5de",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/bb621d67722462faecccc1737ba6f8ca6a974d33"
        },
        "date": 1758507227295,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8298.605066271162,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1317.607722591031,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1390.5487958939673,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1422.7978882379246,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3271.125007993545,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4018.2972435583797,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1445.9712548751058,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1530.3622419198357,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3797.186367187467,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4210.728186890829,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5743.1383080322385,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "7a8c5f5afe655f12cd4dd3fb1058e9d83a61a1bf",
          "message": "Move keyword and keyword validators to keyword package",
          "timestamp": "2025-09-22T09:45:10+08:00",
          "tree_id": "38520af1ad87ff57b8421782b69550fa46772b75",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/7a8c5f5afe655f12cd4dd3fb1058e9d83a61a1bf"
        },
        "date": 1758507516050,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8116.282553490838,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1327.8508118262741,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1394.0083868683712,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1425.1572090434329,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3234.314629996012,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4008.1759810078024,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1433.8265385014413,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1512.262806300346,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3900.4718494820277,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4160.939511564752,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5885.582516165464,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "523cf7c1e2cfacb3f477210c079d3a5ab6a25482",
          "message": "Move keyword and keyword validators to keyword package",
          "timestamp": "2025-09-22T10:19:01+08:00",
          "tree_id": "3c03272865a5d36954bde0233c09a4cc5fc0aec5",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/523cf7c1e2cfacb3f477210c079d3a5ab6a25482"
        },
        "date": 1758507973665,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7947.718608212464,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1334.0703685032668,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1401.02660386706,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1430.8670048622978,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3307.41448817564,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4015.1833450338404,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1439.0111732219736,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1500.3428947266639,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 4035.1087163387606,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4208.288585226448,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5775.434657925704,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "93a78904956d65cabaa6f0420f529f038781a559",
          "message": "Move keyword and keyword validators to keyword package",
          "timestamp": "2025-09-22T10:28:21+08:00",
          "tree_id": "e2d92baf0ff159bcc98e111378f65a2f69920496",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/93a78904956d65cabaa6f0420f529f038781a559"
        },
        "date": 1758508519179,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8326.744801293024,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1333.949219314645,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1390.897038175759,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1428.9940818389448,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3271.717781487289,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 3964.8091738117632,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1428.0849621249524,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1523.0631358747723,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 4025.8595689680174,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4144.521764656342,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5873.465484880632,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "eb672b3efbadbd732aecf5c1380dea35564c8d41",
          "message": "Rename SpecVersion.VersionFlag to Specification.Version and refactor",
          "timestamp": "2025-09-22T12:31:31+08:00",
          "tree_id": "c6e84cba4a922f4c136d681dae3002bc6f5472ee",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/eb672b3efbadbd732aecf5c1380dea35564c8d41"
        },
        "date": 1758515892010,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7874.482317603334,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1332.227128760029,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1392.741500025456,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1427.19226224209,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3280.7483861509972,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4003.357712109159,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1405.832542246105,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1472.6830732350306,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3950.0416812652907,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4141.649992093033,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5820.77730325263,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "id": "0d32dc5f9c4ea2c947f0ca5ea35f2f521cadedcb",
          "message": "Rename SpecVersion.VersionFlag to Specification.Version and refactor",
          "timestamp": "2025-09-22T12:36:07+08:00",
          "tree_id": "3cf2ba9ad8dc36e03f166371c344e21c7dc88d49",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/0d32dc5f9c4ea2c947f0ca5ea35f2f521cadedcb"
        },
        "date": 1758516169195,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 7853.977905096904,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1325.6372585501142,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1403.2409609028762,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1439.871600662151,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3276.6421871552943,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4012.766454209855,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1460.1924112554836,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1498.9203425165017,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 4022.218644555594,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4218.012746838835,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5867.3906274027095,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      },
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
          "distinct": false,
          "id": "b0ba692e4bf1a9f868e4526067b74e3960a5afda",
          "message": "Rename SchemaId to DialectId",
          "timestamp": "2025-09-22T12:44:13+08:00",
          "tree_id": "7135d0be82335928f2779c2b39be4639062ca7c0",
          "url": "https://github.com/justin-tay/json-schema-validator/commit/b0ba692e4bf1a9f868e4526067b74e3960a5afda"
        },
        "date": 1758516651847,
        "tool": "jmh",
        "benches": [
          {
            "name": "com.networknt.schema.benchmark.NetworkntBenchmark.basic",
            "value": 8179.152063300239,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1330.7444502766123,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1381.3790288124485,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 1432.6186591184135,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 3256.518443944537,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteOptionalBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 4021.00079490321,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2020-12\"} )",
            "value": 1429.1026105198514,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"2019-09\"} )",
            "value": 1511.537984567152,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"7\"} )",
            "value": 3948.3001041326083,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"6\"} )",
            "value": 4246.1665843246,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          },
          {
            "name": "com.networknt.schema.benchmark.NetworkntTestSuiteRequiredBenchmark.testsuite ( {\"specification\":\"4\"} )",
            "value": 5883.877064191896,
            "unit": "ops/s",
            "extra": "iterations: 3\nforks: 1\nthreads: 1"
          }
        ]
      }
    ]
  }
}