package io.estevanfick.desafioandroid.ui.repolist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.estevanfick.desafioandroid.App
import io.estevanfick.desafioandroid.R
import io.estevanfick.desafioandroid.data.NetworkStatus
import io.estevanfick.desafioandroid.data.RepoRepository
import io.estevanfick.desafioandroid.ui.pullrequestlist.PullRequestActivity
import kotlinx.android.synthetic.main.activity_repo.*
import javax.inject.Inject

class RepoActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: RepoRepository

    var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        (application as App).appComponent.inject(this)

        recyclerRepository.layoutManager = LinearLayoutManager(this)
        val adapter = RepoAdapter {
            val intent = Intent(this, PullRequestActivity::class.java)
            intent.putExtra("user", it.owner.login)
            intent.putExtra("repo", it.name)
            startActivity(intent)
        }
        recyclerRepository.adapter = adapter
        recyclerRepository.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return RepoViewModel(repository) as T
            }
        }).get(RepoViewModel::class.java)
        viewModel.pagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.networkStatus.observe(this, Observer {
            when (it) {
                NetworkStatus.RUNNING -> {
                    showSnackbar("Loading...")
                }
                NetworkStatus.SUCCESS -> {
                    hideSnackbar()
                }
                NetworkStatus.FAILED -> {
                    showSnackbar("Internet Error", "Close"){
                        hideSnackbar()
                    }
                }
            }
        })
    }

    fun showSnackbar(message: String, actionName: String? = null, action: (() -> Unit)? = null ) {
        snackBar = Snackbar.make(layoutRepo, message, Snackbar.LENGTH_INDEFINITE)
        actionName.let {
            snackBar?.setAction(actionName) { action?.invoke() }
        }
        snackBar?.show()
    }

    fun hideSnackbar(){
        snackBar?.dismiss()
    }
}
