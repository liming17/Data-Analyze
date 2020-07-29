import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    myData:[],
    displayedData:[],
    rows:0,
    showSpinner:false
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
    }
  },
  actions: {
   async fetchData({commit}){
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
    async fetchMyData({dispatch,commit}){
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
      const displayedData = state.myData.slice(start, start + 3);
      commit(("SET_DISPLAY_DATA"), displayedData);
    },
    updatePagination({commit,dispatch},{myJson,currentPage,perPage}){
       commit("SET_DATA",myJson);
       commit("SET_ROWS",myJson.length);
       dispatch("paginate",{currentPage,perPage});
    },
    async search({dispatch,commit},{text}){
      commit("SET_SPINNER",true);
       const myJson = await this.dispatch("fetchData");
       const values = myJson.filter(val=> 
        val.name.toLowerCase().includes(text.toLowerCase()));
       dispatch("updatePagination",{myJson:values, currentPage:1, perPage:3});
       commit("SET_SPINNER",false);
    }
    
  },
  getters:{
    myData(state){
      return state.myData;
    },
    rows(state){
      return state.rows;
    },
    displayedData(state){
      return state.displayedData;
    },
    showSpinner(state){
      return state.showSpinner;
    }
  },
  modules: {}
});
