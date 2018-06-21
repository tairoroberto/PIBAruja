package br.com.trmasolutions.pibaruja.domain.services.network

import com.remotejobs.io.app.model.CompaniesResponse
import com.remotejobs.io.app.model.HighestPaidRespose
import com.remotejobs.io.app.model.JobsResponse
import com.remotejobs.io.app.model.StatisticsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("getJobs/")
    fun search(@Query("path") path: String): Single<JobsResponse>

    @GET("getCompanies/")
    fun getCompanies(): Single<CompaniesResponse>

    @GET("getCompanyJobs")
    fun getCompanyJobs(@Query("company") company: String): Single<JobsResponse>

    @GET("highestPaid/")
    fun getHighestPaid(): Single<HighestPaidRespose>

    @GET("getStatistics/")
    fun getStatistics(): Single<StatisticsResponse>
}