<template>
    <div>
      <h3>New Component Test</h3>
      <p>{{ text }}</p>
      <button @click="showText">buttons</button>
      <button @click="backend">backend-get</button>
      <button @click="list1">list-get</button>

      <br><br>

      <input type="text" v-model="email">
      <button @click="list2">list-post</button>
      <br><br>

<!--      <li v-for="item in list">-->
<!--        {{item.email}} <br>-->
<!--        {{item.password}} <br>-->
<!--      </li>-->

      <ul v-for="item in list">
        <li>{{item.email}}</li>
        <li>{{item.password}}</li>
      </ul>

      <br><br>

      <input type="checkbox" v-model="checked">
      {{ checked }}
      <br><br>

      <select v-model="selected">
        <option disabled value="">선택하세요</option>
        <option>a</option>
        <option>b</option>
        <option>c</option>
      </select><br>
      선택한 항목:{{ selected }}

    </div>
</template>

<script>

import axios from 'axios'

export default {
  name: "AxiosTest",
  data() { // data 초기화
    return {
      text: 'hello world',
      list: [],
      email: '', // input type 의 값을 받아올 것이기 때문에 초기화만
      checked:false,
      selected:''

    }
  },
  mounted(){
    this.list1() // jquery의 document.ready(function()) 이랑 비슷한 역할인 듯.
  },
  methods: { // data 혹은 템플릿 내부의 값 접근시 this로 접근
    showText() {
      this.text = 'hello world 2'
    },
    backend(){
      axios.get('http://localhost:8080/axiosTest')
        .then((res) => {
          alert(res.data); //controller가 return한 data res.data로 응답할수 있음
          console.log(res.status);
          console.log(res.config);
          console.log(res.data);
          console.log(res.headers);
          console.log(res.request);
          console.log(res.statusText);
        })
        .catch((err) => {
          console.log(err);
        })
    },
    list1() {
      axios.get("http://localhost:8080/list")
        .then((res) => {
          //console.log(res.data); //controller가 return한 json 형식의 데이터(@ResponseBody) res.data 로 가져올수 있음
          this.list = res.data;
          console.log(JSON.stringify(this.list));
        })
        .catch((err) => {
          console.log('list err' + err);
        })
    },
    list2(){
      console.log(this.email); // input 값 확인
      let paramData = {
        email: this.email
      }
      axios.post('http://localhost:8080/post', paramData)
        .then((res)=>{
          alert(res.data);
        })
    }
  }
}

</script>

<style scoped>
  div {
    padding: 20px;
  }
  div h3 {
    margin: 20px;
  }
</style>
