angular.module('releaseOpsApp', [])
  .factory('Api', ['$http', function($http){
    var base = 'http://localhost:8080/api/releases';
    return {
      list: function(){ return $http.get(base); },
      create: function(data){ return $http.post(base, data); },
      update: function(id,data){ return $http.put(base + '/' + id, data); },
      delete: function(id){ return $http.delete(base + '/' + id); }
    };
  }])
  .controller('MainCtrl', ['Api', '$window', function(Api, $window){
    var vm = this;
    vm.releases = [];
    vm.form = {status: 'Open'};

    vm.load = function(){
      Api.list().then(function(res){ vm.releases = res.data; });
    };

    vm.create = function(){
      Api.create(vm.form).then(function(){ vm.form = {status: 'Open'}; vm.newRelease = false; vm.load(); });
    };

    vm.edit = function(r){ vm.editing = angular.copy(r); };

    vm.update = function(){
      Api.update(vm.editing.id, vm.editing).then(function(){ vm.editing = null; vm.load(); });
    };

    vm.delete = function(id){ if($window.confirm('Delete release '+id+'?')) Api.delete(id).then(vm.load); };

    vm.cancelEdit = function(){ vm.editing = null; };

    vm.login = function(){
      // This is a mock login forr demo - store a placeholder token
      $window.localStorage.setItem('releaseops_token', 'demo-token');
      alert('Logged in (mock)');
    };

    vm.load();
  }]);
