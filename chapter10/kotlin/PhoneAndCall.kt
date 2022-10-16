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
 *
 * 차이를 메서드로 추출
 */
open class Phone(val amount:Double,val seconds:Duration){
    private val calls:ArrayList<Call> = ArrayList()

    fun getCalls():List<Call> = this.calls
    fun call(call:Call){
        this.calls.add(call)
    }

    fun calculateFee():Double {
        var result:Double = 0.0
        this.calls.forEach{
            result += calculateCallFee(it)
         }
        return result
    }

    private fun calculateCallFee(call:Call):Double = this.amount.times(call.getDuration().seconds / this.seconds.seconds)

}

class NightlyDiscountPhone(
    private val nightlyAmount: Double,
    private val regularAmount: Double,
    private val seconds: Duration){

    private val LATE_NIGHT_HOUR:Int = 22
    private val calls:ArrayList<Call> = ArrayList()
    fun getCalls():List<Call> = this.calls
    fun call(call:Call){
        this.calls.add(call)
    }
    fun calculateFee(): Double {
        var result:Double = 0.0

        this.calls.forEach{
            result += calculateCallFee(it)
        }
        return result
    }

    private fun calculateCallFee(call:Call):Double{
        return if(call.getFrom().hour >= this.LATE_NIGHT_HOUR){
            return this.nightlyAmount.times(call.getDuration().seconds / this.seconds.seconds)
        }else{
            return this.regularAmount.times(call.getDuration().seconds / this.seconds.seconds)
        }
    }
}



/**
 * 10초당 5원씩 부과되는 요금제에 가입한 사용자가 각각 1분동안 두번 통화를 한 경우
 *
 * (요구사항 추가) 통화요금에 대한 세금부과 - 코드 중복 발생()
 * (코드개선) 중복코드를 피하기 위해 타입코드를 사용하여 클래스를 합침. 하지만 타입코드를 사용함으로 인해 낮은 응집도와 높은 결합또를 가지는 구조가 되었음
 */
fun 요구사항() {
    val phone: Phone = Phone(5.0, Duration.ofSeconds(10))
    val nightlyPhone = NightlyDiscountPhone(3.0,5.0, Duration.ofSeconds(10))
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