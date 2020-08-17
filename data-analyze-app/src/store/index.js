import Vue from "vue";
import Vuex from "vuex";
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    myData:[],
    displayedData:[],
    rows:0,
    showSpinner:false,
    token:"",
    API_token:  "2dd22b04f0mshb3f303e9f309eb1p1fbf79jsnb090e24265fb",
    API_prefix: "https://www.alphavantage.co/query?"
  },
  mutations: {
    SET_DATA(state,myData){
      state.myData = myData;
    },
    SET_ROWS(state,rows){
      state.rows = rows;
    },
    SET_DISPLAY_DATA(state,displayedData){
      state.displayedData = displayedData;
    },
    SET_SPINNER(state,showSpinner){
      state.showSpinner = showSpinner;
    },
    SET_TOKEN(state,token){
      state.token = token;
    }
  },
  actions: {
   async fetchData({commit}) {
      commit("SET_SPINNER",true);
      return new Promise(resolve=>{
        setTimeout(async ()=>{
          const res = await fetch("data.json");
          const val = await res.json();
          resolve(val);
          commit("SET_SPINNER",false);
        },1000)
      })
    },
    async fetchMyData({dispatch,commit}) {
       commit("SET_SPINNER",true);
       const myJson = await dispatch("fetchData");
       commit("SET_DATA",myJson);
       const displayedData = myJson.slice(0, 3);
       commit("SET_DISPLAY_DATA",displayedData);
       commit("SET_SPINNER",false);
       commit("SET_ROWS",myJson.length);
    },
    async paginate({commit, state}, {currentPage,perPage}) {
      const start = (currentPage - 1) * perPage;
      const displayedData = state.myData.slice(start, start + perPage);
      commit(("SET_DISPLAY_DATA"), displayedData);
    },
    updatePagination({commit,dispatch},{myJson,currentPage,perPage}){
       commit("SET_DATA",myJson);
       commit("SET_ROWS",myJson.length);
       dispatch("paginate",{currentPage,perPage});
    },
    async search({dispatch,commit},{text}) {
      commit("SET_SPINNER",true);
       const myJson = await this.dispatch("fetchData");
       const values = myJson.filter(val=> 
        val.name.toLowerCase().includes(text.toLowerCase()));
       dispatch("updatePagination",{myJson:values, currentPage:1, perPage:3});
       commit("SET_SPINNER",false);
    },
    async setToken({commit},{token}){
       commit(("SET_TOKEN"),token);
    },
    async removeToken({commit},{token}){

      axios.get("/logoutCurrentUser?access_token=" + token)
      .then((res) => {
        commit(("SET_TOKEN"),"");
        console.log(res);
      })
      .catch((error) => {
        console.log(error);
    });
    }
    
  },
  getters: {
    myData(state) {
      return state.myData;
    },
    rows(state) {
      return state.rows;
    },
    displayedData(state) {
      return state.displayedData;
    },
    showSpinner(state) {
      return state.showSpinner;
    },
    token(state) {
      return state.token;
    },
    API_token(state) {
      return state.API_token;
    },
    API_prefix(state) {
      return state.API_prefix;
    }
  },
  modules: {}
});
