package com.raghu.stackex.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raghu.stackex.R
import com.raghu.stackex.adapters.UserAdapter
import com.raghu.stackex.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: UserViewModel
    lateinit var rvUsers: RecyclerView
    lateinit var adapter: UserAdapter

    override fun onClick(v: View?) {
        when(v) {
            search_bt -> {
                showStatus(R.string.status_searching)
                viewModel.getUses(search_et.text.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        search_bt.setOnClickListener(this)

        rvUsers = findViewById<View>(R.id.user_recycler_view) as RecyclerView

        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(UserViewModel::class.java)

        viewModel.usersLiveData.observe(this, Observer {
            it?.let { users ->
                if(users.size > 0) {
                    adapter = UserAdapter(users)
                    rvUsers.adapter = adapter
                    rvUsers.layoutManager = LinearLayoutManager(this)
                    showUsers()

                    adapter.setOnItemClickListener(object : UserAdapter.ClickListener {
                        override fun onItemClick(position: Int, v: View?) {
                            val userDetailIntent = Intent(this@UsersActivity, UserDetailActivity::class.java)
                            userDetailIntent.putExtra("userDetailObj", users.get(position))
                            startActivity(userDetailIntent)
                        }
                    })
                } else {
                    showStatus(R.string.status_empty)
                }
            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            it?.let { error ->
                showStatus(error)
            }
        })
    }

    private fun showUsers() {
        user_recycler_view.visibility = View.VISIBLE
        status_tv.visibility = View.GONE
    }

    private fun showStatus(status: Int) {
        user_recycler_view.visibility = View.GONE
        status_tv.setText(status)
        status_tv.visibility = View.VISIBLE
    }
}