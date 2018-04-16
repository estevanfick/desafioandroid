package io.estevanfick.desafioandroid.ui.pullrequestlist

import android.support.v7.util.DiffUtil
import io.estevanfick.desafioandroid.data.model.PullRequest

class PullRequestDiff: DiffUtil.ItemCallback<PullRequest>(){
    override fun areItemsTheSame(oldItem: PullRequest?, newItem: PullRequest?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: PullRequest?, newItem: PullRequest?): Boolean {
        return oldItem == newItem
    }

}