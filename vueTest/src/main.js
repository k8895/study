// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
/* 1.定义组件，这里使用从其他文件import进来 */
import Vue from 'vue'
import App from './App'
import router from './router'
import VueRouter from 'vue-router'
import YDUI from 'vue-ydui'
import 'vue-ydui/dist/ydui.rem.css'

Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(YDUI)

/* eslint-disable no-new */
new Vue({
  el: '#apps',
  router,
  template: '<App/>',
  components: {App}
})

Vue.component('do-item', {
  template: '<p>注册组件</p>'
})

Vue.component('props_item', {
  props: ['param'],
  template: '<p>param = {{param.text}}</p>'
})
