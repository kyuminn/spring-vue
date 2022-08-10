<template>
  <!--
   부모에서 자식으로 데이터를 보낼때는 부모는 : , 자식은 props를 이용해 받을 수 있음.
   자식에서 부모로 데이터를 보낼때는 자식에서 this.$emit('method이름',보낼 값); 을 명시하고
   부모에서는 emit에서 적은 method이름(보낼값)을 통해서 얻을 수 있음
   -->
  <div id="app">
    <h1>모달 컴포넌트 만들기</h1>
    <!-- v-if=true여야 보임-->
    <child-for-modal v-if="show" :msg="message" :name="name" @close-modal="hideModal"
    @nameFromChild="nameShow"
    > <!--@close-modal="show=false"-->
    </child-for-modal>
    <button @click="show=true">Open Modal</button>
    {{ nameFromC }}
  </div>
</template>

<script>
import Child from "./Child";

export default {
  name: "App",
  components: {
    childForModal : Child,
  },
  data() {
    return {
      show: false,
      message :"Hello From Vue!!",
      name : 'testname', // 자식 컴포넌트 props로 내려주기 위한 용도, 내려준 다음에 자식에서 데이터를 얻어온다
      nameFromC : '',
    };
  },
  methods:{
    // viewModal(){
    //   this.show = !this.show;
    // },
    hideModal(){
      if(confirm("창을 닫으시겠습니까?")){
        this.show=false;
      }else{
        return;
      }
    },
    nameShow(value){ // e.target.value로 parameter가 전달되어 오나봄. parameter name은 아무거나 써도 됨
      this.nameFromC = value;
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

