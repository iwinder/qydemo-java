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
            <tr><#list fieldList as field>
                    <#if field.nameHump!="createdAt" && field.nameHump!="updatedAt">
                <th>${field.nameCn}</th></#if></#list>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="${domain} in ${domain}s" :key="${domain}.id" >
                <#list fieldList as field><#if field.nameHump!="createdAt" && field.nameHump!="updatedAt">
                <td>{{${domain}.${field.nameHump}}}</td></#if>
                </#list>
            <td>
                <div class="hidden-sm hidden-xs btn-group">
                    <!-- 编辑 -->
                    <button v-on:click="edit(${domain})" class="btn btn-xs btn-info">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>
                    <!-- 删除 -->
                    <button v-on:click="del(${domain}.id)" class="btn btn-xs btn-danger">
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



        <!-- Modal -->
        <div id="form-modal" class="modal fade"   tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <#list fieldList as field> <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
                            <div class="form-group">
                                <label   class="col-sm-2 control-label">${field.nameCn}</label>
                                <div class="col-sm-10">
                                    <input   v-model="${domain}.${field.nameHump}" class="form-control" placeholder="${field.nameCn}">
                                </div>
                            </div></#if>
                        </#list>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" v-on:click="save()" class="btn btn-primary">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>

    import Pagination from '../../components/pagination';

    export default {
        name: '${module}-${domain}',
        components: {Pagination},
        data: function() {
            return {
            ${domain}: {},
            ${domain}s: []
        }
        },
        mounted: function() {
            // this.$parent.activeSidebar("sidebar-${module}-${domain}");
            let _this = this;
            _this.list(1);
        },
        methods: {
            /**
             * 点击【新增】
             */
            add() {
                let _this = this;
                _this.${domain} = {};
                $("#form-modal").modal("show");
            },
            /**
             * 点击【编辑】
             */
            edit(${domain}) {
                let _this = this;
                // _this.${domain} = ${domain};
                // 复制给新对象，防止修改影响到源对象
                _this.${domain} = $.extend({},${domain});
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                // 保存校验
                if (1 != 1
                <#list fieldList as field>
                <#if !field.nullAble>
                    || !Validator.require(_this.${domain}.${field.nameHump}, "${field.nameCn}")
                </#if>
                <#if (field.length > 0)>
                    || !Validator.length(_this.${domain}.${field.nameHump}, "${field.nameCn}", 1, ${field.length})
                </#if>
                </#list>
                ) {
                    return;
                }
                Loding.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/${module}/admin/${domain}/save",  _this.${domain}).then((response)=>{
                    Loding.hide(_this.$isDebug);
                    console.log("保存${tableNameCn}的结果：", response);
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
            del(${domain}Id) {
                let _this = this;
                Confirm.show('确认删除？',"删除后不可恢复，确认删除？", function(){
                    Loding.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + "/${module}/admin/${domain}/delete/"+${domain}Id).then((response)=>{
                        Loding.hide(_this.$isDebug);
                        console.log("删除${tableNameCn}列表结果：", response);
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
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/${module}/admin/${domain}/list", {
                    page: page,
                    size: _this.$refs.pagination.size // $refs使用组件别名pagination，获取组件里面的变量size
                }).then((response)=>{
                    console.log("查询${tableNameCn}的结果：", response);
                    let resp = response.data;
                    _this.${domain}s = resp.content.list;
                    // 重新渲染分页组件，使其页码样式与查询页数相同
                    _this.$refs.pagination.render(page, resp.content.total);
                });
            }
        }
    }
</script>

