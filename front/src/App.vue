<template>
  <div class="app">
    <Navigation :actual-panel="actualPanel" @changePanel="getPanel($event)"/>
    <router-view  @changePanel="getPanel($event)" />
    <footer>
      <p>@Programowanie Zespołowe UMK 2023</p>
      <p>@Zespół nr 2</p>
    </footer>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation.vue";
export default {
  name: 'App',

  data(){
    return {
      actualPanel: "login",
    }
  },


  components: {
    Navigation,
  },

  methods: {
    getPanel(panel){
      this.actualPanel = panel;
    },

  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Teko:wght@500&display=swap');
@import url('https://fonts.googleapis.com/css2?family=PT+Sans:wght@700&display=swap');

:root {
  --appblue: #3b64c5;
  --whiteText: #ffffff;
  --navMenuColor: #d9e7fc;
  --changableElements: #1c232e;
  --footerColor: #e8e8e8;
  --halfView: gray;
}

.app {
  height: 100%;
  width: 100%;
  margin: 0;
}

h1 {
  margin: 20px 0 0 0 ;
}

hr {
  width: 100%;
  border: 2px solid var(--appblue);
}

.option-btn {
  width: 130px;
  height: 40px;
  margin: 20px auto;
  display: flex;
  justify-content: center;
  align-items: center;
  background: var(--appblue);
  border: 1px solid var(--appblue);
  border-radius: 20px;
  font-family: 'Teko', sans-serif;
  font-size: 22px;
  color: var(--whiteText);
  text-decoration: none;
}

.option-btn:hover {
  background: var(--whiteText);
  color: var(--appblue);
}

.negative {
  color: red;
  margin: auto;
}

.positive {
  color: green;
}

.loader {
  width: 20px;
  height: 20px;
  margin: 40px auto 0 auto;
  border: 5px solid #f3f3f3;
  border-radius: 50%;
  border-top: 5px solid var(--appblue);
  border-bottom: 5px solid var(--appblue);
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
}

.panel {
  background: var(--navMenuColor);
  width: 360px;
  margin: 20px auto 220px auto;
  display: block;
  color: var(--appblue);
  text-align: center;
  font-family: 'Teko', sans-serif;
  border: 2px solid var(--appblue);
  border-radius: 30px;
}

.division {
  width: 90%;
  border: 1px solid var(--appblue);
}

input {
  padding: 10px;
  margin: 0;
  font-size: 17px;
  border-radius: 10px;
  border: 2px solid var(--appblue);
}

footer {
  width: 100%;
  height: 70px;
  position: relative;
  margin-top: 150px;
  background-color: var(--footerColor);
  display: grid;
  justify-content: center;
  font-family: 'Teko', sans-serif;
  color: var(--halfView);
  font-size: 14px;
  align-items: end;
  row-gap: 0;
}

p {
  margin: 0;
  text-align: center;
}

.warning-message {
  width: 100%;
  margin-top: 20px;
  display: flex;
  justify-content: center;
  font-size: 22px;
  color: red;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
