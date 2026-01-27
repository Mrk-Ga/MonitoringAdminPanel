package com.example.iot_project.api.test_server

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {

            "/api/gates" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(gatesJson)
            }

            "/api/users" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(usersJson)
            }

            "/api/logs" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(accessLogsJson)
            }

            "/api/buildings" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(buildingsJson)
            }

            else -> {
                MockResponse()
                    .setResponseCode(404)
                    .setBody("""{ "error": "Not found" }""")
            }
        }



    }


}

private const val buildingsJson = """
[
  {
    "id": 1,
    "name": "A",
    "address": "ul. Akademicka 1"
  },
  {
    "id": 2,
    "name": "B",
    "address": "ul. Politechniczna 5"
  }
]
"""

private const val gatesJson = """
[
  {
    "id": 1,
    "name": "Brama główna",
    "building_id": 1
  },
  {
    "id": 2,
    "name": "Wejście boczne",
    "building_id": 2
  }
]
"""

private const val usersJson = """
[
  {
    "id": 1,
    "refId": 123456,
    "name": "Jan",
    "lastname": "Kowalski",
    "email": "jan.kowalski@test.pl"
  },
  {
    "id": 2,
    "refId": 654321,
    "name": "Anna",
    "lastname": "Nowak",
    "email": "anna.nowak@test.pl"
  }
]
"""

private const val accessLogsJson = """
[
  {
    "id": 1,
    "operation": "OPEN",
    "user_id": 1,
    "gate_id": 1,
    "access_time": "2026-01-22T10:15:00"
  },
  {
    "id": 2,
    "operation": "DENIED",
    "user_id": 2,
    "gate_id": 2,
    "access_time": "2026-01-22T10:17:30"
  }
]
"""



