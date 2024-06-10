angular.module('userList',[]).controller("UserListController",function ($scope,$http){
    const contextPath = 'http://localhost:8081/api/v1'

    $scope.getUsers=function (){
        const url=contextPath+"/user/all";
        let i=0;
        $http.get(url)
            .then(function (response){
                console.log(response.data);
                $scope.Users=response.data;
                console.log($scope.Users);
            });
    };
    $scope.setButton=function (Status){
        if(Status!="block"){
            return "Заблокировать";
        }
        else if (Status=="block"){
            return "Разблокировать";
        }
    };
    $scope.changeStatus=function (user){
        const url = contextPath+"/user/block";
        $http.put(url, user)
            .then(function (responce) {
                console.log(responce.data);
                location.reload();
            });
    }
    $scope.openInfo=function (user){
        localStorage.setItem("_some_user", JSON.stringify(user));
        window.location.replace('http://localhost:8081/someProfile.html');
    };
    $scope.getUsers();

});

const showOrHidePassword = () => {
    const password = document.getElementById('password');
    if (password.type === 'password') {
        password.type = 'text';
    } else {
        password.type = 'password';
    }
};