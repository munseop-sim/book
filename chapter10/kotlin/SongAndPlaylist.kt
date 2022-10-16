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
open class Playlist(){
    private val tracks:ArrayList<Song> = ArrayList()

    fun getTracks():List<Song> = this.tracks
    fun append(song:Song) = this.tracks.add(song)
}


/**
 * 플레이스트에서 삭제 기능이 추가된 PersonalPlaylist 클래스
 */
class PersonalPlaylist(): Playlist() {
    fun remove(song:Song){
        (getTracks() as ArrayList).remove(song)
    }
}
