angular.module('history',[]).controller("HistoryController",function ($scope,$http) {
    const contextPath = 'http://localhost:8081/api/v1';
    $scope.getUser=function (){
        $scope.User = JSON.parse(localStorage.getItem("_user"));
        if($scope.User==null){
            // window.location.replace('http://localhost:8081/logIn.html');
        }
        console.log(typeof $scope.User);
        console.log($scope.User);

    };

    $scope.showHistory = function () {
        const url = contextPath + '/history/'+$scope.User.id;
        $http.get(url)
            .then(function (response) {
            console.log(response.data)
            $scope.History = response.data;
        });
    };


    $scope.openProfile=function (){
        window.location.replace('http://localhost:8081/profile.html');
    };
    $scope.getUser();
    $scope.showHistory();
});
