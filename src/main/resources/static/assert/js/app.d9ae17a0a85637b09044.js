webpackJsonp([1],{"+skl":function(e,t){},Cc49:function(e,t){},IAPG:function(e,t){},IZgh:function(e,t){},NHnr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a("7+uW"),o={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var n=a("VU/8")({name:"App"},o,!1,function(e){a("Cc49")},null,null).exports,s=a("/ocq"),i=a("//Fk"),u=a.n(i),l=a("mtWM"),c=a.n(l),d=(a("7JE7"),a("BTaQ")),m=a.n(d);function p(e,t){return new u.a(function(a,r){c.a.post(e,t).then(function(e){a(e.data)},function(e){r(e)}).catch(function(e){r(e)})})}function f(e,t){return new u.a(function(a,r){c.a.get(e,{params:t}).then(function(e){a(e.data)},function(e){r(e)}).catch(function(e){r(e)})})}c.a.defaults.withCredentials=!0,c.a.defaults.timeout=2e4,c.a.defaults.headers.post["Content-Type"]="application/json;charset=UTF-8",c.a.defaults.baseURL="/",c.a.interceptors.request.use(function(e){return e.headers.common["x-token"]=localStorage.getItem("token"),e},function(e){return Object(d.Message)({type:"error",message:"错误的传参"}),u.a.reject(e)}),c.a.interceptors.response.use(function(e){if(200===e.status){if(401===e.data.code||402===e.data.code)e.data.code;else{if(200===e.data.code)return e;e.data.message}return e}return e.data.message,u.a.reject(e)},function(e){return res},function(e){return Object(d.Message)({type:"error",message:"网络异常"}),u.a.reject(e)});var h={name:"Login",data:function(){return{formInline:{user:"",password:""},ruleInline:{user:[{required:!0,message:"用户名不能为空!",trigger:"blur"}],password:[{required:!0,message:"密码不能为空!",trigger:"blur"},{type:"string",min:5,message:"密码长度不能小于5!",trigger:"blur"}]}}},methods:{handleSubmit:function(){var e=this;p("/v1/user/login",{username:this.formInline.user,password:this.formInline.password}).then(function(t){if(200===t.code){var a=t.data;localStorage.setItem("token",a.token),localStorage.setItem("userInfo",a),localStorage.setItem("userId",a.id),"admin"===a.role.roleName?e.$router.push({path:"/admin/users"}):"tea"===a.role.roleName?e.$router.push({path:"/tea/courses"}):e.$router.push({path:"/stu/course"})}})}}},v={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"login"},[a("h2",[e._v("登录")]),e._v(" "),a("Form",{ref:"formInline",attrs:{model:e.formInline,rules:e.ruleInline}},[a("FormItem",{attrs:{prop:"user"}},[a("Input",{attrs:{type:"text",placeholder:"用户名"},model:{value:e.formInline.user,callback:function(t){e.$set(e.formInline,"user",t)},expression:"formInline.user"}},[a("Icon",{attrs:{slot:"prepend",type:"ios-person-outline"},slot:"prepend"})],1)],1),e._v(" "),a("FormItem",{attrs:{prop:"password"}},[a("Input",{attrs:{type:"password",placeholder:"密码"},model:{value:e.formInline.password,callback:function(t){e.$set(e.formInline,"password",t)},expression:"formInline.password"}},[a("Icon",{attrs:{slot:"prepend",type:"ios-lock-outline"},slot:"prepend"})],1)],1),e._v(" "),a("FormItem",[a("Button",{attrs:{type:"primary",long:""},on:{click:function(t){return e.handleSubmit()}}},[e._v("登录")])],1)],1)],1)},staticRenderFns:[]};var g=a("VU/8")(h,v,!1,function(e){a("IZgh")},"data-v-01978ca4",null).exports,y={name:"Users",data:function(){return{data:[],columns:[{title:"姓名",key:"nickname"},{title:"角色",slot:"role"},{title:"学号或工号",key:"no"},{title:"操作",slot:"action",width:150,align:"center"}],totalElements:0,pageNo:1}},mounted:function(){this.queryData()},methods:{addUserInfo:function(){this.$router.push({path:"/admin/user"})},changePage:function(e){this.pageNo=e,this.queryData()},queryData:function(){var e=this;f("/v1/user/getUsers",{pageNo:this.pageNo,pageSize:10}).then(function(t){if(200===t.code){var a=t.data;e.data=a.content,e.totalElements=a.totalElements,e.pageNo=a.number+1,console.log(a)}})},remove:function(e){var t=new FormData;t.append("userId",e),p("/v1/user/delUser",t).then(function(e){200===e.code&&console.log(e.data)})},edit:function(e){this.$router.push({path:"/admin/user",query:{id:e}})},handleSubmit:function(){var e=this;p("/v1/user/login",{username:this.formInline.user,password:this.formInline.password}).then(function(t){if(200===t.code){var a=t.data;localStorage.setItem("token",a.token),localStorage.setItem("userInfo",a),"admin"===a.role.roleName?e.$router.push({path:"/admin/users"}):"tea"===a.role.roleName?e.$router.push({path:"/tea/course"}):e.$router.push({path:"/stu/course"}),console.log(t.data)}}),this.$refs[name].validate(function(t){t?e.$Message.success("Success!"):e.$Message.error("Fail!")})},layout:function(){localStorage.removeItem("token"),localStorage.removeItem("userInfo"),this.$router.push({path:"/"})}}},I={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Layout",[a("Header",{style:{position:"fixed",width:"100%"}},[a("Menu",{attrs:{mode:"horizontal",theme:"dark","active-name":"1"}},[a("div",{staticClass:"layout-logo"}),e._v(" "),a("div",{staticClass:"layout-nav"},[a("MenuItem",{attrs:{name:"1"}},[a("Icon",{attrs:{type:"ios-navigate"}}),e._v("\n            用户管理\n          ")],1)],1)]),e._v(" "),a("a",{staticStyle:{position:"absolute",top:"0",right:"20px","z-index":"1000",color:"white"},attrs:{href:"javascript:(0)"},on:{click:function(t){return e.layout()}}},[e._v("退出")])],1),e._v(" "),a("Content",{style:{margin:"88px 20px 0",background:"#fff",minHeight:"500px"}},[a("div",{staticStyle:{margin:"10px 0 10px 0",height:"30px"}},[a("Button",{staticStyle:{float:"right"},attrs:{type:"primary"},on:{click:function(t){return e.addUserInfo()}}},[e._v("新增")])],1),e._v(" "),a("Table",{attrs:{data:e.data,columns:e.columns},scopedSlots:e._u([{key:"role",fn:function(t){var r=t.row;return["admin"===r.role.roleName?a("strong",[e._v("管理员")]):"tea"===r.role.roleName?a("strong",[e._v("教师")]):a("strong",[e._v("学生")])]}},{key:"action",fn:function(t){var r=t.row;return t.index,[a("Button",{staticStyle:{"margin-right":"5px"},attrs:{type:"primary",size:"small"},on:{click:function(t){return e.edit(r.id)}}},[e._v("编辑")]),e._v(" "),a("Button",{attrs:{type:"error",size:"small"},on:{click:function(t){return e.remove(r.id)}}},[e._v("删除")])]}}])}),e._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.totalElements,current:e.pageNo},on:{"on-change":e.changePage}})],1)])],1)],1)],1)},staticRenderFns:[]};var b=a("VU/8")(y,I,!1,function(e){a("NV0d")},"data-v-843f04a4",null).exports,_={name:"User",data:function(){return{userId:0,formValidate:{username:"",nickname:"",no:"",age:"",phone:"",gender:0,roleId:0},ruleValidate:{username:[{required:!0,message:"用户名不能为空!",trigger:"blur"}],nickname:[{required:!0,message:"昵称不能为空!",trigger:"blur"}],no:[{required:!0,message:"学号不能为空!",trigger:"blur"}],age:[{required:!0,message:"年龄不能为空!",trigger:"blur"}],phone:[{required:!0,type:"phone",message:"手机号不能为空",trigger:"blur"}],gender:[{required:!0,message:"性别不能为空!",trigger:"change"}],roleId:[{required:!0,message:"角色不能为空!",trigger:"change"}]}}},mounted:function(){this.userId=this.$route.query.id,null!=this.userId&&this.queryUserInfo(this.userId)},methods:{queryUserInfo:function(e){var t=this,a={userId:e};(new FormData).append("userId",e),f("/v1/user/getUser",a).then(function(e){if(200===e.code){var a=e.data;t.formValidate=a,t.formValidate.roleId=a.role.id}})},submit:function(){var e=this;p("/v1/user/saveUser",this.formValidate).then(function(t){if(200===t.code){t.data;e.$router.push({path:"/admin/users"})}})},back:function(){this.$router.push({path:"/admin/users"})},layout:function(){localStorage.removeItem("token"),localStorage.removeItem("userInfo"),this.$router.push({path:"/"})}}},k={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Layout",[a("Header",{style:{position:"fixed",width:"100%"}},[a("Menu",{attrs:{mode:"horizontal",theme:"dark","active-name":"1"}},[a("div",{staticClass:"layout-logo"}),e._v(" "),a("div",{staticClass:"layout-nav"},[a("MenuItem",{attrs:{name:"1"}},[a("Icon",{attrs:{type:"ios-navigate"}}),e._v("\n            用户管理\n          ")],1)],1)]),e._v(" "),a("a",{staticStyle:{position:"absolute",top:"0",right:"20px","z-index":"1000",color:"white"},attrs:{href:"javascript:(0)"},on:{click:function(t){return e.layout()}}},[e._v("退出")])],1),e._v(" "),a("Content",{style:{margin:"88px 20px 0",background:"#fff",minHeight:"500px",paddingTop:"20px"}},[a("Form",{ref:"formValidate",attrs:{model:e.formValidate,rules:e.ruleValidate,"label-width":200}},[a("FormItem",{attrs:{label:"用户名",prop:"username"}},[a("Input",{attrs:{placeholder:"请输入用户名"},model:{value:e.formValidate.username,callback:function(t){e.$set(e.formValidate,"username",t)},expression:"formValidate.username"}})],1),e._v(" "),a("FormItem",{attrs:{label:"昵称",prop:"nickname"}},[a("Input",{attrs:{placeholder:"请输入昵称"},model:{value:e.formValidate.nickname,callback:function(t){e.$set(e.formValidate,"nickname",t)},expression:"formValidate.nickname"}})],1),e._v(" "),a("FormItem",{attrs:{label:"学号",prop:"no"}},[a("Input",{attrs:{placeholder:"请输入学号"},model:{value:e.formValidate.no,callback:function(t){e.$set(e.formValidate,"no",t)},expression:"formValidate.no"}})],1),e._v(" "),a("FormItem",{attrs:{label:"年龄",prop:"age"}},[a("Input",{attrs:{placeholder:"请输入年龄"},model:{value:e.formValidate.age,callback:function(t){e.$set(e.formValidate,"age",t)},expression:"formValidate.age"}})],1),e._v(" "),a("FormItem",{attrs:{label:"电话",prop:"phone"}},[a("Input",{attrs:{placeholder:"请输入电话"},model:{value:e.formValidate.phone,callback:function(t){e.$set(e.formValidate,"phone",t)},expression:"formValidate.phone"}})],1),e._v(" "),a("FormItem",{attrs:{label:"性别",prop:"gender"}},[a("RadioGroup",{model:{value:e.formValidate.gender,callback:function(t){e.$set(e.formValidate,"gender",t)},expression:"formValidate.gender"}},[a("Radio",{attrs:{label:1,value:"1"}},[e._v("男")]),e._v(" "),a("Radio",{attrs:{label:2,value:"2"}},[e._v("女")])],1)],1),e._v(" "),a("FormItem",{attrs:{label:"角色",prop:"roleId"}},[a("RadioGroup",{model:{value:e.formValidate.roleId,callback:function(t){e.$set(e.formValidate,"roleId",t)},expression:"formValidate.roleId"}},[a("Radio",{attrs:{label:2,value:2}},[e._v("教师")]),e._v(" "),a("Radio",{attrs:{label:3,value:3}},[e._v("学生")])],1)],1),e._v(" "),a("FormItem",[a("Button",{attrs:{type:"primary"},on:{click:function(t){return e.submit()}}},[e._v("提交")]),e._v(" "),a("Button",{staticStyle:{"margin-left":"8px"},on:{click:function(t){return e.back()}}},[e._v("返回")])],1)],1)],1)],1)],1)},staticRenderFns:[]};var x=a("VU/8")(_,k,!1,function(e){a("IAPG")},"data-v-3863191f",null).exports,V={name:"Course",data:function(){return{data:[],columns:[{title:"课程名",key:"courseName"},{title:"房间号",key:"roomNo"},{title:"课程描述",key:"desc"},{title:"订阅数",key:"subNum"},{title:"操作",slot:"action",width:150,align:"center"}],totalElements:0,pageNo:1}},mounted:function(){this.queryCourse()},methods:{addCourse:function(){this.$router.push({path:"/tea/course"})},changePage:function(e){this.pageNo=e,this.queryData()},queryCourse:function(){var e=this;f("/v1/course/getCourses",{pageNo:this.pageNo,pageSize:10}).then(function(t){if(200===t.code){var a=t.data;e.data=a.content,e.totalElements=a.totalElements,e.pageNo=a.number+1}})},edit:function(e){this.$router.push({path:"/tea/course",query:{courseId:e}})},remove:function(e){var t=this,a=new FormData;a.append("courseId",e),p("/v1/course/delCourse",a).then(function(e){200===e.code&&t.queryCourse()})},layout:function(){localStorage.removeItem("token"),localStorage.removeItem("userInfo"),this.$router.push({path:"/"})}}},S={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Layout",[a("Header",{style:{position:"fixed",width:"100%"}},[a("Menu",{attrs:{mode:"horizontal",theme:"dark","active-name":"1"}},[a("div",{staticClass:"layout-logo"}),e._v(" "),a("div",{staticClass:"layout-nav"},[a("MenuItem",{attrs:{name:"1"}},[a("Icon",{attrs:{type:"ios-navigate"}}),e._v("\n            课程管理\n          ")],1)],1)]),e._v(" "),a("a",{staticStyle:{position:"absolute",top:"0",right:"20px","z-index":"1000",color:"white"},attrs:{href:"javascript:(0)"},on:{click:function(t){return e.layout()}}},[e._v("退出")])],1),e._v(" "),a("Content",{style:{margin:"88px 20px 0",background:"#fff",minHeight:"500px"}},[a("div",{staticStyle:{margin:"10px 0 10px 0",height:"30px"}},[a("Button",{staticStyle:{float:"right"},attrs:{type:"primary"},on:{click:function(t){return e.addCourse()}}},[e._v("新增")])],1),e._v(" "),a("Table",{attrs:{data:e.data,columns:e.columns},scopedSlots:e._u([{key:"action",fn:function(t){var r=t.row;return t.index,[a("Button",{staticStyle:{"margin-right":"5px"},attrs:{type:"primary",size:"small"},on:{click:function(t){return e.edit(r.id)}}},[e._v("编辑")]),e._v(" "),a("Button",{attrs:{type:"error",size:"small"},on:{click:function(t){return e.remove(r.id)}}},[e._v("删除")])]}}])}),e._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.totalElements,current:e.pageNo},on:{"on-change":e.changePage}})],1)])],1)],1)],1)},staticRenderFns:[]};var w=a("VU/8")(V,S,!1,function(e){a("ij+V")},"data-v-1b0e4d08",null).exports,N={name:"User",data:function(){return{courseId:0,formValidate:{courseName:"",roomNo:"",desc:""},ruleValidate:{courseName:[{required:!0,message:"课程名不能为空!",trigger:"blur"}],roomNo:[{required:!0,message:"房间号不能为空!",trigger:"blur"}],desc:[{required:!0,message:"描述不能为空!",trigger:"blur"}]}}},mounted:function(){this.courseId=this.$route.query.courseId,null!=this.courseId&&this.queryCourseInfo(this.courseId)},methods:{queryCourseInfo:function(e){var t=this;f("/v1/course/getCourse",{courseId:e}).then(function(e){if(200===e.code){var a=e.data;t.formValidate=a,t.formValidate.roleId=a.role.id}})},submit:function(){var e=this;p("/v1/course/saveCourse",this.formValidate).then(function(t){if(200===t.code){t.data;e.$router.push({path:"/tea/courses"})}})},back:function(){this.$router.push({path:"/tea/courses"})},layout:function(){localStorage.removeItem("token"),localStorage.removeItem("userInfo"),this.$router.push({path:"/"})}}},C={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Layout",[a("Header",{style:{position:"fixed",width:"100%"}},[a("Menu",{attrs:{mode:"horizontal",theme:"dark","active-name":"1"}},[a("div",{staticClass:"layout-logo"}),e._v(" "),a("div",{staticClass:"layout-nav"},[a("MenuItem",{attrs:{name:"1"}},[a("Icon",{attrs:{type:"ios-navigate"}}),e._v("\n            课程管理\n          ")],1)],1)]),e._v(" "),a("a",{staticStyle:{position:"absolute",top:"0",right:"20px","z-index":"1000",color:"white"},attrs:{href:"javascript:(0)"},on:{click:function(t){return e.layout()}}},[e._v("退出")])],1),e._v(" "),a("Content",{style:{margin:"88px 20px 0",background:"#fff",minHeight:"500px",paddingTop:"20px"}},[a("Form",{ref:"formValidate",attrs:{model:e.formValidate,rules:e.ruleValidate,"label-width":200}},[a("FormItem",{attrs:{label:"课程名",prop:"courseName"}},[a("Input",{attrs:{placeholder:"请输入课程名"},model:{value:e.formValidate.courseName,callback:function(t){e.$set(e.formValidate,"courseName",t)},expression:"formValidate.courseName"}})],1),e._v(" "),a("FormItem",{attrs:{label:"房间号",prop:"roomNo"}},[a("Input",{attrs:{placeholder:"请输入房间号"},model:{value:e.formValidate.roomNo,callback:function(t){e.$set(e.formValidate,"roomNo",t)},expression:"formValidate.roomNo"}})],1),e._v(" "),a("FormItem",{attrs:{label:"课程描述",prop:"desc"}},[a("Input",{attrs:{placeholder:"课程描述"},model:{value:e.formValidate.desc,callback:function(t){e.$set(e.formValidate,"desc",t)},expression:"formValidate.desc"}})],1),e._v(" "),a("FormItem",[a("Button",{attrs:{type:"primary"},on:{click:function(t){return e.submit()}}},[e._v("提交")]),e._v(" "),a("Button",{staticStyle:{"margin-left":"8px"},on:{click:function(t){return e.back()}}},[e._v("返回")])],1)],1)],1)],1)],1)},staticRenderFns:[]};var $=a("VU/8")(N,C,!1,function(e){a("sgYa")},"data-v-e281ef04",null).exports,q={name:"Course",data:function(){return{userId:0,data:[],columns:[{title:"课程名",key:"courseName"},{title:"房间号",key:"roomNo"},{title:"课程描述",key:"desc"},{title:"操作",slot:"action",width:150,align:"center"}],totalElements:0,pageNo:1}},mounted:function(){this.userId=localStorage.getItem("userId"),this.queryCourse()},methods:{queryCourse:function(){var e=this,t={pageNo:this.pageNo,pageSize:10};f("/v1/course/getCourses",t).then(function(a){if(200===a.code){var r=a.data;for(t in r.content)r.content[t].userIds&&r.content[t].userIds.split(",").includes(e.userId)?r.content[t].isSub=1:r.content[t].isSub=0;e.data=r.content,e.totalElements=r.totalElements,e.pageNo=r.number+1}})},changePage:function(e){this.pageNo=e,this.queryData()},sub:function(e){var t=this,a=new FormData;a.append("courseId",e),p("/v1/course/subCourses",a).then(function(e){200===e.code&&t.queryCourse()})},abbrSub:function(e){var t=this,a=new FormData;a.append("courseId",e),p("/v1/course/abbrSubCourses",a).then(function(e){200===e.code&&t.queryCourse()})},layout:function(){localStorage.removeItem("token"),localStorage.removeItem("userInfo"),this.$router.push({path:"/"})}}},F={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Layout",[a("Header",{style:{position:"fixed",width:"100%"}},[a("Menu",{attrs:{mode:"horizontal",theme:"dark","active-name":"1"}},[a("div",{staticClass:"layout-logo"}),e._v(" "),a("div",{staticClass:"layout-nav"},[a("MenuItem",{attrs:{name:"1"}},[a("Icon",{attrs:{type:"ios-navigate"}}),e._v("\n            课程管理\n          ")],1)],1)]),e._v(" "),a("a",{staticStyle:{position:"absolute",top:"0",right:"20px","z-index":"1000",color:"white"},attrs:{href:"javascript:(0)"},on:{click:function(t){return e.layout()}}},[e._v("退出")])],1),e._v(" "),a("Content",{style:{margin:"88px 20px 0",background:"#fff",minHeight:"500px"}},[a("Table",{attrs:{data:e.data,columns:e.columns},scopedSlots:e._u([{key:"action",fn:function(t){var r=t.row;return t.index,[a("Button",{directives:[{name:"show",rawName:"v-show",value:0===r.isSub,expression:"row.isSub === 0"}],staticStyle:{"margin-right":"5px"},attrs:{type:"primary",size:"small"},on:{click:function(t){return e.sub(r.id)}}},[e._v("订阅")]),e._v(" "),a("Button",{directives:[{name:"show",rawName:"v-show",value:1===r.isSub,expression:"row.isSub === 1"}],attrs:{type:"error",size:"small"},on:{click:function(t){return e.abbrSub(r.id)}}},[e._v("取消订阅")])]}}])}),e._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.totalElements,current:e.pageNo},on:{"on-change":e.changePage}})],1)])],1)],1)],1)},staticRenderFns:[]};var U=a("VU/8")(q,F,!1,function(e){a("nJWC")},"data-v-01c144ae",null).exports;r.default.use(s.a);var E=new s.a({routes:[{path:"/",name:"Login",component:g},{path:"/admin/users",name:"Users",component:b},{path:"/admin/user",name:"User",component:x},{path:"/tea/courses",name:"Courses",component:w},{path:"/tea/course",name:"Course",component:$},{path:"/stu/course",name:"Course1",component:U}]});a("+skl");r.default.config.productionTip=!1,r.default.use(m.a),new r.default({el:"#app",router:E,components:{App:n},template:"<App/>"})},NV0d:function(e,t){},"ij+V":function(e,t){},nJWC:function(e,t){},sgYa:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.d9ae17a0a85637b09044.js.map