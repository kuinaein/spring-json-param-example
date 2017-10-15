<template>
  <div id="app">
    <hello-world :foo="foo" :contextPath="contextPath" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import Component from 'vue-class-component';
import { Prop } from 'vue-property-decorator';

import HelloWorld from './components/HelloWorld';

@Component<Vue>({
  name: 'app',
  components: {
    HelloWorld,
  },
})
export default class App extends Vue {
  @Prop({ required: true })
  public foo: {};

  @Prop({ required: true })
  public contextPath: string;
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
