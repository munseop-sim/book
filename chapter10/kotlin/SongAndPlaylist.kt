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
 *
 * (요구사항 추가) playlist에서 노래의 목록뿐만 아니라 가수별 노래의 제목도 함께 관리하도록 변경
 */
open class Playlist(){
    private val tracks:ArrayList<Song> = ArrayList()
    private val singers: HashMap<String, String> = HashMap<String,String>()

    fun getTracks():ArrayList<Song> = this.tracks
    fun getSingers():HashMap<String,String> = this.singers

    fun append(song:Song) {
        this.tracks.add(song)
        this.singers.put(song.getSinger(), song.getTitle())
    }
}


/**
 * 플레이스트에서 삭제 기능이 추가된 PersonalPlaylist 클래스
 *
 * Playlist에 singers 컬럼이 추가되었으므로, 정상적으로 작동하기 위해서는 remove함수도 수정해야한다.
 * -> 부모클래스의 변경으로 인해 자식클래스도 변경해야됨을 알 수 있다.
 *
 */
class PersonalPlaylist(): Playlist() {
    fun remove(song:Song){
        getTracks().remove(song)
        getSingers().remove(song.getSinger())
    }
}
