//服务层
app.service('sellerService',function($http){

    this.updateStatus=function(id,status){
        return $http.get('../store/updateStatus?id='+id +'&status='+status);
    }
	this.login=function(entity){
        return  $http.post('../store/login',entity );
    }
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../store/findAll');
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../store/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../store/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../store/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../store/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../store/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../store/search?page='+page+"&rows="+rows, searchEntity);
	}    	
});
