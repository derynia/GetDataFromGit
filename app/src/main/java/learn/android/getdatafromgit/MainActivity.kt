package learn.android.getdatafromgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import learn.android.getdatafromgit.databinding.ActivityMainBinding
import learn.android.getdatafromgit.recycler.UserRepoAdapter
import learn.android.getdatafromgit.util.Resource
import learn.android.getdatafromgit.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val userReposViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserRepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.editUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                lifecycleScope.launch {
                    getDataFromServer(binding.editUserName.text.toString())
                }
            }
        })

        binding.repoRecycler.adapter = adapter
        lifecycleScope.launch {
            userReposViewModel.userReposStateEvent.collect {
                    event ->
                when (event) {
                    is Resource.Success -> {
                        adapter.setList(event.data)
                    }
                    is Resource.Error -> {
                        event.message?.let { Log.d(ERROR_TAG, it) }
                    } else ->
                        Unit
                }
            }
        }
    }

    fun getDataFromServer(userName: String) {
        lifecycleScope.launch {
            userReposViewModel.getUserRepoList(userName)
        }
    }

    companion object {
        const val ERROR_TAG = "UserRepoError"
    }
}