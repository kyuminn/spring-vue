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
     @click ??? ????????? ????????? ???????????? () ?????? , ???????????? ????????? ???????????? ()?????? ??? ???????????? ???.
     -->
    <button @click="href">?????? ????????? ??????</button><br><br>
    <br>
    <router-link to="/valCheck">????????? ?????? ?????????</router-link>
    <br><br>
    <button @click="$router.push({path:'/parent'})">?????? ?????????</button>
    <br><br>
    <button @click="openNewWindow">??? ??? ?????? ?????????</button>
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
      this.$router.push({name:'MultiPart'}) // ?????? ????????? ????????? ??????, router/index.js??? ????????? component name ???????????? ???.
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
