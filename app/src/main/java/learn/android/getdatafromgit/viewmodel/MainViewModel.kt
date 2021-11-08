package learn.android.getdatafromgit.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import learn.android.getdatafromgit.model.UserRepoItem
import learn.android.getdatafromgit.retrofit.ReposApi
import learn.android.getdatafromgit.util.Resource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val userReposEvent = MutableStateFlow<Resource<List<UserRepoItem>>>(Resource.Error(""))
    val userReposStateEvent: StateFlow<Resource<List<UserRepoItem>>> = userReposEvent

    suspend fun getUserRepoList(userName: String) = withContext(Dispatchers.IO) {
        try {
            val response = api.getUserRepos(userName)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                userReposEvent.value = Resource.Success(result)
            } else {
                userReposEvent.value = Resource.Error(response.message())
            }
        } catch (e: Exception) {
            userReposEvent.value = Resource.Error(e.message ?: unknownError)
        }
    }

    companion object {
        private const val MAIN_SERVER = "https://api.github.com"
        const val unknownError = "Unknown error"

        private val retrofit = Retrofit.Builder()
            .baseUrl(MAIN_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ReposApi::class.java)
    }
}