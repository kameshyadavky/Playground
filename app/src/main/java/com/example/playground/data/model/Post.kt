package com.example.playground.data.model

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property userId the unique identifier of the author of the post
 * @property id the unique identifier of the post
 * @property title the title of the post
 * @property body the content of the post
 */
data class Post(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val body: String?
)
/**
for two way databinding as a pojo class
class Post(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    body: String?
): BaseObservable(){
    @get: Bindable
    var body: String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.body)
        }

}
 **/