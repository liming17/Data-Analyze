import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Profile from "../views/Profile.vue";
import Missing from "../views/Missing.vue";
import Info from "../views/Info.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import MyList from "../components/MyList.vue";
import ViewList from "../components/ViewList.vue";
import WatchList from "../components/WatchList.vue";

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
    //child component url: profile/1/info
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
    path: "/MyList",
    name: "MyListMain",
    component: MyList
  },
  {
    path: "/MyList/:id",
    name: "MyList",
    component: MyList,
    children: [
      {
        path: '',
        component: WatchList, // default child path
      },
      {
        path: 'ViewList',
        name: "ViewList",
        component: ViewList,
        props: true
      },
      {
        path: 'defaultList',
        name: "defaultList",
        component: WatchList,
      },
    ]
  },
  {
    path: "/ViewList",
    name: "ViewList",
    component: ViewList,
    props: true 
  },
  {
    path: "/blog",
    name: "Blog",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Blog.vue")
  },
  {
    path: "/postBlog",
    name: "PostBlog",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/PostBlog.vue")
  },
  {
    path: "/stockOverview/:id",
    name: "StockOverview",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/StockOverview.vue")
  },
  {
    path: "*",
    component: Missing
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
