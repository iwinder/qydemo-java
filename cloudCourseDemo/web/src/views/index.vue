<template>
    <main role="main">

      <section class="jumbotron text-center">
        <div class="container">
          <h1>在线视频课程平台</h1>
          <p class="lead text-muted"> 知识付费时代刚刚起步，在这个领域有很多的发展机会。整个课程以实战为基础，手把手从零开始，一步一步搭建一个完整的企业级开发架构。不讲废话，只讲干货。</p>
          <p>
           <a href="#" class="btn btn-primary my-2 p-3 font-weight-bold">点击进入所有课程</a>
          </p>
        </div>
      </section>

      <div class="album py-5 bg-light">
        <div class="container">

          <div class="row">
            <div v v-for="o in news" :key="o.id"  class="col-md-4">
              <div class="card mb-4 shadow-sm">
                <img class="img-fluid" v-bind:src="o.image"  @error="defaultImageFuc">
                <div class="card-body">
                    <h4 class="">{{o.name}}</h4>
                    <p class="card-text">{{o.summary}}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                        </div>
                        <small class="text-muted">
                            <span class="badge badge-info"><i class="fa fa-yen" aria-hidden="true"></i>&nbsp;{{o.price}}</span>&nbsp;
                             <span class="badge badge-info"><i class="fa fa-user" aria-hidden="true"></i>&nbsp;123</span>&nbsp;
                        </small>
                    </div>
                </div>
              </div>
            </div>
   
          </div>
        </div>
      </div>

    </main>
</template>
<script>
    export default {
        name: "index",
        data: function() {
            return {
                 news: [],
                //  defaultImage: _this.defaultImageFuc()
            }
        },
        mounted() {
            let _this = this;
            _this.listNews();
        },
        methods: {
            listNews() {
                let _this = this;
                _this.$ajax.get(process.env.VUE_APP_SERVER + '/business/web/course/list-new').then((response) =>{
                    console.log("查询新上好课结果：", response);
                    let resp = response.data;
                    if(resp.success) {
                        _this.news = resp.content;
                    } 
                }).catch((err) => {
                    console.log("error：", err);
                }); 
            },
            defaultImageFuc(event) {
                console.log(event)
                var img=event.srcElement;
                img.src = "../../static/images/course/avatar"+Tool.randomNum(1,8)+".jpg";
                img.onerror = null; //防止一直跳动

            }
        }  
    }
</script>