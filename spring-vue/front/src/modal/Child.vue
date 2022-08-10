<template>
  <div class="modal">
    <div class="overlay" @click="$emit('close-modal')"></div>
    <!--부모 component에 정의된 메서드를 호출하는듯..-->
    <div class="modal-card">
    {{ msg }}
      <br>
      부모->자식 전달 테스트 : 이름: <input type="text" v-model="name" readonly>
      <br><br>
<!--      만약 readonly가 아니라 자식에서 값을 수정할 수 있게 하려면 @input 사용하기-->
<!--      https://whitepro.tistory.com/255-->
      자식->부모 전달 테스트 : 이름 <input type="text" v-model="name2"><!--@input="sendName"-->
      <button @click="sendName">전송!!</button>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
    name : "Child",
  // 부모에서 보낸 데이터 받기- props 사용
    props : {
      msg :{
        type:String,
        required:false
      },
      name:{
        type:String,
        required:true
      }
    },
  data(){
      return {
        name2:'',
      }
  },
  methods:{
      sendName(e){
        //@input 사용할 때는
        // this.$emit('nameFromChild',e.target.value);

        this.$emit('nameFromChild',this.name2);
        const paramData = {
          name : this.name2
        };
        axios.post('http://localhost:8080/popup',paramData)
          .then((res)=>{
            console.log(res.data);
          })
          .catch((err)=>{
            console.log(err);
          })
        this.$emit('close-modal');
      }
  }
};
</script>

<style>
/* Modal */
.modal,
.overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
}
.overlay {
  opacity: 0.5;
  background-color: black;
}
.modal-card {
  position: relative;
  max-width: 50%;
  margin: auto;
  margin-top: 50px;
  padding: 20px;
  background-color: white;
  min-height: 500px;
  z-index: 10;
  opacity: 1;
}
</style>
