package io.estevanfick.desafioandroid.ui.pullrequestlist

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.estevanfick.desafioandroid.R
import io.estevanfick.desafioandroid.data.model.PullRequest
import io.estevanfick.desafioandroid.ext.toFormat
import kotlinx.android.synthetic.main.list_item_pull_request.view.*


class PullRequestAdapter(val listener: (String) -> Unit): ListAdapter<PullRequest, PullRequestAdapter.ViewHolder>(PullRequestDiff()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_pull_request, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pullRequest: PullRequest, listener: (String) -> Unit) {
            itemView.txtPullRequestTitle.text = pullRequest.title
            itemView.txtPullRequestBody.text = pullRequest.body
            itemView.txtPullRequestDate.text = pullRequest.date.toFormat()
            itemView.txtPullRequestAuthor.text = pullRequest.user.login
            Picasso.get()
                    .load(pullRequest.user.avatarUrl)
                    .into(itemView.imgPullRequestAuthor)
            itemView.setOnClickListener {
                listener(pullRequest.url)
            }
        }
    }
}