// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// 여기에 선언해주면 전역적으로 사용할 수 있음
// main.js에 등록한 미들웨어 (vue-moment)같은 것은 : <template> 태그 내부에서는 $를 붙여서 , <script>태그 내부에서는 this. 를 붙여서 접근 가능 (TeamTable.vue참조)
import VueMoment from 'vue-moment'

// axios 전역화해서 사용하기.
// 다른 컴포넌트에서 일일이 import 할 필요 없이 this.$axios.로 접근할 수 있음.
// import axios from "axios";
// Vue.prototype.$axios = axios;

Vue.config.productionTip = false

Vue.use(VueMoment);


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
