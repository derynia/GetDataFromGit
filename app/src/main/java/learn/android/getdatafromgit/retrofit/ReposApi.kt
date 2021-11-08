package learn.android.getdatafromgit.retrofit

import learn.android.getdatafromgit.model.UserRepoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposApi {
    @GET(ENDPOINT)
    suspend fun getUserRepos(@Path("user") user: String): Response<List<UserRepoItem>>

    companion object {
        private const val ENDPOINT = "/users/{user}/repos"
    }
}