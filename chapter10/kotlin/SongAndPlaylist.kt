package chapter10.kotlin

/**
 * 초기 요구사항으로 인해 작성된 코드
 */
class Song(private val singer:String,private val title:String){
    fun getSinger():String = this.singer
    fun getTitle():String= this.title
}

/**
 * 초기 요구사항으로 인해 작성된 코드
 */
class Playlist(){
    private val tracks:ArrayList<Song> = ArrayList()

    fun getTracks():List<Song> = this.tracks
    fun append(song:Song) = this.tracks.add(song)
}