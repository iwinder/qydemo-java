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
             </div>
         </div>
         <hr>
         
        <!-- ref设置pagination组件别名为 pagination -->
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="6"></pagination>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>名称</th>
                <th>页面</th>
                <th>请求</th>
                <th>父id</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="resource in resources" :key="resource.id" >
                <td>{{resource.id}}</td>
                <td>{{resource.name}}</td>
                <td>{{resource.page}}</td>
                <td>{{resource.request}}</td>
                <td>{{resource.parent}}</td>
            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <!-- 编辑 -->
                    <button v-on:click="edit(resource)" class="btn btn-xs btn-info">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>
                    <!-- 删除 -->
                    <button v-on:click="del(resource.id)" class="btn btn-xs btn-danger">
                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                </div>

                <div class="hidden-md hidden-lg">
                    <div class="inline pos-rel">
                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                        </button>

                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">

                            <li>
                                <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                            <span class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                            </span>
                                </a>
                            </li>

                            <li>
                                <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                            <span class="red">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </td>
            </tr> <!--tr结束 -->
            </tbody>
        </table>
 
    </div>

</template>

<script>

    import Pagination from '../../components/pagination';

    export default {
        name: 'system-resource',
        components: {Pagination},
        data: function() {
            return {
                resource: {},
                resources: [],
                resourceStr: "",
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
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/system/admin/resource/list", {
                    page: page,
                    size: _this.$refs.pagination.size // $refs使用组件别名pagination，获取组件里面的变量size
                }).then((response)=>{
                    Loading.hide(_this.$isDebug);
                    console.log("查询资源的结果：", response);
                    let resp = response.data;
                    _this.resources = resp.content.list;
                    // 重新渲染分页组件，使其页码样式与查询页数相同
                    _this.$refs.pagination.render(page, resp.content.total);
                });
            }
        }
    }
</script>

