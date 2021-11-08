package learn.android.getdatafromgit.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import learn.android.getdatafromgit.databinding.RecycleritemUserRepoBinding
import learn.android.getdatafromgit.model.UserRepoItem

class UserRepoAdapter : ListAdapter<UserRepoItem, UserRepoHolder>(DiffCallBack())
{
    private class DiffCallBack : DiffUtil.ItemCallback<UserRepoItem>() {
        override fun areItemsTheSame(
            oldItem: UserRepoItem,
            newItem: UserRepoItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserRepoItem,
            newItem: UserRepoItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setList(userRepoList: List<UserRepoItem>?) {
        if (userRepoList != null) {
            submitList(userRepoList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecycleritemUserRepoBinding.inflate(inflater, parent, false)

        return UserRepoHolder(binding)
    }

    override fun onBindViewHolder(userRepoHolder: UserRepoHolder, position: Int) {
        userRepoHolder.makeBinding(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}