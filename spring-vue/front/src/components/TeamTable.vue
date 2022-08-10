<template>
<!--  https://hjh0827.tistory.com/72-->
  <div>
    <button type="button" @click="showCalendar">달력 보기</button>
    <br><br>
    <input type="date" v-model="dateRange.pickedDate">
    <button type="button" @click="search">날짜로 검색하기</button>
    <div v-if="showCal">
      <date-picker
        inline
        :editable="false"
        :lang="datepickerLang"
        @pick="calendarPick"

      />
      <!-- TeamTable.vue 가 부모 컴포넌트이고 DatePicker가 자식 컴포넌트임

       -->
    </div>
    <br><br>

    {{dateRange.pickedDate}}

    <table>
      <thead>
          <th>팀이름</th>
          <th>가입일</th>
      </thead>
      <tbody>
        <tr v-for="item in this.teamList">
          <td>{{item.name}}</td>
          <td>{{item.estbdate}}</td>
          <!--<td>{{ $moment(item.estbdate).format('YYYY-MM-DD')}}</td>--> <!--vue-moment 사용-->
        </tr>
      </tbody>
    </table>
    <button @click="excel">excel 다운로드</button>
  </div>
</template>

<script>
import axios from "axios";
import excelDownload from "../js/common";
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";

export default {
  name: "TestTable.vue",
  components:{
    DatePicker
  },
  data(){
    return {
      teamList : [],
      showCal : false,
      dateRange :{
        // main.js에서 vue-moment global 설정 했으므로 $moment로 접근할 수 있음.
        pickedDate : this.$moment().format('YYYY-MM-DD') //default 값 지정(오늘 날짜), 포맷으로 형식 지정.
      },
      // 달력 내부에서 사용할 언어와 , 월일 순서 배치
      datepickerLang:{
        yearFormat: "YYYY년",
        monthFormat: "M월",
        monthBeforeYear: false,
        days:["일","월","화","수","목","금","토"]

      }
    }
  },
  mounted() {
    this.search();
  },
  methods:{
    excel(){
      excelDownload(this.teamList);
    },
    showCalendar(){
      this.showCal=!this.showCal;
    },
    calendarPick(item) {
      this.dateRange.pickedDate = this.$moment(item).format("YYYY-MM-DD");
    },
    search(){
      const paramData = {
        estbdate : this.dateRange.pickedDate
      };
      axios.post("http://localhost:8080/searchDate",paramData)
        .then((res)=>{
          this.teamList = this.manufacDate(res.data);
        })
        .catch((err)=>{
          console.log(err);
        })
    },
    // 연도, 월, 날짜가 array에 담겨서 각각 옴 [2022,6,30] 이런식으로. vue-moment를 사용하면 쉽게 포맷을 바꿔서 보여줄 순 있음 (위의 주석처리한 td 참고)
    // 하지만 vue-moment를 사용하면 엑셀에 안나옴, 게다가 vue-moment를 사용하면 달(month) 값을 모두 -1 해줘야함.
    // 그래서 일단 다 가공함
    manufacDate(data){
      for(let i =0 ; i<data.length; i++){
        let year = data[i].estbdate[0];
        let month = data[i].estbdate[1];
        let day = data[i].estbdate[2];
        if(month<10){
          month = "0"+month;
        }
        if(day<10){
          day = "0"+day;
        }
        data[i].estbdate = year+"-"+month+"-"+day;
      }
      return data;
    },
  }

}


</script>

<style scoped>
    table {
      width: 300px;
      margin:auto;
    }

    table,tr,td {
      border: 1px solid black
    }
</style>
