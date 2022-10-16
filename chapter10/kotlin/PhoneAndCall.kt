import java.time.Duration
import java.time.LocalDateTime
import java.util.DoubleSummaryStatistics

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
 * (요구사항 추가) 통화요금에 대한 세금부과
 */
class Phone(private val amount:Int,
            private val seconds:Duration,
            private val taxRate:Double){
    private var calls:ArrayList<Call>  = ArrayList()
    fun getCalls():List<Call> = this.calls
    fun call(call:Call){
        this.calls.add(call)
    }

    fun calculateFee():Double {
        var result:Double = 0.0
        this.calls.forEach{
            var calculatedValue = amount.times(it.getDuration().seconds / this.seconds.seconds)
            result += calculatedValue
        }
        return result.plus(result.times(this.taxRate))
    }
}

/**
 * 기존의 Phone 클래스를 복사하여 관련내용만 조금 수정함
 * 아래와 같은 코드를 작성하면 구현시간은 절약이 가능하나, Phone, NightlyDiscountPhone 클래스 사이에 중복코드가 생성이 된다.
 * 이때에 생성된 중복코드로 인해 요구사항 변경시에 어떠한 일이 벌어질지 예상 불가능하다.
 *
 * (요구사항 추가) 통화요금에 대한 세금부과
 */
class NightlyDiscountPhone(
    val nightlyAmount:Int,
    val regularAmount:Int,
    val seconds: Duration,
    val taxRate: Double
){
    private val LATE_NIGHT_HOUR:Int = 22
    private val calls:ArrayList<Call> = ArrayList()

    fun call(call:Call){
        this.calls.add(call)
    }
    fun getCalls():List<Call> = this.calls

    fun calculateFee():Double {
        var result = 0.0
        this.calls.forEach{
            if(it.getFrom().hour >= this.LATE_NIGHT_HOUR){
                result += this.nightlyAmount.times(it.getDuration().seconds / this.seconds.seconds)
            }else{
                result += this.regularAmount.times(it.getDuration().seconds / this.seconds.seconds)
            }
        }
        return result.plus(result.times(this.taxRate))
    }
}



/**
 * 10초당 5원씩 부과되는 요금제에 가입한 사용자가 각각 1분동안 두번 통화를 한 경우
 *
 * (요구사항 추가) 통화요금에 대한 세금부과 - 코드 중복 발생()
 */
fun 요구사항() {
    val phone: Phone = Phone(5, Duration.ofSeconds(10), 0.03)
    val nightlyPhone = NightlyDiscountPhone(3,5, Duration.ofSeconds(10), 0.03)
    nightlyPhone.call(
        Call(
            LocalDateTime.of(2018, 1, 1, 22, 10, 0),
            LocalDateTime.of(2018, 1, 1, 22, 11, 0)
        )
    )

    nightlyPhone.call(
        Call(
            LocalDateTime.of(2018, 1, 2, 12, 10, 0),
            LocalDateTime.of(2018, 1, 2, 12, 11, 0)
        )
    )
    nightlyPhone.getCalls().forEach{
        phone.call(it)
    }

    println("일반 요금 : ${phone.calculateFee()}")
    println("심야할인 적용 요금 : ${nightlyPhone.calculateFee()}")

}


fun main(){
    요구사항()
}