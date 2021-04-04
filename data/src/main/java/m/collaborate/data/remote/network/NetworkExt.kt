package m.collaborate.data.remote.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import m.collaborate.data.remote.model.wrapper.ResWrapper
import retrofit2.HttpException

suspend fun <T : Any> safeAPi(apiCall: suspend () -> T): ResWrapper<T> {
    return withContext(Dispatchers.IO) {
        try {
            ResWrapper.Success(apiCall.invoke())
        } catch (t: Throwable) {
            when(t) {
                is HttpException -> {
                    ResWrapper.Error(isNetworkError = false, errorCode = t.code(), errorBody = t.response()?.errorBody())
                }
                else -> {
                    ResWrapper.Error(true, null, null)
                }
            }
        }
    }
}