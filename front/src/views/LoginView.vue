<template>
  <div class="panel">
    <h1>Logowanie</h1>
    <hr class="division">
    <LoginForm />
    <div class="maybe">
      <hr class="diver"/>
      <p>lub</p>
      <hr class="diver"/>
    </div>
    <div id="question">
      <p>Nie masz konta?</p>
      <router-link @click="singalPanel('regis')" to="/registration"> Zarejestruj się!</router-link>
    </div>
  </div>
</template>

<script>
import LoginForm from "@/components/forms/LoginForm.vue";

export default {
  name: "LoginView",
  components: {
    LoginForm
  },

  methods: {
    singalPanel(panel){
      this.$emit('changePanel', panel);
    }
  }
}
</script>

<style scoped>

.maybe {
  width: 100%;
  display: flex;
  align-items: center;
}

.diver {
  width: 25%;
  height: 0;
  border: 1px solid var(--appblue);
}

#question {
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 19px;
  margin-bottom: 10px;
}

</style>