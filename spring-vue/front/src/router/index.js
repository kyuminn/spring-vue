import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import AxiosTest from "../components/AxiosTest"
import MemberTable from "../components/MemberTable"
import TeamTable from "../components/TeamTable"
import MemberDetail from "../components/MemberDetail"
import MultiPart from "../components/MultiPart";
import ValCheck from "../components/ValCheck";
import Parent from "../modal/Parent";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path:'/axios',
      name: 'AxiosTest',
      component : AxiosTest
    },
    {
      path:'/member',
      name:'MemberTable',
      component : MemberTable
    },
    {
      path:'/team',
      name:'TeamTable',
      component : TeamTable
    },
    {
      path:'/detail/:email',
      name:'MemberDetail',
      component : MemberDetail
    },
    {
      path:'/multipart',
      name : 'MultiPart',
      component : MultiPart
    },
    {
      path:'/valCheck',
      name: 'ValCheck',
      component : ValCheck
    },
    {
      path:'/parent',
      name:'Parent',
      component: Parent
    }
  ]
})
