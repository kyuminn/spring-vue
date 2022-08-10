<template>
  <!-- vue의 경우 root tag 인 div 안에 모든 것을 작성해야 함.-->
  <div>
    <h2>Member table</h2>
    이메일:<input @keyup.enter="searchByEmail" type="text" v-model="searchValue">
    비밀번호:<input @keyup.enter="searchByEmail" type="text" v-model="searchValue2">
    <button @click="searchByEmail">검색</button>

  <table>
    <thead>
      <th>email</th>
      <th>password</th>
    </thead>
    <tbody>
      <tr v-for="item in memberList">
<!--        <td><router-link :to="`/detail/${item.email}`">{{item.email}}</router-link></td>-->
        <td><router-link :to="{ name : 'MemberDetail', params:{email :`${item.email}`}}">{{item.email}}</router-link></td> <!-- 두개 중 아무거나 사용 가능-->
        <td>{{item.password}}</td>
      </tr>
    </tbody>
  </table>
    <!-- with query, resulting in `/register?plan=private` -->
<!--    <router-link :to="{ path: '/register', query: { plan: 'private' }}">-->
    <!--
    1. router-link default는 path
    <router-link to="해당 url url">

    2. router-link 에서 v-bind를 통해 속성(attribute) 전달 가능
    attribute는 router 디렉터리 하위의 index.js에 정의된 값
    <router-link :to="{name : '컴포넌트 이름', path : '해당 url', params: {}, query:{}}"></router-link>
    params는 pathVariable로 들어감
    query는 queryString으로 들어감

    -->
<!--    <router-link :to="{name : '컴포넌트 이름', path : '해당 url', params: {}, query:{}}"></router-link>-->
  <br>
    <button @click="excel">excel 다운로드 하기</button>

    <button @click="rest">restful test</button>
  </div>
</template>

<script>
import axios from "axios"
import excelDownload from "../js/common";
export default {
  name: "table.vue",
  data(){
    return {
      memberList:[],
      searchValue :'',
      searchValue2 :'',
      restList:[],
    }
  },
  mounted() {
    axios.get('http://localhost:8080/member')
      .then((res)=>{
        this.memberList = res.data;
        console.log(JSON.stringify(this.memberList));
      })
      .catch((err)=>{
        console.log(err);
      })
  },
  methods: {
    excel(){
      excelDownload(this.memberList);
    },
    searchByEmail(){
      const paramData = {
        "email": this.searchValue,
        "password": this.searchValue2
      }
      console.log(JSON.stringify(paramData))
      // post로 보낸 요청은 컨트롤러에서 @ReqeustBody로 받을 수 있다
      axios.post('http://localhost:8080/member',paramData)
        .then((res)=>{
          this.memberList = res.data;
        })
        .catch((err)=>{
          console.log('err'+err);
        })
    },
    rest(){
      axios.get('http://localhost:8080/restful')
      .then((res)=>{
        console.log(res.data);
        this.restList = res.data;
        console.log(this.restList.length);
        this.memberList = this.restList;
      })
      .catch((err)=>{
        console.log(err);
        console.log(err.response);
        console.log(err.response.headers);
      })
    },
    /*
    Restful API 요청과 응답
    Vue 에서 axios를 이용해서 http 통신을 할 때
    http request가 성공했을 때(status가 2xx대)는 response 객체의 data 항목에 httpbody가 담겨져 오고 ,
    실패했을 때는 error 객체의 response 항목에 httpbody가 담겨져 온다.
    http status가 200번대가 아니면 무조건 catch(error)쪽을 탐. (커스터마이징할 수 있음)
    예) then((res)=>{
          console.log(res.data); //body
          console.log(res.headers); //header
          console.log(res.status); //status
    })
        catch((err)=>{
           console.log(err.response) //body
           console.log(err.response.headers) //header
           console.log(err.response.status) // status
        })
        이 외에도 console로 찍어서 원하는 값을 .으로 접근할 수 있음.
    */
  }
}


</script>

<style scoped>
  table {
    width: 400px;
    margin: auto;
  }

  tablem, td, th {
    border: 1px solid black;
  }
</style>
