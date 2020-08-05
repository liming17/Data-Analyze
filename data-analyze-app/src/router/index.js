import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Profile from "../views/Profile.vue";
import Missing from "../views/Missing.vue";
import Info from "../views/Info.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/profile",
    name: "ProfileMain",
    component: Profile
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },
  {
    path: "/register",
    name: "register",
    component: Register
  },
  {
    // profile/1/info
    path: "/profile/:id",
    name: "Profile",
    component: Profile,
    children: [
      {
        path: 'info',
        name: "Info",
        component: Info
      }
    ]
  },
  {
    path: "/blog",
    name: "Blog",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Blog.vue")
  },
  {
    path:"*",
    component: Missing
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
