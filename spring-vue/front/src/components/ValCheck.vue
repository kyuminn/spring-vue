<template>
  <div>
<!--    https://kr.vuejs.org/v2/guide/forms.html-->
<!--    라디오 버튼은 하나만 선택해야 하고, 체크박스는 여러개 선택해도 됨. 체크박스 여러개 선택하고 싶을 경우 같은 이름으로 v-model 지정 후 -->
<!--    데이터에서 빈 리스트로 받으면 됨-->

    텍스트:<input type="text" v-model="myText" ref="text"> 입력한 값 : {{myText}}<br>
    체크박스:<input type="checkbox" v-model="checkbox">{{checkbox}}<br>
<!--    라디오 버튼1:One:<input type="radio" @change="radioChange" value="one" v-model="checkedRadio">-->
<!--    Two:<input type="radio" @change="radioChange" value="two" v-model="checkedRadio">-->
    라디오 버튼1:One:<input type="radio" value="one" v-model="checkedRadio">
    Two:<input type="radio" value="two" v-model="checkedRadio"> {{checkedRadio}}
    전체체크 해제<button type="button" @click="disableRadio">버튼</button><br>
    <!--순수 javascript에서는 querySelector를 이용하여, vue에서는 ref 를 사용하여 element에 접근할 수 있음.
    js의 querySelector,getElementById 와 같은 역할임.
     vue의 목적중에는 개발자가 실제 DOM을 다루지 않게 하는 것이 있기 때문에 (Virtual DOM으로 인해)  , 되도록이면 $ref를 쓰지 않는 것이 좋음.. 그럼 어쩌라고-->
    파일:<input type="file" @change="fileInput" ref="fileUpload"><br>
    이메일:<input type="email" v-model="email"><br>
    <select v-model="selected">
      <option disabled value="">선택하세요</option>
      <option>a</option>
      <option>b</option>
      <option>c</option>
    </select>
    {{selected}}
 <br><br>
    <br><br>
    체크박스 여러개 리스트에 담아보기
    <input type="checkbox" value="check1" v-model="checkList">
    <input type="checkbox" value="check2" v-model="checkList">
    <input type="checkbox" value="check3" v-model="checkList">
    <br>
    체크한 이름: {{ checkList }}
    <br><br>
  <button type="button" @click="valCheck">유효성 검사</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ValCheck",
  data(){
    return {
      myText:'',
      checkbox:false,
      checkedRadio:'',
      file:null,
      email:'',
      selected:'',
      checkList:[],
    }
  },
  mounted() {
    axios.get('http://localhost:8080/sessionCheck')
      .then((res)=>{
        console.log(res.data.session);
        console.log(res.data.sessionId);
      })
      .catch((err)=>{
        console.log(err);
      })
  },
  methods:{
    fileInput(e){
      // 파일 용량 체크 후 파일 초기화 ..
      if(e.target.files[0].size > 1024*1024*2){
        alert('파일은 2MB 이하만 첨부 가능합니다');
        // 파일 선택 옆에 나오지 않도록 파일 초기화
        this.$refs.fileUpload.value = null; //실제 dom 접근 후 value 초기화 (파일 선택 버튼 옆에 파일명 안 나오도록..)
        this.file = null; // 가상 dom value 초기화. ( valCheck()에서의 유효성 검사 위해!)
      }else{
        this.file = e.target.files[0];
      }
      // 파일 확장자 체크
      // lastIndexOf : 단어 끝에서 처음 나오는 값의 위치를 처음부터 시작한 index로 표현
      // 예시 : example.xslx 인 경우 끝에서부터 처음 만나는 . 을 찾고, 그 .의 index는 7!
      let fileLength = this.file.name.length;
      let lastDot = this.file.name.lastIndexOf('.');
      let fileExt = this.file.name.substring(lastDot,fileLength).toLowerCase();
      console.log(fileExt);
      if(!this.checkFileExt(fileExt)){
        alert('사용 가능한 확장자가 아닙니다.')
        this.$refs.fileUpload.value = null;
        this.file = null;
      };
      },
    checkFileExt(ext){
      let extArray = ['.xlsx','.ppt'];
      for(let i=0; i<extArray.length;i++){
        if(extArray[i]=== ext){
          return true
        }
        return false;
      }
    },
    disableRadio(){
      // radio button, selected => v-model 사용시 각각 value와 option value가 바인딩됨. checkbox의 경우 체크박스 체크 여부 또는 체크한 value가 바인딩됨
      this.checkedRadio="";
    },
    valCheck(){
      if(this.myText===''){
        alert('text 값을 입력해 주세요');
        //focus - ref 설정 해줘야함
        this.$refs.text.focus();
        return;
      };
      if(this.checkbox===false){
        alert('check 해주세요');
        return;
      };
      if(this.file===null){
        alert('파일을 첨부해주세요');
        return;
      };
      if(this.selected===""){
        alert('선택해주세요');
        return;
      };
      let regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
      if(!regExp.test(this.email)){
        alert('이메일 양식에 맞게 입력해주세요');
        return;
      }
      if(this.checkedRadio===''){
        alert('라디오를 선택해주세요~');
        return;
      }
    }
  },
}
</script>

<style scoped>

</style>
