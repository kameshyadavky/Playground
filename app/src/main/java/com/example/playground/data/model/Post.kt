package com.example.playground.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property userId the unique identifier of the author of the post
 * @property id the unique identifier of the post
 * @property title the title of the post
 * @property body the content of the post
 */
@Entity
data class Post(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "userId")val userId: Int?,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name="body")val body: String?
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