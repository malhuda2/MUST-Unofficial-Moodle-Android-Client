package com.skybox.seven.edustat.api

import com.skybox.seven.edustat.model.AuthResponse
import com.skybox.seven.edustat.model.ConversationResponse
import com.skybox.seven.edustat.model.Course
import com.skybox.seven.edustat.model.Site
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Servies connecting to any Must moodle
 * Boolean's are expressed interms of 1 and 0
 */
interface MoodleService {
    /**
     * Authentication
     * using the default service used by the official app
     * @param
     */
    @GET("login/token.php?service=moodle_mobile_app")
    fun login(@Query("username") username: String, @Query("password") password: String): Single<AuthResponse>

    /**
     * Get site information, {mostly just for getting the userId}
     */
    @GET("webservice/rest/server.php?wsfunction=core_webservice_get_site_info")
    fun getSite(): Single<Site>

    /**
     * Get all the user courses
     */
    @GET("webservice/rest/server.php?wsfunction=core_enrol_get_users_courses")
    fun getCourses(@Query("userid") id: Int): Single<List<Course>>

    /**
     * Get all user chats without the complicated params
     * @param mergeSelf (whether to include self-conversations (true) or ONLY private conversations (false) when private conversations are requested.)
     */
    @GET("webservice/rest/server.php?wsfunction=core_message_get_conversations")
    fun getAllChats(@Query("userid") id: Int, @Query("limitfrom") limitFrom: Int,
                    @Query("limitnum") limitNum: Int, @Query("mergeself") mergeSelf: Int): Single<ConversationResponse>

    /**
     * Get all user chats with the complicated params
     * @param type (the type of the conversation, if you wish to filter to a certain type (see api constants).)
     * @param favorites (whether to include NO favourites (false) or ONLY favourites (true), or null to ignore this setting.)
     */
    @GET("webservice/rest/server.php?wsfunction=core_message_get_conversations")
    fun getAllChats(@Query("userid") id: Int, @Query("limitfrom") limitFrom: Int,
                    @Query("limitnum") limitNum: Int, @Query("type") type: Int,
                    @Query("favourites") favorites: Int, @Query("mergeself") mergeSelf: Int): Single<ConversationResponse>
}