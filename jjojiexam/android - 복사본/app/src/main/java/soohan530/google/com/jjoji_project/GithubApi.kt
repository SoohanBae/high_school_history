package soohan530.google.com.jjoji_project

import io.reactivex.Observable
import retrofit2.http.*


class GithubApi {
    interface GithubApiImpl {
        @GET("/faq/list")
        fun getRepoList(@Query("q") query: String): Observable<GithubResponseModel>
    }

    interface GithubApiImpl2 {
        @POST("/user/login")
        fun postRepoList(@FieldMap param:HashMap<String,String>) : Observable<GithubResponseModel>
    }



    companion object {
        fun getRepoList(query: String): Observable<GithubResponseModel> {
            return RetrofitCreator.create(GithubApiImpl::class.java).getRepoList(query)
        }

        fun postRepoList(param: HashMap<String,String>) : Observable<GithubResponseModel> {
            return RetrofitCreator.create(GithubApiImpl2::class.java).postRepoList(param)
        }
    }

}