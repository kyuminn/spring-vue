<template>
    <div>
      파일 업로드 - vue
      <br><br>
      이미지 업로드 :
      <!--v-on: 을 @ 로 축약해서 쓰기도 함 -->
      <input type="file" id="file1" @change="handleFileChange"><br>
      <input type="file" id="file2" @change="handleFileChange"><br>
      일반 텍스트:<input type="text" v-model="myText"><br><!-- 파일이랑 텍스트 같이 보내는 연습-->
      <br><br>
      파일과 dto 함께 보내는 경우 연습<br>
      이메일:
      <input type="text" v-model="email">
      <br><br>
      <button @click="sendFile">파일 전송</button>
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "MultiPart",
  data(){
    return {
      myFile1:'',
      myFile2:'',
      myText:'',
      email:'',
    }
  },
  methods:{
    handleFileChange(e){
      if(e.target.id==='file1'){
        this.myFile1 = e.target.files[0]; // 첨부한 첫번째 파일
      }else if(e.target.id==='file2'){
        this.myFile2 = e.target.files[0];
      }
    },
    sendFile(){

      let form = new FormData();
      console.log(this.myFile1.name);
      console.log(this.myFile2.name);
      form.append("myFile1",this.myFile1);
      form.append("myFile1", this.myFile2); // controller에서 value="myFile1"로 받기 때문에 같은 이름으로 추가해도 ok.
      form.append("myText",this.myText);

      const sendData = {
        'email': this.email
      }

      form.append("dto", JSON.stringify(sendData));

      axios.post('http://localhost:8080/fileTest',form,{
        headers : {
          'Content-Type':'multipart/form-data; charset=UTF-8'
        }
      }).then((res)=>{
        console.log('data send success!!');
      })
        .catch((err)=>{
          console.log('data send failed TT');
          console.log(err);
        })
    }
  }
}
</script>

<style scoped>

</style>
