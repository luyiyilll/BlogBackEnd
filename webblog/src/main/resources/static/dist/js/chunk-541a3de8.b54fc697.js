(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-541a3de8"],{8325:function(t,a,e){},"935e":function(t,a,e){"use strict";e.r(a);var r=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"row"},[e("div",{staticClass:"col-md-4 col-md-offset-4 floating-box"},[e("Message",{attrs:{show:t.msgShow,type:t.msgType,msg:t.msg},on:{"update:show":function(a){t.msgShow=a}}}),e("div",{staticClass:"panel panel-default"},[t._m(0),e("div",{staticClass:"panel-body",attrs:{"data-validator-form":""}},[e("div",{staticClass:"form-group"},[e("label",{staticClass:"control-label"},[t._v("用户名")]),e("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.username,expression:"username",modifiers:{trim:!0}},{name:"validator",rawName:"v-validator:input.required",value:{regex:/^[a-zA-Z]+\w*\s?\w*$/,error:"用户名要求以字母开头的单词字符"},expression:"{ regex: /^[a-zA-Z]+\\w*\\s?\\w*$/, error: '用户名要求以字母开头的单词字符' }",arg:"input",modifiers:{required:!0}}],staticClass:"form-control",attrs:{type:"text",placeholder:"请填写用户名"},domProps:{value:t.username},on:{input:function(a){a.target.composing||(t.username=a.target.value.trim())},blur:function(a){return t.$forceUpdate()}}})]),e("div",{staticClass:"form-group"},[e("label",{staticClass:"control-label"},[t._v("密码")]),e("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.password,expression:"password",modifiers:{trim:!0}},{name:"validator",rawName:"v-validator.required",value:{regex:/^\w{6,16}$/,error:"密码要求 6 ~ 16 个单词字符"},expression:"{ regex: /^\\w{6,16}$/, error: '密码要求 6 ~ 16 个单词字符' }",modifiers:{required:!0}}],staticClass:"form-control",attrs:{id:"password",type:"password",placeholder:"请填写密码"},domProps:{value:t.password},on:{input:function(a){a.target.composing||(t.password=a.target.value.trim())},blur:function(a){return t.$forceUpdate()}}})]),e("div",{staticClass:"form-group"},[e("label",{staticClass:"control-label"},[t._v("确认密码")]),e("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.cpassword,expression:"cpassword",modifiers:{trim:!0}},{name:"validator",rawName:"v-validator.required",value:{target:"#password"},expression:"{ target: '#password' }",modifiers:{required:!0}}],staticClass:"form-control",attrs:{type:"password",placeholder:"请填写确认密码"},domProps:{value:t.cpassword},on:{input:function(a){a.target.composing||(t.cpassword=a.target.value.trim())},blur:function(a){return t.$forceUpdate()}}})]),e("div",{staticClass:"form-group"},[e("label",{staticClass:"control-label"},[t._v("图片验证码")]),e("input",{directives:[{name:"model",rawName:"v-model.trim",value:t.captcha,expression:"captcha",modifiers:{trim:!0}},{name:"validator",rawName:"v-validator.required",value:{title:"图片验证码"},expression:"{ title: '图片验证码' }",modifiers:{required:!0}}],staticClass:"form-control",attrs:{type:"text",placeholder:"请填写验证码"},domProps:{value:t.captcha},on:{input:function(a){a.target.composing||(t.captcha=a.target.value.trim())},blur:function(a){return t.$forceUpdate()}}})]),e("div",{staticClass:"thumbnail",attrs:{title:"点击图片重新获取验证码"},on:{click:t.getCaptcha}},[e("div",{staticClass:"captcha vcenter",domProps:{innerHTML:t._s(t.captchaTpl)}})]),e("button",{staticClass:"btn btn-lg btn-success btn-block",attrs:{type:"submit"},on:{click:t.register}},[e("i",{staticClass:"fa fa-btn fa-sign-in"}),t._v(" 注册\n        ")])])])],1)])},s=[function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"panel-heading"},[e("h3",{staticClass:"panel-title"},[t._v("请注册")])])}],i=e("8d85"),o=(e("ac6a"),e("6b75"));function n(t){if(Array.isArray(t))return Object(o["a"])(t)}function c(t){if("undefined"!==typeof Symbol&&Symbol.iterator in Object(t))return Array.from(t)}var l=e("06c5");function p(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}function u(t){return n(t)||c(t)||Object(l["a"])(t)||p()}var d=function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:6,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"QWERTYUIOPLKJHGFDSAZXCVBNM1234567890",e="",r=[];try{r=u(Array(t)).map((function(){return a[Math.floor(Math.random()*a.length)]}))}catch(s){}return r.forEach((function(t){e+='<span class="flex1 hcenter">'.concat(t,"</span>")})),r=r.join(""),{tpl:e,captcha:r}},m=(e("e8ec"),{name:"Register",data:function(){return{captchaTpl:"",localCaptcha:"",username:"",password:"",cpassword:"",captcha:"",msg:"",msgType:"",msgShow:!1}},created:function(){this.getCaptcha()},methods:{getCaptcha:function(){var t=d(6),a=t.tpl,e=t.captcha;this.captchaTpl=a,this.localCaptcha=e},register:function(t){var a=this;this.$nextTick((function(){var e="submit"===t.target.type?t.target:t.target.parentElement;e.canSubmit&&a.submit()}))},submit:function(){var t=this;if(this.captcha.toUpperCase()!==this.localCaptcha)this.showMsg("验证码不正确"),this.getCaptcha();else{var a={username:this.username,password:this.password,avatar:"https://api.adorable.io/avatars/200/".concat(this.username,".png")};Object(i["b"])(this.username,this.password,a.avatar).then((function(e){t.$store.state.user;t.login(a)})).catch((function(a){t.showMsg("该用户已存在")}))}},login:function(t){this.$store.dispatch("login",t),this.showMsg("注册成功","success")},showMsg:function(t){var a=this,e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"warning";this.msg=t,this.msgType=e,this.msgShow=!1,this.$nextTick((function(){a.msgShow=!0}))}}}),h=m,v=(e("e693"),e("2877")),f=Object(v["a"])(h,r,s,!1,null,"ab243a26",null);a["default"]=f.exports},e693:function(t,a,e){"use strict";var r=e("8325"),s=e.n(r);s.a}}]);
//# sourceMappingURL=chunk-541a3de8.b54fc697.js.map