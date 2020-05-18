Tool = {
    randomNum: function(minNum,maxNum) {
        // https://www.cnblogs.com/mq0036/p/9139231.html
        // https://www.cnblogs.com/starof/p/4988516.html
        switch(arguments.length){ 
            case 1: 
                return parseInt(Math.random()*minNum+1,10);  
            case 2: 
                return parseInt(Math.random()*(maxNum-minNum+1)+minNum,10);  
            default: 
                return 0;
        } 
      },
    
      
  }