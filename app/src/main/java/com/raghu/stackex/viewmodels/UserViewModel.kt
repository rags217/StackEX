package com.raghu.stackex.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.raghu.stackex.R
import com.raghu.stackex.models.User
import com.raghu.stackex.models.UserResponse
import com.raghu.stackex.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {

    var repository:UserRepository = UserRepository()
    val usersLiveData = MutableLiveData<ArrayList<User>>()
    val errorLiveData = MutableLiveData<Int>()

    fun getUses(searchString: String) {
        viewModelScope.launch {
            try {
                val response = repository.getUsers(searchString)
                if(response.code() == 404) {
                    errorLiveData.postValue(R.string.status_nw_error)
                    return@launch
                }

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val body =  response.body()
                        val bodyStr = String(body!!.bytes())
                        val usersResponse = Gson().fromJson(bodyStr, UserResponse::class.java)
                        Log.e(TAG, "Users size:".plus(usersResponse.items.size))
                        usersLiveData.postValue(usersResponse.items)
                    } else {
                        errorLiveData.postValue(R.string.status_error)
                    }
                }
            } catch (ex: Exception) {
                Log.e(TAG, "Request failed: \n ${ex.message}")
                if(ex.message!!.contains("Unable to resolve host"))
                    errorLiveData.postValue(R.string.status_unknown_host)
                else if(ex.message!!.contains("Unable to connect"))
                    errorLiveData.postValue(R.string.status_nw_error)
                else
                    errorLiveData.postValue(R.string.status_error)
            }
        }

    }

    companion object {
        private const val TAG = "UserViewModel"
    }
}