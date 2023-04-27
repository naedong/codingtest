import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    var minerals = arrayOf("diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond")
    var picks = intArrayOf(0, 1, 1)

    println(solution(picks, minerals))
}

            // picks [ dia , iron, stone ]
fun solution(picks : IntArray, minerals : Array<String>) : Int {
    var answer : Int = 0
    picks[1] = picks[1] * 5
    picks[2] = picks[2] * 5
    var mineralCount  = 0


    if(picks[0] * 5 != 0){
        if(minerals.size > picks[0] * 5) { mineralCount  += 5
            answer += picks[0] * 5 }
        else {
            answer = minerals.size
            mineralCount  += minerals.size
        }
    }
    if(mineralCount  != 0 && mineralCount  < minerals.size) {
        for( a in minerals.indices ){
            if(minerals.get(a) == "diamond"){
                mineralCount  ++
                answer += 5
                picks[1] = picks[1] - 1
            }else{
                mineralCount  ++
                answer ++
                picks[1] = picks[1]  - 1
            }

            if(picks[1] == 0){
                break
            }
        }

    }
    else if (mineralCount  == 0){

        for( a in mineralCount..minerals.size -1 ){
            if(minerals.get(a) == "diamond"){
                mineralCount  ++
                answer += 5
                picks[1] = picks[1] - 1
            }else {
                mineralCount  ++
                answer ++
                picks[1] = picks[1] - 1
            }

            if(picks[1] == 0){
                break
            }
        }
    }
   if(picks[1] == 0 && mineralCount  < minerals.size){
       for ( a in mineralCount ..minerals.size - 1 ){
           if(minerals.get(a) == "stone"){
               picks[2] = picks[2] - 1
               answer ++
               mineralCount  ++
           }
           else {
               mineralCount  ++
               picks[2] = picks[2] - 1
               answer += 5
           }
           if(picks[2] == 0){
               break
           }
       }
   }


    return answer
}

fun editsolution(picks: IntArray, minerals: List<String>): Int {
    // 각 곡괭이별로 캘 수 있는 광물 수를 저장하는 맵을 만듦
    val pickMap = mutableMapOf("diamond" to picks[0], "iron" to picks[1], "stone" to picks[2])
    // 광물별로 캘 때 필요한 피로도를 저장하는 맵을 만듦
    val fatigueMap = mapOf("diamond" to 3, "iron" to 5, "stone" to 1)

    var totalFatigue = 0 // 총 피로도
    var currentPick = "" // 현재 사용중인 곡괭이
    var pickCount = 0 // 현재 사용한 곡괭이로 캔 광물 수
    for (mineral in minerals) {
        if (currentPick != mineral) { // 현재 사용중인 곡괭이와 캘 광물이 다르면
            currentPick = mineral // 곡괭이를 바꿔서 광물을 캠
            pickCount = 0 // 새로운 곡괭이로 캔 광물 수 초기화
        }
        // 현재 곡괭이로 더이상 캘 수 없으면 다른 곡괭이를 선택
        while (pickCount == 5 || pickMap[currentPick] == 0) {
            currentPick = pickMap.filterValues { it > 0 }.keys.first() // 사용 가능한 곡괭이 중 첫 번째 선택
            pickCount = 0 // 새로운 곡괭이로 캔 광물 수 초기화
        }
        totalFatigue += fatigueMap[mineral]!! // 광물을 캘 때 필요한 피로도 추가
        pickMap[currentPick] = pickMap[currentPick]!! - 1 // 사용한 곡괭이 개수 감소
        pickCount++ // 현재 곡괭이로 캔 광물 수 증가
    }
    return totalFatigue
}