package learn.android.getdatafromgit.recycler

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import learn.android.getdatafromgit.databinding.RecycleritemUserRepoBinding
import learn.android.getdatafromgit.model.UserRepoItem

class UserRepoHolder(private val binding: RecycleritemUserRepoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun makeBinding(userRep : UserRepoItem) {
        Log.d("UserRero", userRep.name)
        binding.userRepoItem.text = userRep.name
    }
}

