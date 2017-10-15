<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Context Path</h2>
    <div><a :href="contextPath || '.'">
      {{contextPath || '.'}}
    </a></div>
    <h2>Variable 'foo'</h2>
    <pre>{{foo}}</pre>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      // injected in HTML
      foo,
      contextPath,
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}

pre {
  text-align:left
}
</style>
