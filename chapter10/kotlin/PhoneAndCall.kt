import java.time.Duration
import java.time.LocalDateTime

/**
 * 개별 통화 기간을 저장하는 Call 클래스
 */
class Call(private val from: LocalDateTime,
           private val to:LocalDateTime){
    fun getDuration(): Duration = Duration.between(from, to)
    fun getFrom():LocalDateTime = this.from
}

/**
 * 통화요금 계산을 위한 Phone 클래스 (최초 요구사항)
 */
class Phone(private val amount:Int,
            private val seconds:Duration){
    private var calls:ArrayList<Call>  = ArrayList()

    fun call(call:Call){
        this.calls.add(call)
    }

    fun calculateFee():Int {
        var result:Int = 0
        this.calls.forEach{
            var calculatedValue = amount.times(it.getDuration().seconds / this.seconds.seconds).toInt()
            result += calculatedValue
        }
        return result
    }
}

/**
 * 10초당 5원씩 부과되는 요금제에 가입한 사용자가 각각 1분동안 두번 통화를 한 경우
 */
fun 최초_요구사항() {
    val phone: Phone = Phone(5, Duration.ofSeconds(10))

    phone.call(
        Call(
            LocalDateTime.of(2018, 1, 1, 12, 10, 0),
            LocalDateTime.of(2018, 1, 1, 12, 11, 0)
        )
    )

    phone.call(
        Call(
            LocalDateTime.of(2018, 1, 2, 12, 10, 0),
            LocalDateTime.of(2018, 1, 2, 12, 11, 0)
        )
    )

    println(phone.calculateFee())
}

//심야할인 요금제 시행으로 인한 클래스 추가
//기존의 Phone 클래스를 복사하여 관련내용만 조금 수정함
//아래와 같은 코드를 작성하면 구현시간은 절약이 가능하나, Phone, NightlyDiscountPhone 클래스 사이에 중복코드가 생성이 된다.
//이때에 생성된 중복코드로 인해 요구사항 변경시에 어떠한 일이 벌어질지 예상 불가능하다.
class NightlyDiscountPhone(
    val nightlyAmount:Int,
    val regularAmount:Int,
    val seconds: Duration
){
    private val LATE_NIGHT_HOUR:Int = 22
    private val calls:ArrayList<Call> = ArrayList()

    fun call(call:Call){
        this.calls.add(call)
    }

    fun calculateFee():Int {
        var result:Int = 0
        this.calls.forEach{
            if(it.getFrom().hour >= this.LATE_NIGHT_HOUR){
                result += this.nightlyAmount.times(it.getDuration().seconds / this.seconds.seconds).toInt()
            }else{
                result += this.regularAmount.times(it.getDuration().seconds / this.seconds.seconds).toInt()
            }
        }
        return result
    }
}