package com.raghu.stackex

import com.google.gson.Gson
import com.raghu.stackex.models.UserResponse
import com.raghu.stackex.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun testIfWebServiceIsReachable() = runBlocking{
        val response = UserRepository().getUsers("")
        assertEquals(response.isSuccessful, true)
    }

    @Test
    fun testIfReturned20Items() = runBlocking{
        val response = UserRepository().getUsers("")
        val body =  response.body()
        val bodyStr = String(body!!.bytes())
        val usersResponse = Gson().fromJson(bodyStr, UserResponse::class.java)
        assertEquals(usersResponse.items.size, 20)
    }

    @Test
    fun testEmptyList() = runBlocking{
        val usersResponse = Gson().fromJson("{\"items\":[]}", UserResponse::class.java)
        assertEquals(usersResponse.items.size, 0)
    }

    @Test
    fun testDataParsing() {
        val usersResponse = Gson().fromJson("{\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"badge_counts\": {\n" +
                "        \"bronze\": 4,\n" +
                "        \"silver\": 0,\n" +
                "        \"gold\": 0\n" +
                "      },\n" +
                "      \"account_id\": 2467298,\n" +
                "      \"is_employee\": false,\n" +
                "      \"last_modified_date\": 1533307808,\n" +
                "      \"last_access_date\": 1614096280,\n" +
                "      \"reputation_change_year\": 0,\n" +
                "      \"reputation_change_quarter\": 0,\n" +
                "      \"reputation_change_month\": 0,\n" +
                "      \"reputation_change_week\": 0,\n" +
                "      \"reputation_change_day\": 0,\n" +
                "      \"reputation\": 1,\n" +
                "      \"creation_date\": 1362779107,\n" +
                "      \"user_type\": \"registered\",\n" +
                "      \"user_id\": 2150092,\n" +
                "      \"location\": \"Peterborough, UK\",\n" +
                "      \"link\": \"https://stackoverflow.com/users/2150092/raghavendran-chandran\",\n" +
                "      \"profile_image\": \"https://www.gravatar.com/avatar/e693673805018ec473145b97b5327f7c?s=128&d=identicon&r=PG\",\n" +
                "      \"display_name\": \"raghavendran chandran\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"has_more\": false,\n" +
                "  \"quota_max\": 10000,\n" +
                "  \"quota_remaining\": 9980\n" +
                "}", UserResponse::class.java)
        val user = usersResponse.items.get(0)

        assertEquals(user.user_id, 2150092)
        assertEquals(user.display_name, "raghavendran chandran")
        assertEquals(user.reputation, 1)
        assertEquals(user.badge_counts.gold, 0)
        assertEquals(user.badge_counts.silver, 0)
        assertEquals(user.badge_counts.bronze, 4)
        assertEquals(user.location, "Peterborough, UK")
    }
}