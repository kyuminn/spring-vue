<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
<!--    <h2>Essential Links</h2>-->
<!--    <ul>-->
<!--      <li>-->
<!--        <a-->
<!--          href="https://vuejs.org"-->
<!--          target="_blank"-->
<!--        >-->
<!--          Core Docs-->
<!--        </a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a-->
<!--          href="https://forum.vuejs.org"-->
<!--          target="_blank"-->
<!--        >-->
<!--          Forum-->
<!--        </a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a-->
<!--          href="https://chat.vuejs.org"-->
<!--          target="_blank"-->
<!--        >-->
<!--          Community Chat-->
<!--        </a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a-->
<!--          href="https://twitter.com/vuejs"-->
<!--          target="_blank"-->
<!--        >-->
<!--          Twitter-->
<!--        </a>-->
<!--      </li>-->
<!--      <br>-->
<!--      <li>-->
<!--        <a-->
<!--          href="http://vuejs-templates.github.io/webpack/"-->
<!--          target="_blank"-->
<!--        >-->
<!--          Docs for This Template-->
<!--        </a>-->
<!--      </li>-->
<!--    </ul>-->
<!--    <h2>Ecosystem</h2>-->
<!--    <ul>-->
<!--      <li>-->
<!--        <a-->
<!--          href="http://router.vuejs.org/"-->
<!--          target="_blank"-->
<!--        >-->
<!--          vue-router-->
<!--        </a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a-->
<!--          href="http://vuex.vuejs.org/"-->
<!--          target="_blank"-->
<!--        >-->
<!--          vuex-->
<!--        </a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a-->
<!--          href="http://vue-loader.vuejs.org/"-->
<!--          target="_blank"-->
<!--        >-->
<!--          vue-loader-->
<!--        </a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a-->
<!--          href="https://github.com/vuejs/awesome-vue"-->
<!--          target="_blank"-->
<!--        >-->
<!--          awesome-vue-->
<!--        </a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <button @click="increment">Count is :{{count}}</button>-->
<!--      </li>-->
<!--    </ul>-->

    <br>
    <router-link to="/axios">axios</router-link><br><br>
    <router-link to="/member">member table</router-link><br><br>
    <router-link to="/team">team table</router-link><br><br>

    <!--click
     @click 에 메소드 하나만 적을때는 () 가능 , 여러개의 메소드 적을때는 ()까지 꼭 붙여줘야 함.
     -->
    <button @click="href">파일 업로드 예제</button><br><br>
    <br>
    <router-link to="/valCheck">유효성 검사 테스트</router-link>
    <br><br>
    <button @click="$router.push({path:'/parent'})">팝업 테스트</button>
    <br><br>
    <button @click="openNewWindow">새 창 열기 테스트</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () { //data will be exposed on 'this' in script methods
    return {
      msg: '',
      count : 0
    }
  },
  methods: { // methods can be bound as events listeners in templates.
    increment(){
      this.count++;
    },
    href(){
      this.$router.push({name:'MultiPart'}) // 버튼 클릭시 페이지 이동, router/index.js에 선언한 component name 적어주면 됨.
    },
    openNewWindow(){
      window.open('https://google.com','_blank');
    }
  },
  mounted() { // mounted is called when the component is mounted
    console.log(`The initial count is ${this.count}`)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

</style>
