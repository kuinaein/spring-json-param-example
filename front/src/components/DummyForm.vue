<template>
<form ref="form" method="POST">
  <input type="hidden" name="json" :value="json" />
</form>
</template>

<script lang="ts">
import Vue from 'vue';
import Component from 'vue-class-component';

@Component({})
export default class DummyForm extends Vue {
  public $refs: {
    form: HTMLFormElement;
  };

  // undefined will not be reactive
  public json: string | null = null;

  public submit(req: any) {
    this.json = JSON.stringify(req);
    Vue.nextTick(() => this.$refs.form.submit());
  }
}
</script>
