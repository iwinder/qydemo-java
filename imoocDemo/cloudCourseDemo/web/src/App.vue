<template>
  <div >
    <the-header></the-header>
     <router-view></router-view>
    <the-footer></the-footer>
  </div>
</template>

<script> 
import TheHeader from "./components/the-header";
import TheFooter from "./components/the-footer";

export default {
  name: 'app',
  components: { 
    TheHeader,
    TheFooter
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
