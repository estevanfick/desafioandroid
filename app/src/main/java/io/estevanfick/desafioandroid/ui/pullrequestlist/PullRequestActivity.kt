package io.estevanfick.desafioandroid.ui.pullrequestlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.estevanfick.desafioandroid.App
import io.estevanfick.desafioandroid.R
import io.estevanfick.desafioandroid.data.NetworkStatus
import io.estevanfick.desafioandroid.data.PullRequestRepository
import kotlinx.android.synthetic.main.activity_pull_requests.*
import javax.inject.Inject

class PullRequestActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: PullRequestRepository

    var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_requests)

        (application as App).appComponent.inject(this)

        val user = intent.getStringExtra("user")
        val repo = intent.getStringExtra("repo")

        title = repo

        recyclerPullRequest.layoutManager = LinearLayoutManager(this)
        val adapter = PullRequestAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(intent)
        }
        recyclerPullRequest.adapter = adapter
        recyclerPullRequest.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        val viewModel = ViewModelProviders.of(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return PullRequestViewModel(repository) as T
            }
        }).get(PullRequestViewModel::class.java)
        viewModel.loadData(user, repo)
        viewModel.pullRequests.observe(this, Observer {
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
        snackBar = Snackbar.make(layoutPullRequest, message, Snackbar.LENGTH_INDEFINITE)
        actionName.let {
            snackBar?.setAction(actionName) { action?.invoke() }
        }
        snackBar?.show()
    }

    fun hideSnackbar(){
        snackBar?.dismiss()
    }
}
