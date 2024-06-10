angular.module('orders', []).controller("OrdersController", function ($scope, $http) {
    const contextPath = 'http://localhost:8081/api/v1';
    $scope.getUser = function () {
        $scope.User = JSON.parse(localStorage.getItem("_user"));
        // if ($scope.User == null) {
        //     window.location.replace('http://localhost:8081/logIn.html');
        // }
        console.log($scope.User);
    };

    $scope.getOrders=function (){
        const url = contextPath + '/orders/seller/'+$scope.User.id;
        $http.get(url)
            .then(function (response){
                console.log(response.data);
                $scope.Orders=response.data;
            });

    };
    $scope.editStatus= function (item){
        console.log(item)
        const url = contextPath + '/orders/editOrder';
        $http.put(url,item)
            .then(function (response){
            });
    }

    $scope.getUser()
    $scope.getOrders()
});
