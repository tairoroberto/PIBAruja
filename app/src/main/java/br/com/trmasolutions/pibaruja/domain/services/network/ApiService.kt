package br.com.trmasolutions.pibaruja.domain.services.network

import br.com.trmasolutions.pibaruja.model.*
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    /*########## EVENTS ##########*/
    @GET("event/")
    fun getEvents(): Single<EventsResponse>

    @POST("event/")
    fun setEvent(event: Event): Single<DefaultResponse>

    @PUT("event/")
    fun updateEvent(event: Event): Single<DefaultResponse>

    @DELETE("event/")
    fun deleteEvent(event: Event): Single<DefaultResponse>

    /*########## USERS ##########*/
    @GET("user/")
    fun getUsers(): Single<UsersResponse>

    @POST("user/")
    fun setUser(user: User): Single<DefaultResponse>

    @PUT("user/")
    fun updateUser(user: User): Single<DefaultResponse>

    @DELETE("user/")
    fun deleteUser(user: User): Single<DefaultResponse>

    /*########## ECPC DATES ##########*/
    @GET("ecpc/date")
    fun getEcpcDates(): Single<EcpcDatesResponse>

    @POST("ecpc/date")
    fun setEcpcDate(ecpcDate: EcpcDate): Single<DefaultResponse>

    @PUT("ecpc/date")
    fun updateEcpcDate(ecpcDate: EcpcDate): Single<DefaultResponse>

    @DELETE("ecpc/date")
    fun deleteEcpcDate(ecpcDate: EcpcDate): Single<DefaultResponse>

    /*########## ECPC MARRIAGE COUPLES ##########*/
    @GET("ecpc/married_couple")
    fun getEcpcMarriedCouples(@Query("ecpc_date_uid") ecpcDateUid: String): Single<EcpcMarriedCoupleResponse>

    @POST("ecpc/married_couple")
    fun setEcpcMarriedCouple(user: User): Single<DefaultResponse>

    @PUT("ecpc/married_couple")
    fun updateEcpcMarriedCouple(user: User): Single<DefaultResponse>

    @DELETE("ecpc/married_couple")
    fun deleteEcpcMarriedCouple(user: User): Single<DefaultResponse>

}