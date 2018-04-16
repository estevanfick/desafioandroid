package io.estevanfick.desafioandroid.ui.repolist

import android.arch.paging.PagedListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.estevanfick.desafioandroid.R
import io.estevanfick.desafioandroid.data.model.Repo
import kotlinx.android.synthetic.main.list_item_repo.view.*

class RepoAdapter(val listener: (Repo) -> Unit): PagedListAdapter<Repo, RepoAdapter.ViewHolder>(RepoDiff()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repo: Repo, listener: (Repo) -> Unit) {
            itemView.txtRepositoryName.text = repo.name
            itemView.txtRepositoryDescription.text = repo.description
            itemView.txtRepositoryStars.text = repo.stars.toString()
            itemView.txtRepositoryForks.text = repo.forks.toString()
            itemView.txtRepositoryUserName.text = repo.owner.login
            Picasso.get()
                    .load(repo.owner.avatarUrl)
                    .into(itemView.imgAuthor)
            itemView.setOnClickListener {
                listener(repo)

            }
        }
    }
}