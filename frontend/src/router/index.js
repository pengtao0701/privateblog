/*
 * @Author: pengtao.blog 
 * @Date: 2022-06-17 00:27:53 
 * @Last Modified by: pengtao.blog
 * @Last Modified time: 2022-06-28 22:40:29
 */
import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import MainBlog from "../views/mainview/mainblog.vue";
import ToolView from "../views/mainview/toolpage.vue";
import ApiView from "../views/mainview/apipage.vue";
import AboutView from "../views/mainview/aboutpage.vue";
import NotFound from "../components/404/index.vue";
import Mainindex from "../views/mainindex.vue"
import Editblog from "../views/mainview/editblog.vue"
import UserView from "../views/mainview/userinfopage.vue"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)
import { Message } from 'element-ui'


Vue.use(VueRouter);

const routes = [

    {
        label: "home",
        path: "/",
        component: Home,
        children: [
            {
                path: "",
                redirect: "/mainindex"
            },
            {
                path: '/toolview',
                component: ToolView
            },
            {
                path: "/apiview",
                component: ApiView,
            },
            {
                path: "/aboutview",
                component: AboutView,
            },
            
            {
                path: "/mainindex",
                component: Mainindex,
                children: [
                    {
                        path: "",
                        redirect: "/mianblog"
                    },
                    {
                        path: '/mianblog',
                        component: MainBlog
                    },
                    {
                        path: "/editblog",
                        component: Editblog,
                    },
                    {
                        path: "/userpage",
                        component: UserView,
                    },
                   
                ]
            },
        ]
    },

    {
        path: "/404",
        name: "NotFound",
        component: NotFound,
    },
    {
        path: "*",
        redirect: {
            name: 'NotFound',
        },
        component: NotFound,
    }


];
const router = new VueRouter({
    mode: "history",
    routes,
})

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
// ????????????
// ?????? router.beforeEach ?????????????????????????????????????????????????????????
router.beforeEach((to, from, next) => {
    //?????????????????????????????? 
      if (to.path === '/' || to.path === '/aboutview' || to.path === '/mainindex' || to.path === '/mianblog') {
        next();
      } else {
        // ????????????????????????token
        let token = localStorage.getItem('token');
        // ??????token????????????????????????????????????????????? ??????????????????
        if (token === null || token === '') {
             Message.error('????????????????????????!');
          next({path:'/'});
        } else {
          next();
        }
      }
    });

export default router;