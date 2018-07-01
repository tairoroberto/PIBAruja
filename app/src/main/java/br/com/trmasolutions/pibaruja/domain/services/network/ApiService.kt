package br.com.trmasolutions.pibaruja.domain.services.network

import br.com.trmasolutions.pibaruja.model.*
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    /*########## EVENTS ##########*/
    @GET("event/")
    fun getEvents(): Single<EventsResponse>

    @POST("event/")
    fun setEvent(@Body event: Event): Single<DefaultResponse>

    @PUT("event/")
    fun updateEvent(@Body event: Event): Single<DefaultResponse>

    @DELETE("event/")
    fun deleteEvent(@Body event: Event): Single<DefaultResponse>

    /*########## USERS ##########*/
    @GET("user/")
    fun getUsers(): Single<UsersResponse>

    @POST("user/")
    fun setUser(@Body user: User): Single<DefaultResponse>

    @PUT("user/")
    fun updateUser(@Body user: User): Single<DefaultResponse>

    @DELETE("user/")
    fun deleteUser(@Body user: User): Single<DefaultResponse>

    /*########## ECPC DATES ##########*/
    @GET("ecpc/date")
    fun getEcpcDates(): Single<EcpcDatesResponse>

    @POST("ecpc/date")
    fun setEcpcDate(@Body ecpcDate: EcpcDate): Single<DefaultResponse>

    @PUT("ecpc/date")
    fun updateEcpcDate(@Body ecpcDate: EcpcDate): Single<DefaultResponse>

    @DELETE("ecpc/date")
    fun deleteEcpcDate(@Body ecpcDate: EcpcDate): Single<DefaultResponse>

    /*########## ECPC MARRIAGE COUPLES ##########*/
    @GET("ecpc/married_couple")
    fun getEcpcMarriedCouples(@Query("ecpc_date_uid") ecpcDateUid: String): Single<EcpcMarriedCoupleResponse>

    @POST("ecpc/married_couple")
    fun setEcpcMarriedCouple(@Body ecpcMarriedCouple: EcpcMarriedCouple): Single<DefaultResponse>

    @PUT("ecpc/married_couple")
    fun updateEcpcMarriedCouple(@Body ecpcMarriedCouple: EcpcMarriedCouple): Single<DefaultResponse>

    @DELETE("ecpc/married_couple")
    fun deleteEcpcMarriedCouple(@Body ecpcMarriedCouple: EcpcMarriedCouple): Single<DefaultResponse>

    @GET("youtube/v3/search")
    fun searchYouTubeVideos(@Query("channelId") channelId: String, @Query("order") order: String, @Query("part") part: String, @Query("pageToken") pageToken: String = "", @Query("key") key: String)

}