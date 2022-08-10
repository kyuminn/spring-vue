<template>
  <div>
    <h2>상세 페이지</h2>
    <!-- vue.js input 데이터 바인딩 태그 속성에 v-bind: 또는 : 라고 쓰면 아래 data() 안에 있는 값과 연결됨.-->
<!--    v-bind:value="member.email"-->
    email:<input type="text" v-model="member.email">
    <br>
    password:<input type="password" v-bind:value="member.password">
    <button @click="edit1">수정</button>
    <button @click="delete3">멤버 삭제</button>
    <!-- default method 주의..$delete()라는 함수가 내부에 이미 있음
    cf: Vue에서 $는 전역을 의미. public 이라고 생각하면 될듯.
    -->
<!--    잘 나옴-->
<!--    {{this.member.email}}-->
<!--    {{this.member.password}}-->

  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "MemberDetail.vue",
  data(){
    return {
        member : {},
    }
  },
  mounted() {
    const pathVar = this.$route.params.email; //pathVariable에 있는 email 가져옴
    console.log('pathVar:'+pathVar);
    axios.get("http://localhost:8080/member/detail",{
      params:{
        email : pathVar // 경로에서 ?email=value 형식으로 쿼리스트링으로 전달됨
      }
    }).then((res)=>{
        this.member = res.data;
        console.log(JSON.stringify(this.member));
      })
      .catch((err)=>{
        console.log('err'+err);
      })
  },
  methods:{
    edit1(){
      // id 따로 안만들어서 불가능.......... console에 잘나오는지만 확인하고 수정은 team으로 해야할듯
      console.log('edit email={}',this.member.email); //수정된 이메일 잘 나옴..
      axios.put('http://localhost:8080/member',{
        "email": this.member.email
      })
      .then((res)=>{
        console.log(res.data);
      })
    },
    delete1(){
      const paramData = {
        "email" : this.member.email
      }
      axios.post('http://localhost:8080/member/delete',paramData)
        .then((res)=>{
          console.log(res.data); // true or false
          alert(res.data);
        })
        .catch((err)=>{
          console.log(err);
        })
    },
    // axios : get,post,delete,put(수정) 4가지 방식이 있음
    // get과 delete 사용방법 동일 / post와 put 사용방법 동일
    // get과 delete의 경우 param을 적어주면 쿼리스트링으로 들어가고, httpbody에 전송하고 싶으면 delete2와 같이 data 속성을 한번 더 감싸주면 된다
    // put과 post의 경우 바로 param 적어주면 됨
    delete2(){
      const params = {
        "email": this.member.email
      }
      axios.delete('http://localhost:8080/member',{
        data : { //booy로 보내는 방식
          "email": this.member.email
        }
      })
      .then((res)=>{
        console.log(res.data);
      })
    },
    delete3(){
      const sendData = {
        "email": this.member.email
      }
      axios.delete('http://localhost:8080/members',{
        //params: sendData 로 가능
        params :{
          "email": this.member.email
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
