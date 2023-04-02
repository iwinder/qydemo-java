<template>
    <div>
        <p>
            <button v-on:click="list()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>
         <div class="row">
             <div class="col-md-6">
                 <textarea id="resource-textarea" class="form-control" v-model="resourceStr" name="resource" rows="10"></textarea>
                  <br>
                  <button id="save-btn" type="button" class="btn btn-primary" v-on:click="save()">
                      保存
                  </button>
             </div>
              <div class="col-md-6">
                   <ul id="tree" class="ztree"></ul>
             </div>
         </div>
         <hr>

  
 
    </div>

</template>

<script>
 
    export default {
        name: 'system-resource',
        components: {},
        data: function() {
            return {
                resource: {},
                resources: [],
                resourceStr: "",
                 tree: {},
            }
        },
        mounted: function() {
            // this.$parent.activeSidebar("sidebar-system-resource");
            let _this = this;
            _this.list(1);
        },
        methods: { 
            /**
             * 点击【编辑】
             */
            edit(resource) {
                let _this = this;
                // _this.resource = resource;
                // 复制给新对象，防止修改影响到源对象
                _this.resource = $.extend({},resource);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                // 保存校验
                // if (1 != 1
                //     || !Validator.require(_this.resource.name, "名称")
                //     || !Validator.length(_this.resource.name, "名称", 1, 100)
                //     || !Validator.length(_this.resource.page, "页面", 1, 50)
                //     || !Validator.length(_this.resource.request, "请求", 1, 200)
                // ) {
                //     return;
                // }

                if (Tool.isEmpty(_this.resourceStr)) {
                    Toast.warning("资源不能为空！");
                    return;
                }  
                let json = JSON.parse(_this.resourceStr);
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/system/admin/resource/save",  json).then((response)=>{
                    Loading.hide(_this.$isDebug);
                    console.log("保存资源的结果：", response);
                    let resp = response.data;
                    if(resp.success) {
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功");
                    }else {
                        Toast.error(resp.message);
                    }


                });
            },
            /**
             * 点击【删除】
             */
            del(resourceId) {
                let _this = this;
                Confirm.show('确认删除？',"删除后不可恢复，确认删除？", function(){
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + "/system/admin/resource/delete/"+resourceId).then((response)=>{
                        Loading.hide(_this.$isDebug);
                        console.log("删除资源列表结果：", response);
                        let resp = response.data;
                        if (resp.success) {
                            _this.list(1);
                            Toast.success("删除成功");

                        }
                    })
                })
            },
            /**
             * 列表查询
             */
            list() {
                let _this = this;
                Loading.show();
                _this.$ajax.get(process.env.VUE_APP_SERVER + "/system/admin/resource/load-tree").then((response)=>{
                    Loading.hide(_this.$isDebug);
                    console.log("查询资源的结果：", response);
                    let resp = response.data;
                    if(resp.success) {
                        _this.resources = resp.content; 
                        // 初始化资源树
                        _this.initTree();
                    } else {
                        Toast.success(resp.message);
                    }

                });
            },
            initTree() {
                let _this = this;  
                let setting = {
                    data: {
                        simpleData: {
                            idKey: "id",
                            pIdKey: "parent",
                            rootPId: "",
                            // enable: true
                        }
                    }
                } 
                _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.resources);
                _this.zTree.expandAll(true);
            }
        }
    }
</script>

