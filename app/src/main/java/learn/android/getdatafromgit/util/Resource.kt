package learn.android.getdatafromgit.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Empty<T>() : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(message = message)
    class ShowProgress<T>():Resource<T>()
    class HideProgress<T>():Resource<T>()
    class ShowMessage<T>(message: String):Resource<T>(message = message)
}