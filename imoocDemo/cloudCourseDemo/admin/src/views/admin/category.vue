<template>
    <div class="row">
        <div class="col-md-6">
            <p>
                <button v-on:click="add1()" class="btn btn-white btn-default btn-round">
                    <i class="ace-icon fa fa-edit"></i>
                    新增一级分类
                </button>
                &nbsp;
                <button v-on:click="all()" class="btn btn-white btn-default btn-round">
                    <i class="ace-icon fa fa-refresh"></i>
                    刷新
                </button>
            </p>

            <table id="level1-table" class="table  table-bordered table-hover">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <tr v-for="category in level1" :key="category.id" v-on:click="onClickLevel1(category)" v-bind:class="{'active' : category.id === active.id}" >
                        <td>{{category.id}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>
                        <td>
                            <!-- 编辑 -->
                            <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>
                            <!-- 删除 -->
                            <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                            </button>
                        </td>
                    </tr> <!--tr结束 -->
                </tbody>
            </table>   
        </div>
        <div class="col-md-6">
            <p>
                <button v-on:click="add2()" class="btn btn-white btn-default btn-round">
                    <i class="ace-icon fa fa-edit"></i>
                    新增二级分类
                </button>
            </p>
            <table id="level1-table" class="table  table-bordered table-hover">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>名称</th>
                        <th>顺序</th>
                        <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    <tr v-for="category in level2" :key="category.id" >
                        <td>{{category.id}}</td>
                        <td>{{category.name}}</td>
                        <td>{{category.sort}}</td>
                        <td>
                            <!-- 编辑 -->
                            <button v-on:click="edit(category)" class="btn btn-xs btn-info">
                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                            </button>
                            <!-- 删除 -->
                            <button v-on:click="del(category.id)" class="btn btn-xs btn-danger">
                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                            </button>
                        </td>
                    </tr> <!--tr结束 -->
                </tbody>
            </table>
        </div>


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
                             
 
                            <div class="form-group">
                                <label   class="col-sm-2 control-label">父分类</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">{{active.name || "无"}}</p>    
                                </div>
                            </div>
 
                            <div class="form-group">
                                <label   class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">

                                     <input   v-model="category.name" class="form-control" placeholder="名称">
                                </div>
                            </div>
 
                            <div class="form-group">
                                <label   class="col-sm-2 control-label">顺序</label>
                                <div class="col-sm-10">

                                     <input   v-model="category.sort" class="form-control" placeholder="顺序">
                                </div>
                            </div>

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



    export default {
        name: 'business-category',
        components: {},
        data: function() {
            return {
                category: {},
                categorys: [],
                level1: [],
                level2:[],
                active: {},
            }
        },
        mounted: function() {
            // this.$parent.activeSidebar("sidebar-business-category");
            let _this = this;
            _this.all();
        },
        methods: {
            /**
             * 点击【新增一级分类】
             */
            add1() {
                let _this = this;
                _this.category = {};
                _this.active = {};
                _this.level2 = [];
                _this.category = {
                    parent: "00000000"
                }
                $("#form-modal").modal("show");
            },
            /**
             * 点击【新增二级分类】
             */
            add2() {
                let _this = this;
                if(Tool.isEmpty(_this.active)) {
                    Toast.warning("请先点击一级分类");
                    return;
                }
                _this.category = {
                    parent: _this.active.id
                };
                $("#form-modal").modal("show");
            },
            /**
             * 点击【编辑】
             */
            edit(category) {
                let _this = this;
                // _this.category = category;
                // 复制给新对象，防止修改影响到源对象
                _this.category = $.extend({},category);
                $("#form-modal").modal("show");
            },
            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                // 保存校验
                if (1 != 1
                    || !Validator.require(_this.category.parent, "父id")
                    || !Validator.require(_this.category.name, "名称")
                    || !Validator.length(_this.category.name, "名称", 1, 50)
                ) {
                    return;
                }
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/category/save",  _this.category).then((response)=>{
                    Loading.hide(_this.$isDebug);
                    console.log("保存分类的结果：", response);
                    let resp = response.data;
                    if(resp.success) {
                        $("#form-modal").modal("hide");
                        _this.all();
                        Toast.success("保存成功");
                    }else {
                        Toast.error(resp.message);
                    }


                });
            },
            /**
             * 点击【删除】
             */
            del(categoryId) {
                let _this = this;
                Confirm.show('确认删除？',"删除后不可恢复，确认删除？", function(){
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + "/business/admin/category/delete/"+categoryId).then((response)=>{
                        Loading.hide(_this.$isDebug);
                        console.log("删除分类列表结果：", response);
                        let resp = response.data;
                        if (resp.success) {
                            _this.all();
                            Toast.success("删除成功");

                        }
                    })
                })
            },
            /**
             * 列表查询
             */
            all() {
                let _this = this;
                _this.$ajax.post(process.env.VUE_APP_SERVER + "/business/admin/category/all").then((response)=>{
                    console.log("查询分类的结果：", response);
                    let resp = response.data;
                    _this.categorys = resp.content;
                    _this.level1 = [];
                    // 将所有记录格式化成树形结构
                    for(let i=0;i< _this.categorys.length; i++) {
                        let c = _this.categorys[i];
                        if(c.parent === "00000000") {
                            _this.level1.push(c);
                            for(let j=0;j<_this.categorys.length;j++) {
                                let child = _this.categorys[j];
                                if(child.parent === c.id) {
                                    if(Tool.isEmpty(c.children)) {
                                        c.children = [];
                                    }
                                    c.children.push(child);
                                }
                            }
                        }
                    }

                    _this.level2 = [];
                    // 对当前一级分类中选中的表格触发一次点击事件，以刷新二级菜单列表
                    // 注意：界面的渲染需要等vue绑定好变量后才做，所以加延时100ms
                    setTimeout(function () {
                        $("tr.active").trigger("click");
                    }, 100);
                });
            },
            onClickLevel1(category) {
                let _this = this;
                _this.active = category;
                _this.level2 = category.children;
            }
        }
    }
</script>

<style scoped>
 .active td {
    background-color: #d6e9c6 !important;
  }
</style>

