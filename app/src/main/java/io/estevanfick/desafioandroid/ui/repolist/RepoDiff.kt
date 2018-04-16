package io.estevanfick.desafioandroid.ui.repolist

import android.support.v7.util.DiffUtil
import io.estevanfick.desafioandroid.data.model.Repo

class RepoDiff: DiffUtil.ItemCallback<Repo>(){
    override fun areItemsTheSame(oldItem: Repo?, newItem: Repo?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Repo?, newItem: Repo?): Boolean {
        return oldItem == newItem
    }

}