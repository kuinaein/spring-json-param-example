import 'bootstrap-honoka/dist/js/bootstrap.min.js';

import Vue from 'vue';
import App from './App.vue';

Vue.config.productionTip = false;

// injected in HTML
declare const foo: {};
declare const contextPath: string;

/* tslint:disable:no-unused-expression */
new App({
  el: '#app',
  propsData: {
    foo,
    contextPath,
  },
});
