<template>
  <div id="app">
    <button @click="submit" type="button" class="btn btn-primary">
      押してください
    </button>
    <dummy-form ref="form" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import Component from 'vue-class-component';
import { Prop } from 'vue-property-decorator';

import DummyForm from './components/DummyForm';

@Component<Vue>({
  name: 'app',
  components: {
    DummyForm,
  },
})
export default class App extends Vue {
  public $refs: {
    form: DummyForm;
  };

  @Prop({ required: true })
  public foo: {};

  public submit() {
    this.$refs.form.submit(this.foo);
  }
}
</script>
