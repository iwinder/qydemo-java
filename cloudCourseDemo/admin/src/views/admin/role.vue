<template>
    <div>
        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>
            &nbsp;
            <button v-on:click="list()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>
        <!-- ref设置pagination组件别名为 pagination -->
        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="6"></pagination>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>id</th>
                <th>角色</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="role in roles" :key="role.id" >
                <td>{{role.id}}</td>
                <td>{{role.name}}</td>
                <td>{{role.desc}}</td>
            <td>
                <button v-on:click="editResource(role)" class="btn btn-xs btn-info">
                      <i class="ace-icon fa fa-list bigger-120"></i>
                </button>
                &nbsp;
                <!-- 编辑 -->
                <button v-on:click="edit(role)" class="btn btn-xs btn-info">
                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                </button>
                &nbsp;
                <!-- 删除 -->
                <button v-on:click="del(role.id)" class="btn btn-xs btn-danger">
                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                </button> 
            </td>
            </tr> <!--tr结束 -->
            </tbody>
        </table>



        <!-- Modal -->
        <div id="resource-modal" class="modal fade"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">角色资源关联配置</h4>
                    </div>
                    <div class="modal-body">
                        <ul id="tree" class="ztree"></ul>   
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <i class="ace-icon fa fa-times"></i>关闭
                        </button>
                        <button type="button" v-on:click="saveResource()" class="btn btn-primary">
                              <i class="ace-icon fa fa-plus blue"></i>保存
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>

    import Pagination from '../../components/pagination';

    export default {
        name: 'system-role',
        components: {Pagination},
        data: function() {
            return {
                role: {},
                roles: [],
                resources: [],
                zTree: {},
            }
        },
        mounted: function() {
            // this.$parent.activeSidebar("sidebar-system-role");
            let _this = this;
            _this.list(1);
        },
        methods: {
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.role = {};
                $("#form-modal").modal("show");
            },
            /**
             * 点击【编辑】
             */
            edit(role) {
                let _this = this;
                // _this.role = role;
                // 复制给新对象，防止修改影响到源对象
                _this.role = $.extend({},role);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.role.name, "角色")
                    || !Validator.length(_this.role.name, "角色", 1, 50)
                    || !Validator.require(_this.role.desc, "描述")
                    || !Validator.length(_this.role.desc, "描述", 1, 100)
                ) {
                    return;
                }
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/system/admin/role/save",  _this.role).then((response)=>{
                    Loading.hide(_this.$isDebug);
                    console.log("保存角色的结果：", response);
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
            del(roleId) {
                let _this = this;
                Confirm.show('确认删除？',"删除后不可恢复，确认删除？", function(){
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + "/system/admin/role/delete/"+roleId).then((response)=>{
                        Loading.hide(_this.$isDebug);
                        console.log("删除角色列表结果：", response);
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/system/admin/role/list", {
                    page: page,
                    size: _this.$refs.pagination.size // $refs使用组件别名pagination，获取组件里面的变量size
                }).then((response)=>{
                    Loading.hide(_this.$isDebug);
                    console.log("查询角色的结果：", response);
                    let resp = response.data;
                    _this.roles = resp.content.list;
                    // 重新渲染分页组件，使其页码样式与查询页数相同
                    _this.$refs.pagination.render(page, resp.content.total);
                });
            },
            /**
             * 点击【编辑】
             */
            editResource(role) {
                let _this = this;
                _this.role = $.extend({}, role);
                _this.loadResource();
                $("#resource-modal").modal("show");
            },

            /**
             * 加载资源树
             */
            loadResource() {
                let _this = this;
                Loading.show();
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/system/admin/resource/load-tree').then((res)=>{
                Loading.hide();
                let response = res.data;
                _this.resources = response.content;
                // 初始化树
                _this.initTree();
                })
            },

            /**
             * 初始资源树
             */
            initTree() {
                let _this = this;
                let setting = {
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                    idKey: "id",
                    pIdKey: "parent",
                    rootPId: "",
                    enable: true
                    }
                }
                };

                _this.zTree = $.fn.zTree.init($("#tree"), setting, _this.resources);
                _this.zTree.expandAll(true);
            },
            /**
             * 资源模态框点击【保存】
             */
            saveResource() {
                let _this = this;
                let resources = _this.zTree.getCheckedNodes();
                console.log("勾选的资源：", resources);

                // 保存时，只需要保存资源id，所以使用id数组进行参数传递
                let resourceIds = [];
                for (let i = 0; i < resources.length; i++) {
                resourceIds.push(resources[i].id);
                }

                _this.$ajax.post(process.env.VUE_APP_SERVER + '/system/admin/role/save-resource', {
                id: _this.role.id,
                resourceIds: resourceIds
                }).then((response)=>{
                let resp = response.data;
                if (resp.success) {
                    Toast.success("保存成功!");
                } else {
                    Toast.warning(resp.message);
                }
                });
            },
        }
    }
</script>

